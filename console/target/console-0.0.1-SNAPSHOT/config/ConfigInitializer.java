package com.linkage.bss.crm.ws.console.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	
	/*public void onStartup(ServletContext servletContext) throws ServletException {
		PropertyPlaceholderConfigurer pphc = new PropertyPlaceholderConfigurer();		
	}*/
	
	
}
