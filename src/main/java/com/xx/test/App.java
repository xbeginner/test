package com.xx.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.xx.test.Model.UserInfo;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
public class App {
	
	@RequestMapping("/")
	public String index(){
		return "Hello Spring Boot";
	}
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
 
	
	@Autowired
	private Environment env;
	
	//destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
			@Bean(destroyMethod =  "close")
			public  DataSource dataSource() {
				DruidDataSource dataSource = new DruidDataSource();
				dataSource.setUrl(env.getProperty("spring.datasource.url"));
				dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
				dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
				dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
				dataSource.setInitialSize(2);//初始化时建立物理连接的个数
				dataSource.setMaxActive(20);//最大连接池数量
				dataSource.setMinIdle(0);//最小连接池数量
				dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
				dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
				dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
				dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
				dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
				return dataSource;
			}
			
			
			
//			@Configuration 
//			static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
//				public void addInterceptors(InterceptorRegistry registry) {
//					registry.addInterceptor(new HandlerInterceptorAdapter() { 
//					@Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
//								if(request.getServletPath().contains("login")||request.getServletPath().contains("Login")){
//									  return true; 
//								}else{
//									System.out.println(request.getSession().getAttribute("currentUserInfo"));
//									UserInfo userInfo = (UserInfo)request.getSession().getAttribute("currentUserInfo");
//									System.out.println(userInfo);
//									  if(request.getSession().getAttribute("currentUserInfo")!=null){
//										     return true;
//									  }else{
//										  response.sendRedirect("/login");
//										  return false;
//									  }
//								}	
//							}
//						})
//					 .addPathPatterns("/**"); 
//					} 
//				}
 
}