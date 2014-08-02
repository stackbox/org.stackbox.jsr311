package org.stackbox.jsr311.model;

import java.lang.reflect.Method;
import java.util.List;

import javax.ws.rs.core.MediaType;

public class ResourceMethod {

	private String httpMethod;
	private List <MediaType> consumeTypes;
	private List <MediaType> produceTypes;
	private String path;
	private Resource parent;
	private List <Class<?>> argsClass;
	private List <Object> argsParams;
	
	private Method reflectMethod;
	private ResourceMethod.Data data;

	public static class Data {
		private String method;
		private String absoluteurl;
	
		public Data() {
	
		}
		
		public Data(String method, String absoluteurl) {
			super();
			this.method = method;
			this.absoluteurl = absoluteurl;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getAbsoluteurl() {
			return absoluteurl;
		}

		public void setAbsoluteurl(String absoluteurl) {
			this.absoluteurl = absoluteurl;
		}

		@Override
		public String toString() {
			return method + "" + absoluteurl;
		}
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public List<MediaType> getConsumeTypes() {
		return consumeTypes;
	}
	public void setConsumeTypes(List<MediaType> consumeTypes) {
		this.consumeTypes = consumeTypes;
	}
	public List<MediaType> getProduceTypes() {
		return produceTypes;
	}
	public void setProduceTypes(List<MediaType> produceTypes) {
		this.produceTypes = produceTypes;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	public List<Class<?>> getArgsClass() {
		return argsClass;
	}
	public void setArgsClass(List<Class<?>> argsClass) {
		this.argsClass = argsClass;
	}
	public List<Object> getArgsParams() {
		return argsParams;
	}
	public void setArgsParams(List<Object> argsParams) {
		this.argsParams = argsParams;
	}
	public Method getReflectMethod() {
		return reflectMethod;
	}
	public void setReflectMethod(Method reflectMethod) {
		this.reflectMethod = reflectMethod;
	}
	public ResourceMethod.Data getData() {
		return data;
	}
	public void setData(ResourceMethod.Data data) {
		this.data = data;
	}
}
