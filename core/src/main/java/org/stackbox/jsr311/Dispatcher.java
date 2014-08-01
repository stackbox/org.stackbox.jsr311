package org.stackbox.jsr311;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Dispatcher {

	public void invoke(HttpServletRequest req, HttpServletResponse resp);
}
