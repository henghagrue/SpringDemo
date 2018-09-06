package com.example.oauth.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
	Log logger = LogFactory.getLog(getClass());
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		String result = new BCryptPasswordEncoder().encode(rawPassword);
		logger.info(rawPassword + "加密 > "+result);
		return result;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		logger.info(rawPassword + " > 密码校验 > "+encodedPassword);
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}


}
