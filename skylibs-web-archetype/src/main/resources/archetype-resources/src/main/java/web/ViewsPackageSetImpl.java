#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web;

import ru.skysoftlab.skylibs.web.ui.ViewsPackageSet;

public class ViewsPackageSetImpl implements ViewsPackageSet {

	@Override
	public String[] getViewsPackages() {
		return new String[] { "${package}.ui" };
	}

}
