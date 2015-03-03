package com.github.ldw4033.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
	private static Logger log=LoggerFactory.getLogger(BaseTest.class);
	@Test
	public void testHelloworld(){
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
	@Test
	public void testCustomRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm1.ini");
		SecurityManager securityManagey=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManagey);
		Subject currentUser=SecurityUtils.getSubject();
		
		UsernamePasswordToken token =new  UsernamePasswordToken("ldw4033", "123");
		
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Assert.assertEquals(true, currentUser.isAuthenticated()); //断言用户已经登录
		currentUser.logout();
		
		
		
 	}
	@Test
	public void testMultiRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm2.ini");
		SecurityManager securityManagey=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManagey);
		Subject currentUser=SecurityUtils.getSubject();
		
//		UsernamePasswordToken token =new  UsernamePasswordToken("zhangsan", "123");
		UsernamePasswordToken token =new  UsernamePasswordToken("ldw4033", "123");
		
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Assert.assertEquals(true, currentUser.isAuthenticated()); //断言用户已经登录
		currentUser.logout();
		
		
		
	}
	@Test
	public void testJDBCRealm(){
		log.info("testJDBCRealm");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		SecurityManager securityManagey=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManagey);
		Subject currentUser=SecurityUtils.getSubject();
		
		
//		UsernamePasswordToken token =new  UsernamePasswordToken("zhangsan", "123");
		UsernamePasswordToken token =new  UsernamePasswordToken("zhang", "1234");
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Assert.assertEquals(true, currentUser.isAuthenticated()); //断言用户已经登录
		currentUser.logout();
		
		
		
	}
	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		log.info("testAllSuccessfulStrategyWithSuccess");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-all-success.ini");
		SecurityManager securityManagey=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManagey);
		Subject currentUser=SecurityUtils.getSubject();
		
		
		UsernamePasswordToken token =new  UsernamePasswordToken("zhangsan", "123");
//		UsernamePasswordToken token =new  UsernamePasswordToken("zhang", "1234");
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Assert.assertEquals(true, currentUser.isAuthenticated()); //断言用户已经登录
		
		//得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = currentUser.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
		
        currentUser.logout();
	}
	

}
