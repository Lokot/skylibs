package ru.skysoftlab.skylibs.system;

import java.io.IOException;
import java.net.URL;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import ru.skysoftlab.skylibs.annatations.ResourcePropetry;
import ru.skysoftlab.skylibs.annatations.SimpleQualifier;

public class ResourcesProducer {

	private Logger LOG = LoggerFactory.getLogger(ResourcesProducer.class);

	@Produces
	@ResourcePropetry("")
	public URL getResUrl(InjectionPoint ip) {
		String resName = getFileName(ip);
		URL url = Resources.getResource(resName);
		return url;
	}

	@Produces
	@ResourcePropetry("")
	public String getResFileToString(InjectionPoint ip) {
		String resName = getFileName(ip);
		URL url = Resources.getResource(resName);
		try {
			return Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			LOG.error("Fail to read resource from url: " + url);
			return "";
		}
	}

	private String getFileName(InjectionPoint ip) {
		return ip.getAnnotated().getAnnotation(SimpleQualifier.class).value();
	}
}
