package com.Zuul_Service.Filter.pre;

import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter{
	//should filter or not
	@Override
	public boolean shouldFilter() {
		 return false;
	}

	//what should it do
	@Override
	public Object run() throws ZuulException {
		 
		        return null;
	}

	//post
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
