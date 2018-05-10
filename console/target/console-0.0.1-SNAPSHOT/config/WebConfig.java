package com.linkage.bss.crm.ws.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.linkage.bss.crm.ws.console.web")
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
	}
	
	/*@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter =  new RequestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setMessageConverters(null);
		requestMappingHandlerAdapter.setReturnValueHandlers(null);
		//requestMappingHandlerAdapter
		
		
		return requestMappingHandlerAdapter;
	}*/
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {		
		configurer.enable();
	}
	
}
