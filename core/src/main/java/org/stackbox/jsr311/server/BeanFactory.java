package org.stackbox.jsr311.server;

import javax.servlet.http.HttpServletRequest;

public interface BeanFactory {
	
	default Object getInstance(Class<?> clazz) throws Exception {
        return clazz.newInstance();
    }

    default Object getInstance(Class<?> clazz, HttpServletRequest request)
            throws Exception {
        return clazz.newInstance();
    }
}
