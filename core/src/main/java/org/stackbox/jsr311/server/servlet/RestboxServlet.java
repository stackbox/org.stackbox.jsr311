package org.stackbox.jsr311.server.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestboxServlet extends HttpServlet {
	
	RestboxServletDispatcher restboxDispathcer;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			restboxDispathcer = new RestboxServletDispatcher(config);
		} catch (IOException e) {
			//error
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			restboxDispathcer.service(request.getMethod(), request.getPathInfo(), request, response);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
