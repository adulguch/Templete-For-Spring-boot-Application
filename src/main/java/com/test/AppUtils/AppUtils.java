package com.test.AppUtils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AppUtils {

	
	public static String getUserName(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 log.debug(auth.getName());
        return auth.getName();
        
	}
	
	public static boolean isAuthenticated(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       return auth.isAuthenticated();
	}


}

