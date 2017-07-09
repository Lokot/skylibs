package ru.skysoftlab.skylibs.security;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.security.auth.login.FailedLoginException;

import org.apache.openejb.core.security.jaas.LoginProvider;

import ru.skysoftlab.skylibs.security.entitys.UserEntity;

@Alternative
@SuppressWarnings("cdi-ambiguous-dependency")
public class JpaLoginProvider implements LoginProvider {

	
	@Inject
	private EntityManager entityManager;
	
	private PropertiesLoginProvider propLoginProvider = new PropertiesLoginProvider();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.openejb.core.security.jaas.LoginProvider#authenticate(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public List<String> authenticate(String user, String password) throws FailedLoginException {
		try {
			TypedQuery<UserEntity> query = entityManager.createNamedQuery("User.byName", UserEntity.class);
			query.setParameter("name", user);
			UserEntity userEntity = query.getSingleResult();
			try {
				String hashPass = HashUtil.md5DigestForString(password);
				if (userEntity.getPassHash().equals(hashPass)) {
					return userEntity.getRole().getRoles();
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} catch (NoResultException e) {
			return propLoginProvider.authenticate(user, password);
		}
		throw new FailedLoginException();
	}
}
