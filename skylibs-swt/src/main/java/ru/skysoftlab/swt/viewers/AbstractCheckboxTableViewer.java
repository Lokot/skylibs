package ru.skysoftlab.swt.viewers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

public abstract class AbstractCheckboxTableViewer<T> extends CheckboxTableViewer {

	protected Class<T> clazz;

	protected AbstractViewerComparator comparator;
	protected AbstractViewerFilter<?> filter;

	@SuppressWarnings("deprecation")
	public AbstractCheckboxTableViewer(Class<T> aClazz, Composite parent, IDataModel dm,
			AbstractViewerComparator aComparator, AbstractViewerFilter<?> aFilter) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		clazz = aClazz;
		comparator = aComparator;
		filter = aFilter;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		getTable().setLayoutData(gridData);
		createColumns();
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		setContentProvider(new ParentChildTreeContentProvider());
		super.setInput(dm);
		if (comparator != null)
			setComparator(comparator);
		if (filter != null)
			addFilter(filter);
		ICheckStateListener listener = getICheckStateListener();
		if (listener != null)
			addCheckStateListener(getICheckStateListener());
	}

	protected abstract ICheckStateListener getICheckStateListener();

	public void setSearchString(String search) {
		filter.setSearchString(search);
	}

	@SuppressWarnings("unused")
	protected TableViewerColumn createTableViewerColumn(String header, int width, int idx) {
		TableViewerColumn column = new TableViewerColumn(this, SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);
		column.getColumn().addSelectionListener(getSelectionAdapter(column.getColumn(), idx));
		return column;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter rv = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				getTable().setSortDirection(dir);
				getTable().setSortColumn(column);
				refresh();
			}
		};
		return rv;
	}

	protected void createColumns() {
		List<ru.skysoftlab.swt.viewers.annotations.TableColumn> annList = new ArrayList<>();
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(ru.skysoftlab.swt.viewers.annotations.TableColumn.class)) {
				ru.skysoftlab.swt.viewers.annotations.TableColumn ann = method
						.getAnnotation(ru.skysoftlab.swt.viewers.annotations.TableColumn.class);
				annList.add(ann);
			}
		}
		annList.sort(new Comparator<ru.skysoftlab.swt.viewers.annotations.TableColumn>() {
			@Override
			public int compare(ru.skysoftlab.swt.viewers.annotations.TableColumn o1,
					ru.skysoftlab.swt.viewers.annotations.TableColumn o2) {
				return Integer.compare(o1.index(), o2.index());
			}
		});
		for (ru.skysoftlab.swt.viewers.annotations.TableColumn tableColumn : annList) {
			TableViewerColumn column = createTableViewerColumn(tableColumn.name(), tableColumn.width(),
					tableColumn.index());
			if (tableColumn.index() == 0) {
				column.setLabelProvider(new DelegatingStyledCellLabelProvider(getColumnLabelProvider()));
			} else {
				column.setLabelProvider(new AnnotationLabelProvider(tableColumn.index()));
			}
		}
	}

	protected abstract IStyledLabelProvider getColumnLabelProvider();

}
