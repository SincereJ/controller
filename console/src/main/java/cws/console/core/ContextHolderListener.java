package cws.console.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextHolderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ContextHolder.setApplicationContext(ac);
	}
}
