package cws.console.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ContextHolder {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		ContextHolder.applicationContext = applicationContext;
	}
	
}
