package com.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.dao.IAppUserDao;
import com.test.dto.AppUserDto;
import com.test.model.AppUser;
import com.test.persistence.dao.common.IOperations;
import com.test.persistence.service.common.AbstractJpaService;
import com.test.service.IAppUserService;

@Service
public class AppUserServiceImpl extends AbstractJpaService<AppUser> implements IAppUserService {
	@Autowired
	@Qualifier("appUserDaoImpl")
	private IAppUserDao appUserDao;


	public AppUserServiceImpl() {
		super();
	}

	@Override
	protected IOperations<AppUser> getDao() {
		// TODO Auto-generated method stub
		return appUserDao;
	}
	
	@Override
	public List<AppUserDto> findAllUsers(){
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


}
