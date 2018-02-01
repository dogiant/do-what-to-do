package com.dogiant.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.dogiant.api.CmsDataService;

@Configuration
public class ExportServiceConfig extends DubboBaseConfig {
    
    @Bean
    public ServiceBean<CmsDataService> cmsDataServiceExport(CmsDataService cmsDataService) {
        ServiceBean<CmsDataService> serviceBean = new ServiceBean<CmsDataService>();
        serviceBean.setProxy("javassist");
        serviceBean.setVersion("cms-1.0");
        serviceBean.setInterface(CmsDataService.class.getName());
        serviceBean.setRef(cmsDataService);
        serviceBean.setTimeout(5000);
        serviceBean.setRetries(3);
        return serviceBean;
    }

}