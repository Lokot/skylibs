#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import ru.skysoftlab.skylibs.web.annatations.MainMenuItem;
import ru.skysoftlab.skylibs.web.annatations.MenuItemView;
import ru.skysoftlab.skylibs.web.ui.views.UserView;
import ${package}.web.MainMenu;
import ${package}.web.MainMenu.ConfigMenu;

import com.vaadin.cdi.CDIView;

@CDIView(MainMenu.USERS)
@MainMenuItem(name = "Настройки", order = MainMenu.CONFIG)
@MenuItemView(name = "Пользователи", order = ConfigMenu.CONFIG_USERS)
public class UsersView extends UserView {

	private static final long serialVersionUID = 2125858000767147430L;
	
}
