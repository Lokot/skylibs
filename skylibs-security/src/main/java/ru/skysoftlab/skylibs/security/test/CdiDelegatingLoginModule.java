package ru.skysoftlab.skylibs.security.test;

import java.util.Map;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class CdiDelegatingLoginModule implements LoginModule {  
	  
	  
    private LoginModule delegate;  

    public void initialize(Subject subject, CallbackHandler callbackHandler,  
                    Map<String, ?> sharedState, Map<String, ?> options) {  

            String lmClassName = (String) options.get("code");  
            delegate = createCdiInstance(lmClassName);  
            delegate.initialize(subject, callbackHandler, sharedState, options);  
    }  


    private LoginModule createCdiInstance(String className) {  
            try {  
                    Class<?> loginModuleClass = Thread.currentThread().getContextClassLoader().loadClass(className);  
                    BeanManager beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");  
                    Bean<LoginModule> loginModuleBean = (Bean<LoginModule>) beanManager.getBeans(loginModuleClass).iterator().next();  
                    return (LoginModule) beanManager.getReference(loginModuleBean,   
                                    loginModuleClass, beanManager.createCreationalContext(loginModuleBean));  
            } catch (Exception x) {  
                    throw new IllegalStateException(x);  
            }  
    }


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
