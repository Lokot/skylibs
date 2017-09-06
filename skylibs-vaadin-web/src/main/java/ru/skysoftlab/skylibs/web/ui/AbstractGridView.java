package ru.skysoftlab.skylibs.web.ui;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Вид с гридом и формой ввода.
 * 
 * @author Артём
 *
 * @param <T>
 *            класс сущности
 * @param <F>
 *            класс формы сущности
 * @param <P>
 *            провайдер
 */
public abstract class AbstractGridView<T, F extends AbstractForm<T>> extends
		BaseMenuView {

	private static final long serialVersionUID = 4734617922366685803L;

	/** класс сущности */
	private Class<T> clazz;
	@Inject
	private EntityManager em;
	private JPAContainer<T> jpaContainer;

	private TextField filter = new TextField();
	protected Grid grid;
	private Button newEntityButton = new Button(getNewButtonLabel());
	private Button deleteEntityButton = new Button(getDelButtonLabel());

	public AbstractGridView(Class<T> clazz) {
		super();
		init(clazz, new Locale("ru"));
	}
	
	public AbstractGridView(Class<T> clazz, Locale locale) {
		super();
		init(clazz, locale);
	}
	
	private void init(Class<T> clazz, Locale locale) {
		this.clazz = clazz;
		grid = new Grid();
		grid.setLocale(locale);
		setLocale(locale);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		configureComponents();
		buildLayout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.skysoftlab.skylibs.web.ui.BaseMenuView#configureComponents()
	 */
	@Override
	protected void configureComponents() {
		try {
			getEntityForm().setGridView(this);
			// Create a persistent person container
			jpaContainer = JPAContainerFactory.make(clazz, em);
			jpaContainer.setEntityProvider(getEntityProvider());
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show(e.getMessage(), Type.TRAY_NOTIFICATION);
		}

		newEntityButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = -4761338363278949750L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					getEntityForm().edit(clazz.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					Notification.show(e.getMessage(), Type.TRAY_NOTIFICATION);
				}
			}
		});
		
		deleteEntityButton.setVisible(false);
		deleteEntityButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = -7453334107338210000L;

			@Override
			public void buttonClick(ClickEvent event) {
				Object itemId = grid.getSelectedRow();
				if (itemId != null) {
					jpaContainer.removeItem(itemId);
					refreshData();
				}
			}
		});

		filter.setInputPrompt("Фильтр...");
		filter.addTextChangeListener(new TextChangeListener() {

			private static final long serialVersionUID = 8724327598558636541L;

			@Override
			public void textChange(TextChangeEvent event) {
				refreshData(event.getText());
			}
		});

		// TODO не понятно зачем это здесь
		grid.setContainerDataSource(new BeanItemContainer<>(clazz));
		grid.setColumnOrder(getColumnOrder());
		for (Object columnId : getRemoveColumn()) {
			grid.removeColumn(columnId);
		}
		for (Entry<String, String> columnEnrty : getColumnsNames().entrySet()) {
			Column bornColumn = grid.getColumn(columnEnrty.getKey());
			bornColumn.setHeaderCaption(columnEnrty.getValue());
		}
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);
		grid.addSelectionListener(new SelectionListener() {

			private static final long serialVersionUID = -1852701286958204444L;

			@Override
			public void select(SelectionEvent event) {
				Object itemId = grid.getSelectedRow();
				if (itemId != null) {
					getEntityForm().edit(
							jpaContainer.getItem(itemId).getEntity());
					deleteEntityButton.setVisible(true);
				} else {
					getEntityForm().edit(null);
					deleteEntityButton.setVisible(false);
				}
			}
		});
		configureGrid();
		refreshData();
	}

	/**
	 * Конфигурируется грид, если надо.
	 */
	protected void configureGrid() {
		// TODO Auto-generated method stub
	}

	protected abstract F getEntityForm();

	protected abstract EntityProvider<T> getEntityProvider();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.skysoftlab.skylibs.web.ui.BaseMenuView#buildLayout()
	 */
	@Override
	protected void buildLayout() {
		HorizontalLayout actions = new HorizontalLayout(filter, newEntityButton, deleteEntityButton);
		actions.setSpacing(true);
		actions.setMargin(new MarginInfo(true, true, true, false));
		actions.setSizeUndefined();
		actions.setWidth("100%");
		filter.setWidth("100%");
		actions.setExpandRatio(filter, 1);

		Label title = new Label(getTitle());
		VerticalLayout left = new VerticalLayout(title, actions, grid);
		left.setSizeFull();
		grid.setSizeFull();
		left.setExpandRatio(grid, 1);

		HorizontalLayout mainLayout = new HorizontalLayout(left,
				getEntityForm());
		mainLayout.setSizeFull();
		mainLayout.setExpandRatio(left, 1);

		// Split and allow resizing
		// setContent(mainLayout);
		layout.addComponent(mainLayout);
		// layout.setComponentAlignment(mainLayout, Alignment.TOP_CENTER);
	}

	public void refreshData() {
		grid.setContainerDataSource(refreshData(filter.getValue()));
		getEntityForm().setVisible(false);
		deleteEntityButton.setVisible(false);
	}

	public JPAContainer<T> getJpaContainer() {
		return jpaContainer;
	}

	public Grid getGrid() {
		return grid;
	}

	protected abstract Indexed refreshData(String value);

	protected abstract String getTitle();

	protected abstract Map<String, String> getColumnsNames();

	protected abstract String getNewButtonLabel();
	
	protected abstract String getDelButtonLabel();

	/**
	 * Колонки для скрытия.
	 * 
	 * @return
	 */
	protected abstract Object[] getRemoveColumn();

	/**
	 * Идентификаторы колонок.
	 * 
	 * @return
	 */
	protected abstract Object[] getColumnOrder();
}
