package com.dogiant.cms.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.dogiant.cms.web.interceptor.AdminLoginInterceptor;
import com.dogiant.cms.web.interceptor.UserLoginInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(WebMvcConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		logger.info("Interceptor Config");
		registry.addInterceptor(new AdminLoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**", "/login", "/login**",
						"/api/login**", "/**/*.html", "/hello", "/error", "/todos/data/api/**");
		registry.addInterceptor(new UserLoginInterceptor())
				.addPathPatterns("/todos/data/api/getDailyBanner");
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
		// registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		// registry.addResourceHandler("/swagger-resources/**").addResourceLocations("classpath:/META-INF/swagger-resources/");
		// registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/assets/**").addResourceLocations(
				"classpath:/static/assets/");
		registry.addResourceHandler("/index.html").addResourceLocations(
				"classpath:/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		//定义一个转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		//添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
		            SerializerFeature.WriteNullStringAsEmpty);

		//在转换器中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);

		//将转换器添加到converters中
		converters.add(fastConverter);

	}


}
