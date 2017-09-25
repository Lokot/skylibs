package ru.skysoftlab.skylibs.one.shot.scope.extension;

import java.io.Serializable;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class OneShotScopeExtension implements Extension, Serializable {
    
	private static final long serialVersionUID = -3051185186430931579L;

	public void addScope(@Observes final BeforeBeanDiscovery event) {
        event.addScope(OneShotScope.class, true, false);
    }

    public void registerContext(@Observes final AfterBeanDiscovery event) {
        
        event.addContext(new OneShotScopeContext());
    }
    
    
}
