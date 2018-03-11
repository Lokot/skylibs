package ru.skysoftlab.skylibs.one.shot.scope.extension;

@SuppressWarnings("rawtypes")
public class KillEvent {
    
	private Class beanType;

    public KillEvent(Class beanType) {
        this.beanType = beanType;
    }

    public Class getBeanType() {
        return beanType;
    }
    
}
