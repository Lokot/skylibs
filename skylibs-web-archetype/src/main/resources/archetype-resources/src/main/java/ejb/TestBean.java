#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class TestBean {
	
	@RolesAllowed("ADMIN")
	public String getProtectedInfo() {
		return "It's protected information.";
	}
}
