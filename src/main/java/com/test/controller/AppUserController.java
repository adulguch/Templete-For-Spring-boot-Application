package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.RestResponse;
import com.test.dto.AppUserDto;
import com.test.service.IAppUserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/appuser/")
public class AppUserController {

	@Autowired
	private IAppUserService userService;


	@ApiOperation(value = "Get User details by Id", nickname = "getUserDetails")
	@GetMapping("getAllUsers")
//	@PreAuthorize("hasAnyRole( 'USER_VIEW','USER_CREATE','USER_EDIT','USER_DELETE')")
	public RestResponse findAll() {
		log.debug("Finding All Users");
		List<AppUserDto> data = userService.findAllUsers();
		final RestResponse response = RestResponse.builder().data(data).success(true).build();

		return response;
	}

}
