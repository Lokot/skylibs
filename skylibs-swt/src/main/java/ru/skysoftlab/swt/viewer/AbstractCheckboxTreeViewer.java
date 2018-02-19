package ru.skysoftlab.swt.viewer;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;

public abstract class AbstractCheckboxTreeViewer extends CheckboxTreeViewer { 

	protected AbstractViewerComparator comparator;
	protected AbstractViewerFilter filter;

	public AbstractCheckboxTreeViewer(Composite parent, IDataModel dm, AbstractViewerComparator aComparator, AbstractViewerFilter aFilter) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		comparator = aComparator;
		filter = aFilter;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		getTree().setLayoutData(gridData);
		createColumns();
		getTree().setHeaderVisible(true);
		getTree().setLinesVisible(true);
		setContentProvider(new ParentChildTreeContentProvider());
		super.setInput(dm);
		setComparator(comparator);
		addFilter(filter);
		addCheckStateListener(getICheckStateListener());
	}
	
	protected abstract ICheckStateListener getICheckStateListener();

	public void setSearchString(String search) {
		filter.setSearchString(search);
	}

	protected abstract void createColumns();

	// TableViewerColumn
	@SuppressWarnings("unused")
	private TreeViewerColumn createTableViewerColumn(String header, int width,
			int idx) {
		TreeViewerColumn column = new TreeViewerColumn(this, SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);
		column.getColumn().addSelectionListener(
				getSelectionAdapter(column.getColumn(), idx));
		return column;
	}

	private SelectionAdapter getSelectionAdapter(final TreeColumn column,
			final int index) {
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

	

}
