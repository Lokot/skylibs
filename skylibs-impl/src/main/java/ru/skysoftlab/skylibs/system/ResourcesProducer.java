package ru.skysoftlab.skylibs.system;

import java.net.URL;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

//import com.google.common.io.Resources;

import ru.skysoftlab.skylibs.annatations.AppPropertyFile;
import ru.skysoftlab.skylibs.annatations.SimpleQualifier;

public class ResourcesProducer {

	@Produces
	@SimpleQualifier("")
	public URL getResUrl(InjectionPoint ip) {
		String resName = getFileName(ip);
//		URL url = Resources.getResource(resName);
//		return url;
		return null;
	}
	
	private String getFileName(InjectionPoint ip) {
		return ip.getAnnotated().getAnnotation(AppPropertyFile.class).value();
	}
}
