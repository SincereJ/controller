package cws.console.core;

public class SpringBeanInvoker {
	
	public static Object getBean(String beanName) {
		return ContextHolder.getApplicationContext().getBean(beanName);
	}

}
