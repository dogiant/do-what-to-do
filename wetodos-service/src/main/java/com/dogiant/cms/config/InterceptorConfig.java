package com.dogiant.cms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dogiant.cms.web.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		logger.info("Interceptor Config");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/assets/**",
				"/login**","/api/login**", "/**/*.html");
		super.addInterceptors(registry);
	}

	 /**
	 * 资源处理器
	 *
	 * @param registry
	 */
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 logger.info("addResourceHandlers");
//	 registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//	 registry.addResourceHandler("/swagger-resources/**").addResourceLocations("classpath:/META-INF/swagger-resources/");
//	 registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	 registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
	 registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/");
	 super.addResourceHandlers(registry);
	 }
}
