package com.gensoft.servicezuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @ Author     ：chenhl01.
 * @ Date       ：Created in 20:58 2018/7/9
 * @ Description：zuul过滤器
 * @ Modified By：
 */
@Component
public class MyFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(String.format("%s >>> %s" , request.getMethod(), request.getRequestURL().toString()));
		if (request.getRequestURI() != null
				&& (request.getRequestURI().contains("swagger") ||request.getRequestURI().contains("api-docs") || request.getRequestURI().contains("configuration"))) {
			return true;
		}
//		String accessToken = request.getHeader("accessToken");
//		if (accessToken == null) {
//			try {
//				ctx.setSendZuulResponse(false);
//				ctx.setResponseStatusCode(401);
//				ctx.getResponse().getWriter().write("accessToken is empty");
//			} catch (Exception e) {
//			}
//			return null;
//		}
		return null;
	}
}
