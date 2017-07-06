package ru.skysoftlab.skylibs.security.test;

import java.util.Map;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

//@LoginDomain(name = "test1", principal = TestPrincipal.class)  
//@LoginModuleDescription(flag = FlagType.REQUIRED)  
public class Test1LoginModule implements LoginModule {  
	  
	  
//    @Inject BeanForLoginModule beanForLoginModule;

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler arg1,
			Map<String, ?> arg2, Map<String, ?> arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}  

}
