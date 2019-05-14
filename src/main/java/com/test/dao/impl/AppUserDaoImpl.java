package com.test.dao.impl;

import org.springframework.stereotype.Repository;


import com.test.dao.IAppUserDao;
import com.test.model.AppUser;
import com.test.persistence.dao.common.AbstractJpaDao;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AppUserDaoImpl extends AbstractJpaDao<AppUser> implements IAppUserDao{
private static final String APP_USER_EXCEPTION = "APP_USER_EXCEPTION";
	
	public  AppUserDaoImpl() {
		super();
		setClazz(AppUser.class);
	}

}
