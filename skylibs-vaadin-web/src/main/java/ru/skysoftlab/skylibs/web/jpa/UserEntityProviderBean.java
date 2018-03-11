package ru.skysoftlab.skylibs.web.jpa;

import javax.ejb.Stateless;

import ru.skysoftlab.skylibs.security.entitys.UserEntity;

@Stateless
public class UserEntityProviderBean extends EntityProviderBean<UserEntity> {

	public UserEntityProviderBean() {
		super(UserEntity.class);
		setTransactionsHandledByProvider(true);
	}

}