package com.cn.filter;

import com.cn.util.BaseContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:登录过滤器
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1-获取本次请求的URI
        String requestURI = request.getRequestURI();
        // 定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employees/login",
                "/employees/logout",
                "/static/backend/**"
        };

        // 2-检查登录状态(请求是否需要处理)
        boolean check = check(urls, requestURI);

        // 3-不需要处理-直接放行
        if (check) {
            filterChain.doFilter(request, response);
            return;
        }
        // 4-判断是否登录--根据session
        if (request.getSession().getAttribute("employee") != null) {
            // 获取当前用户的ID
            Long employeeId = (Long) request.getSession().getAttribute("employee");
            log.info("登录用户ID:" + employeeId);

            // 将用户ID存放到ThreadLocal中
            BaseContextUtils.setCurrentId(employeeId);

            // 输出当前用户的线程ID
            long id = Thread.currentThread().getId();
            log.info("线程ID{}" + id);

            filterChain.doFilter(request, response);
            return;
        }
        // 5-不符合放行请求
        log.info("执行跳转");
        request.getRequestDispatcher("/static/backend/page/login/login.html").forward(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 路径匹配，检查请求是否需要放行
     *
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
