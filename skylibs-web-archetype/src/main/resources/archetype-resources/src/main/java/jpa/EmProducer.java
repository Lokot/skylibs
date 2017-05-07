#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EmProducer implements Serializable {

	private static final long serialVersionUID = 5127966525383571663L;

	@PersistenceContext(unitName = "${persistenceUnit}")
	protected EntityManager em;

	@Produces
	public EntityManager getEntityManager() {
		return em;
	}

}
