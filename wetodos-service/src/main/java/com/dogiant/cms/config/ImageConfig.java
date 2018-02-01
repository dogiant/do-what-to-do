package com.dogiant.cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:config/image.properties")
public class ImageConfig {
	
	public static String fileHost;
	
    public static String imageUrlPrefix;
	
	public static String imageLocalPathPrefix;
	
	@Value("${image.url.file_host}")
	public void setFileHost(String fileHost) {
		ImageConfig.fileHost = fileHost;
	}

	@Value("${image.url.prefix}")
	public void setImageUrlPrefix(String imageUrlPrefix) {
		ImageConfig.imageUrlPrefix = imageUrlPrefix;
	}

	@Value("${image.local_path.prefix}")
	public void setImageLocalPathPrefix(String imageLocalPathPrefix) {
		ImageConfig.imageLocalPathPrefix = imageLocalPathPrefix;
	}

}
