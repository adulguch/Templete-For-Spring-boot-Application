package com.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.test.dao.IAppUserDao;
import com.test.dto.AppUserDto;
import com.test.exception.RestException;
import com.test.model.AppUser;
import com.test.persistence.dao.common.IOperations;
import com.test.persistence.service.common.AbstractJpaService;
import com.test.service.IAppUserService;

@Service
public class AppUserServiceImpl extends AbstractJpaService<AppUser> implements IAppUserService {
	@Autowired
	@Qualifier("appUserDaoImpl")
	private IAppUserDao appUserDao;

	@Autowired
	private Environment env;
	
	public AppUserServiceImpl() {
		super();
	}

	@Override
	protected IOperations<AppUser> getDao() {
		// TODO Auto-generated method stub
		return appUserDao;
	}
	
	@Override
	public List<AppUserDto> findAllUsers() throws RestException{
		List<AppUser> appUserDtoList = appUserDao.findAll();
		return appUserDtoList.stream().map(appUser -> this.getAppUserDto(appUser)).collect(Collectors.toList());
	}
	private AppUserDto getAppUserDto(AppUser appUser) {

		return AppUserDto.builder().userId(appUser.getUserId()).userName(appUser.getUserName())
				.firstName(appUser.getFirstName()).lastName(appUser.getLastName()).email(appUser.getEmail())
				.active(appUser.getActive())
				.telephone(appUser.getTelephone())
				.build();
	}

	@Override
	public AppUserDto getByUserId(long userId)throws RestException {
		AppUser appUser = appUserDao.findOne(userId);
		if(appUser != null) {
			return this.getAppUserDto(appUser); 
		}else {
			throw new RestException(env.getProperty("APP_USER_EXCEPTION"),env.getProperty("USER_NOT_FOUND")+userId);
		}
		
		 
	}


}
