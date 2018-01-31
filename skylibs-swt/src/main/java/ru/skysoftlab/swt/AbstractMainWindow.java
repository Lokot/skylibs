package ru.skysoftlab.swt;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractMainWindow extends ApplicationWindow {

	protected Shell shell;

	/**
	 * Create the application window.
	 */
	public AbstractMainWindow() {
		super(null);
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(final Composite parent) {
		return getMainContent().createContents(parent);
	}

	/**
	 * Возвращает главную страницу приложения.
	 * 
	 * @return
	 */
	protected abstract AppChildContent getMainContent();

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager mainMenu = new MenuManager();
		for (Object item : getMenuItems()) {
			if(item instanceof MenuManager) {
				mainMenu.add((MenuManager) item);	
			} else if(item instanceof IAction) {
				mainMenu.add((IAction) item);	
			} else if(item instanceof IContributionItem) {
				mainMenu.add((IContributionItem) item);	
			}
		}
		return mainMenu;
	}

	protected abstract Object[] getMenuItems();

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(getAppTitle());
		newShell.setImage(Utils.getImage(newShell.getDisplay(), getAppTitleIcon()));
		shell = newShell;
		postConfigureShell();
	}

	/**
	 * Возвращает наименование файла с иконкой приложения.
	 * 
	 * @return
	 */
	protected abstract String getAppTitleIcon();

	/**
	 * Возвращает наименование приложения.
	 * 
	 * @return
	 */
	protected abstract String getAppTitle();

	/**
	 * Конфигурирование дочерних элементов.
	 */
	protected abstract void postConfigureShell();

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public void clearShell() {
		for (Control kid : shell.getChildren()) {
			kid.dispose();
		}
	}
}
