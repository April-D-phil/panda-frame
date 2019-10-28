package com.phil.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidDBConfig {

	@Primary
	@Bean(name = "bladeDataSource")
	@Qualifier("bladeDataSource")
	@ConfigurationProperties("spring.datasource.blade")
	public DataSource dataSourceOne(){
	    return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(name = "pandaDataSource")
	@Qualifier("pandaDataSource")
	@ConfigurationProperties("spring.datasource.panda")
	public DataSource dataSourceTwo(){
	    return DruidDataSourceBuilder.create().build();
	}
 
	@Value("${druid.username}")
    private String username;
    
    @Value("${druid.password}")
    private String password;
	
    //配置一个servlet，用于管理druid数据源信息
	@Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
    	ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
    	servletRegistrationBean.addInitParameter("loginUsername", username);
    	servletRegistrationBean.addInitParameter("loginPassword", password);
    	servletRegistrationBean.addInitParameter("resetEnable", "false");
    	return servletRegistrationBean;
    }
    
	//配置一个filter，用于管理过滤策略
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
    	FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
    	filterRegistrationBean.addUrlPatterns("/*");
    	filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    	return filterRegistrationBean;
    }
	

	
}
