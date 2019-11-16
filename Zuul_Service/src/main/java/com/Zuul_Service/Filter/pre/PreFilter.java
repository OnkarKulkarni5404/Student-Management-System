package com.Zuul_Service.Filter.pre;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter{
	//should filter or not
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//what should it do
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	  //  System.out.println(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    return null;
	}

	//pre
	@Override
	public String filterType() {
		return "pre";
	}

	//order in which this filter should run relative to other filters
	//this filter runs ar Order 1
	@Override
	public int filterOrder() {
		return 1;
	}
		
}
