package com.nagp.assgnmnt.mservices.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Interceptor
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter
{
    Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);

    @Override
    public String filterType()
    {
        return "post";
    }

    @Override
    public int filterOrder()
    {
        return 1;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run() throws ZuulException
    {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request received: {} {}", request, request.getRequestURI());
        return null;
    }
}
