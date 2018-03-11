package ru.skysoftlab.swt.viewers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;

import ru.skysoftlab.swt.viewers.annotations.TableColumn;

public abstract class AbstractCheckboxTreeViewer<T> extends CheckboxTreeViewer {

	protected Class<T> clazz;

	protected AbstractViewerComparator comparator;
	protected AbstractViewerFilter<?> filter;

	public AbstractCheckboxTreeViewer(Class<T> aClazz, Composite parent, IDataModel dm,
			AbstractViewerComparator aComparator, AbstractViewerFilter<?> aFilter) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		clazz = aClazz;
		comparator = aComparator;
		filter = aFilter;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		getTree().setLayoutData(gridData);
		createColumns();
		getTree().setHeaderVisible(true);
		getTree().setLinesVisible(true);
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

	// TableViewerColumn
	@SuppressWarnings("unused")
	protected TreeViewerColumn createTableViewerColumn(String header, int width, int idx) {
		TreeViewerColumn column = new TreeViewerColumn(this, SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);
		column.getColumn().addSelectionListener(getSelectionAdapter(column.getColumn(), idx));
		return column;
	}

	private SelectionAdapter getSelectionAdapter(final TreeColumn column, final int index) {
		SelectionAdapter rv = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				getTree().setSortDirection(dir);
				getTree().setSortColumn(column);
				refresh();
			}
		};
		return rv;
	}

	protected void createColumns() {
		List<TableColumn> annList = new ArrayList<>();
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(TableColumn.class)) {
				TableColumn ann = method.getAnnotation(TableColumn.class);
				annList.add(ann);
			}
		}
		annList.sort(new Comparator<TableColumn>() {
			@Override
			public int compare(TableColumn o1, TableColumn o2) {
				return Integer.compare(o1.index(), o2.index());
			}
		});
		for (TableColumn tableColumn : annList) {
			TreeViewerColumn column = createTableViewerColumn(tableColumn.name(), tableColumn.width(),
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
