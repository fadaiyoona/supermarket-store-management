package com.supermarket.store.management.api.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题的过滤器
 * 前后端分离项目，存在跨域访问的问题，因此需要加一个过滤器，在请求头上增加处理跨域相关的请求头
 */
@Component
public class CorsFilter implements Filter {

    /**
     * 过滤请求
     *
     * @param req http请求
     * @param res http返回结果
     * @param chain 执行链
     * @throws IOException IO异常
     * @throws ServletException servlet异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 获取http请求和返回对象
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest reqs = (HttpServletRequest) req;
        // 在返回结果上增加处理跨域相关的请求头
        response.setHeader("Access-Control-Allow-Origin", reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        // 执行请求
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
