package org.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.common.reflect.ClassPath;

public class TestSS {

	@Test
	public void test() throws IOException {
		ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
		
		System.out.println(classpath.getAllClasses());
	}

}
