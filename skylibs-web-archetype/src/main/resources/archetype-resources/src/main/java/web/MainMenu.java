#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web;

import ru.skysoftlab.skylibs.web.navigation.NavigationService;

public interface MainMenu extends NavigationService {

	public static final String SYSTEM = "system";
	public static final String USERS = "users";
	
	public static final int CONFIG = 0;
	
	public interface ConfigMenu {
		public static final int CONFIG_CONFIG = 0;
		public static final int CONFIG_USERS = 1;	
	}

}
