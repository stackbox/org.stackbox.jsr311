package org.stackbox.jsr311.server.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.stackbox.jsr311.model.ResourceMethod;
import org.stackbox.jsr311.server.ResourceContext;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;


public class RestboxServletDispatcher {

	private ResourceContext context;
	
	public RestboxServletDispatcher(ServletConfig servletConfig) throws IOException {
		
		
		Set<Class<?>> resourceClazz = new HashSet<Class<?>>();
		ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
		ImmutableSet<ClassInfo> classinfos = classpath.getTopLevelClassesRecursive(servletConfig.getInitParameter("packageToScan"));
		
		for(ClassInfo classinfo : classinfos) {
			Class resourceClass = classinfo.load();
			if(resourceClass.isAnnotationPresent(Path.class)) {
				resourceClazz.add(resourceClass);
			}
		}
	
		
		context = new ResourceContext(resourceClazz);
	}
	
	public void service(String method, String absolutePath, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		System.out.println(context.getMethodMap().size());
		
		
		ResourceMethod resourceMethod = context.getMethod(method, absolutePath);
		
		if(null != resourceMethod) {
			
			String resObj = (String)ResourceContext.invokeMethod(request, resourceMethod);

			response.setContentType("text/plain;charset=UTF-8");
			
			
			response.getWriter().write(resObj);
			response.getWriter().flush();
			response.getWriter().close();
			
			
		} else {
			//return 404
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		
	}

	public ResourceContext getContext() {
		return context;
	}

	public void setContext(ResourceContext context) {
		this.context = context;
	}
	
	
}
