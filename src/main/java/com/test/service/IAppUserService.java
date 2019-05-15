package com.test.service;

import java.util.List;

import com.test.dto.AppUserDto;
import com.test.exception.RestException;
import com.test.model.AppUser;
import com.test.persistence.dao.common.IOperations;

public interface IAppUserService extends IOperations<AppUser> { 
	
	List<AppUserDto> findAllUsers() throws RestException;
	
	AppUserDto getByUserId(long userId)throws RestException;
}
