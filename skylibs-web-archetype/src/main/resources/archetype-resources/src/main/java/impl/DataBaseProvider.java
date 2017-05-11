#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.impl;

import static ${package}.impl.ConfigurationNames.SIMPLE_PROP;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.dto.SystemConfigDto;
import ru.skysoftlab.skylibs.entitys.properties.api.PropertyProvider;

public class DataBaseProvider implements Serializable {

	private static final long serialVersionUID = -3903680512655813319L;

	private Logger LOG = LoggerFactory.getLogger(DataBaseProvider.class);

	@Inject
	private EntityManager em;

	@Resource
	private UserTransaction utx;
	
	@Inject
	private PropertyProvider propertyProvider;

	public void saveConfig(SystemConfigDto dto) throws Exception {
		try {
			utx.begin();
			propertyProvider.setStringValue(SIMPLE_PROP, dto.getSimpleProp(), "Простое свойство");
			utx.commit();
		} finally {
			if (utx.getStatus() == 0) {
				utx.rollback();
				LOG.error("Save config error");
			}
		}
	}
	
}
