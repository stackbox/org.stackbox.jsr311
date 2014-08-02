package org.stackbox.jsr311.server;

public class ContextBeanFactory implements BeanFactory {

	private static BeanFactory factory = null;
	static {
		factory = new BeanFactory() {
			
		};
	}
	
	public static <T> T getBean(Class<T> clazz) {
		
		T instance = null;
		
		try {
			instance = (T) factory.getInstance(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instance;
	}
}
