package org.stackbox.jsr311.util;

import java.lang.annotation.Annotation;

public class AnnotationResolver {
	public static Class<?> getClassWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
		
		if(clazz.isAnnotationPresent(annotation)) {
			return clazz;
		}
	
		for(Class<?> intf : clazz.getInterfaces()) {
			if(intf.isAnnotationPresent(annotation)) {
				return intf;
			}
		}
		
		Class<?> superClazz = clazz.getSuperclass();
		if(superClazz != Object.class && superClazz != null) {
			return getClassWithAnnotation(superClazz, annotation);
		}

		return null;
	}
}
