/*   
 * Copyright (c) 2013-2023 LanYoung tech co., LTD. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * LanYoung tech. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with LanYoung tech.   
 *   
 */
package com.github.ldw4033.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm3 implements Realm {
	private static Logger log = LoggerFactory.getLogger(MyRealm3.class);

	public String getName() {
		return "myRealm3";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		log.info(username);
		log.info(password);
		if (!"zhangsan".equals(username)) {
			throw new UnknownAccountException();
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException("密码错误");
		}

		return new SimpleAuthenticationInfo(username+"@163.com", password, getName());
	}

}
