// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.proxy;

import java.lang.reflect.*;

import net.sf.cglib.proxy.*;

/**
 * An intercptor that seamlessly manages invocations on both a native Java proxy and a cglib one.
 * @author Mario Fusco
 */
public abstract class InvocationInterceptor implements MethodInterceptor, java.lang.reflect.InvocationHandler {

    /**
     * A marker interface for an Interceptor that does nothing
     */
	public interface VoidInterceptor { }

    /**
     * A dummy InvocationInterceptor that just ignores any invocation on it
     */
	public static final InvocationInterceptor VOID = new InvocationInterceptor() {
        /**
         * {@inheritDoc}
         */
		public Object invoke(Object proxy, Method method, Object[] args) {
			return (method.getName().equals("equals") && args != null && args.length == 1) ? isVoidProxy(args[0]) : null;
		}

        private boolean isVoidProxy(Object object) {
            return object instanceof VoidInterceptor;
        }
	};
	
    /**
     * {@inheritDoc}
     */
	public final Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		return invoke(proxy, method, args);
	}
}