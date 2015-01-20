package com.github.ldw4033.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class BaseTest {
	@Test
	public void test01(){
		//1、获取SecurityManager工厂，此处使用的是ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、得到SecurityManager实例，并绑定到SecurityUtils
		SecurityManager securityManager= factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、得到Subject及其创建用户名/密码身份验证Token
		Subject currentUser=SecurityUtils.getSubject();
		
		UsernamePasswordToken token=new UsernamePasswordToken("liudw2", "123");
		
		try {
			currentUser.login(token);
			System.out.println(currentUser.getPrincipal());
			System.out.println(currentUser.isAuthenticated());
		} catch (AuthenticationException e) {
			//e.printStackTrace();
			System.out.println("身份验证失败");
			
		}
		
		currentUser.logout();
		
	}

}
