package org.stackbox.jsr311.model;

public class Resource {

	private String resourceName;
	private Class resourceClazz;
	private Object instance;
	
	private String path;


	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Class getResourceClazz() {
		return resourceClazz;
	}

	public void setResourceClazz(Class resourceClazz) {
		this.resourceClazz = resourceClazz;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
