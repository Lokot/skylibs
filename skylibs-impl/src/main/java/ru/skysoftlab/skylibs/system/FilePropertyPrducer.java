package ru.skysoftlab.skylibs.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.skysoftlab.skylibs.annatations.AppPropertyFile;

public class FilePropertyPrducer {

	private Logger LOG = LoggerFactory.getLogger(FilePropertyPrducer.class);

	@Produces
	@AppPropertyFile("")
	public Properties getStringProperty(InjectionPoint ip) {
		Properties prop = new Properties();
		String filename = getFileName(ip);
		InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
		try {
			// load a properties file from class path, inside static method
			prop.load(input);
		} catch (IOException ex) {
			LOG.error("Sorry, unable to find " + filename, ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	private String getFileName(InjectionPoint ip) {
		return ip.getAnnotated().getAnnotation(AppPropertyFile.class).value();
	}
}
