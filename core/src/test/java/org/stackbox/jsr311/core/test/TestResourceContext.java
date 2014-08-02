package org.stackbox.jsr311.core.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import org.junit.Before;
import org.junit.Test;
import org.stackbox.jsr311.core.test.resource.MyResource;
import org.stackbox.jsr311.model.ResourceMethod;
import org.stackbox.jsr311.server.ResourceContext;


public class TestResourceContext {

	private HttpServletRequest mockRequest1 = mock(HttpServletRequest.class);
	private HttpServletRequest mockRequest2 = mock(HttpServletRequest.class);
	
	private ResourceMethod mockSayResourceMethod = mock(ResourceMethod.class);
	private ResourceMethod mockSayHelloResourceMethod = mock(ResourceMethod.class);
	
	@Before
	public void setUp() throws NoSuchMethodException, SecurityException {
		
		when(mockRequest1.getPathInfo()).thenReturn("/path");
		
		when(mockRequest2.getParameter("name")).thenReturn("SRK.Lyu");
		when(mockRequest2.getPathInfo()).thenReturn("/path/say");
		
		Method sayMethod = MyResource.class.getMethod("say", new Class[]{});
		Method sayHelloMethod = MyResource.class.getMethod("sayHello", new Class[]{String.class});
		
		when(mockSayResourceMethod.getReflectMethod()).thenReturn(sayMethod);
		when(mockSayHelloResourceMethod.getReflectMethod()).thenReturn(sayHelloMethod);
	}
	
	
	@Test
	public void testSay() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		assertEquals("hello world", ResourceContext.invokeMethod(mockRequest1, mockSayResourceMethod));
	}
	
	@Test
	public void testSayHello() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException  {
		assertEquals("hello SRK.Lyu", ResourceContext.invokeMethod(mockRequest2, mockSayHelloResourceMethod));
	}
	

}
