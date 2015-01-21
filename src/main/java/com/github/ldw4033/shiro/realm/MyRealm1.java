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
    
public class MyRealm1 implements Realm{

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 得到用户名
		String username=(String) token.getPrincipal();
		// 获得密码
		String password=new String((char[])token.getCredentials()) ;
		if(!"liudianwei".equals(username)){
			throw new UnknownAccountException("用户名错误");
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException("密码错误");
		}
		
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	public String getName() {
		return "myRealm1";
	}

	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

}
  