package org.stackbox.jsr311.server;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.stackbox.jsr311.model.ResourceMethod;
import org.stackbox.jsr311.model.Resource;

public class ResourceContext {

	private Map<String, ResourceMethod> methodMap= new LinkedHashMap<String, ResourceMethod>();

	/**
	 * TODO: 使用工厂方法,单例模式进行重构
	 * @param clazzes
	 */
	public ResourceContext(Set<Class<?>> clazzes) {

		Resource resource = new Resource();
		Set<ResourceMethod> resourceMethods = Collections.EMPTY_SET;
		
		for(Class<?> clazz : clazzes) {
			Path p = clazz.getDeclaredAnnotation(Path.class);
			String rootPath = p.value();
			
			Method[] methods = clazz.getDeclaredMethods();
			
			/**
			 * TODO: 使用builder模式构造resourcemethod实例
			 */
			
			for(Method method : methods) {
				
				ResourceMethod.Data data = new ResourceMethod.Data();
				if(!method.isAnnotationPresent(Path.class)) {
					data.setAbsoluteurl(rootPath);
				} else {
					data.setAbsoluteurl(rootPath + method.getDeclaredAnnotation(Path.class).value());
				}
				
				/**
				 * TODO: 处理GET POST HEAD DELETE PUT的情况
				 */
				data.setMethod("");
				
				ResourceMethod resourceMethod = new ResourceMethod();
				resourceMethod.setData(data);
				resourceMethod.setReflectMethod(method);
				
				
				if(!methodMap.containsKey(data.toString())) {
					methodMap.put(data.toString(), resourceMethod);
				}
				
			}
			
		}

	}

	/**
	 * TODO: 需要对httpMethod进行处理,目前只是根据path进行处理
	 * @param httpMethod
	 * @param path
	 * @return
	 */
	
	public ResourceMethod getMethod(String httpMethod, String path) {
		return methodMap.get(path);
	}

	public static Object invokeMethod(HttpServletRequest request,
			ResourceMethod resourceMethod) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException {

		Method method = resourceMethod.getReflectMethod();
		Object targetObj = ContextBeanFactory.getBean(method
				.getDeclaringClass());

		List<Object> argsList = new ArrayList<Object>();
		
		
		Parameter[] parameters = method.getParameters();

		if (null != parameters && parameters.length > 0) {
			for (Parameter para : parameters) {
				java.lang.annotation.Annotation[] annos = para.getAnnotations();
				
				boolean hasAddedFlg = false;
				if (null != annos && annos.length > 0) {
					for (Annotation anno : annos) {
						if (anno.annotationType().equals(QueryParam.class)) {
							QueryParam queryparam = (QueryParam) anno;

							Object param = request.getParameter(queryparam
									.value());
							if (null != param) {
								argsList.add(param);
							} else {
								argsList.add(para.getClass().newInstance());
							}
							hasAddedFlg = true;
						} 
					}
				}
				
				if(!hasAddedFlg) {
					argsList.add(para.getClass().newInstance());
				}
			}
		}
		
		Object ret = method.invoke(targetObj, argsList.toArray());
		return ret;
	}

	public Map<String, ResourceMethod> getMethodMap() {
		return methodMap;
	}

	public void setMethodMap(Map<String, ResourceMethod> methodMap) {
		this.methodMap = methodMap;
	}

}
