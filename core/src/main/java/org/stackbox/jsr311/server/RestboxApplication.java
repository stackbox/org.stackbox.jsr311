package org.stackbox.jsr311.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.stackbox.jsr311.model.Resource;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class RestboxApplication extends Application {
	
	
	
	private Set <Class<?>> resouces = Collections.EMPTY_SET;
	
	public void packages(String packageName) throws IOException {
		
		ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
		ImmutableSet<ClassInfo> classinfos = null;
		
		if(null != packageName && packageName.length() > 0) {
			classinfos = classpath.getTopLevelClassesRecursive(packageName);
		} else {
			classinfos = classpath.getAllClasses();
		}
		
		for(ClassInfo classinfo : classinfos) {
			resouces.add(classinfo.load());
		}
		
		
	}
	
	/**
	 * TODO: generate wadl
	 */	
	
}
