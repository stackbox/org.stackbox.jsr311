package org.stackbox.jsr311.core.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.stackbox.jsr311.model.ResourceMethod;
import org.stackbox.jsr311.server.servlet.RestboxServletDispatcher;

public class TestDispatcher {

	private static String PACKAGE_TO_SCAN = "org.stackbox.jsr311.core.test.resource";
	
	private ServletConfig mockServletConfig = mock(ServletConfig.class);
	private HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	private HttpServletResponse mockResponse = mock(HttpServletResponse.class);
	
	private StringWriter sw = new StringWriter();
	private  PrintWriter printerWriter = new  PrintWriter(sw);
	
	private RestboxServletDispatcher dispatcher = null;
	
	@Before
	public void setUp() throws IOException {
		when(mockServletConfig.getInitParameter("packageToScan")).thenReturn(PACKAGE_TO_SCAN);
		when(mockRequest.getMethod()).thenReturn("GET");
		when(mockRequest.getPathInfo()).thenReturn("/path2/hi");
		when(mockRequest.getParameter("name")).thenReturn("Stackbox");
		when(mockResponse.getWriter()).thenReturn(printerWriter);
		dispatcher = new RestboxServletDispatcher(mockServletConfig);
	}
	
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		dispatcher.service(mockRequest.getMethod(), mockRequest.getPathInfo(), mockRequest, mockResponse);
		
		/**
		 * TODO: response.getHeader失效,可能是由于mockito库的原因
		 */
		//assertEquals("text/plain", mockResponse.getContentType());
		assertEquals("Hi Stackbox", sw.getBuffer().toString());
	}
	
	@Test
	public void testMethodsNumber() {
		assertEquals(4, dispatcher.getContext().getMethodMap().size());	
	}

}
