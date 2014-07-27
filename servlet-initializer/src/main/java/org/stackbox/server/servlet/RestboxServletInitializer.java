package org.stackbox.server.servlet;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.ws.rs.core.Application;

@HandlesTypes({Application.class, Path.class})
public class RestboxServletInitializer implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> clazzes, ServletContext sc)
			throws ServletException {
		
		
	}
	
	
	public static Set<Class<? extends Application>> filterApplicationClass(Set<Class<?>> clazzes) {
		LinkedHashSet<Class<? extends Application>> res = new LinkedHashSet<Class <? extends Application>>();
		for(Class<?> c : clazzes) {
			if(c != Application.class && Application.class.isAssignableFrom(c)) {
				res.add(c.asSubclass(Application.class));
			}
		}
		return null;
	}

}
