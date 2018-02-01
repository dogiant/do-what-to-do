package com.dogiant.cms.domain.admin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 权限注解
 * 
 * @author dogiant
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemPrivilege {
	String model();
	String privilegeValue();
}
