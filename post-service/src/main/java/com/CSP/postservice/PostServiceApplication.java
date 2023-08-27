package com.CSP.postservice;

import com.CSP.postservice.filters.LoginCheckFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterUrl()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		filterRegistrationBean.addUrlPatterns("/post/addPost");
		filterRegistrationBean.addUrlPatterns("/post/like/*");
		filterRegistrationBean.addUrlPatterns("/post/editPost");
		filterRegistrationBean.addUrlPatterns("/post/deletePost/*");
		filterRegistrationBean.addUrlPatterns("/post/myPosts");
		return filterRegistrationBean;
	}
}
