package cn.zyf.videoserver.videoserver.config;


import cn.zyf.videoserver.utils.LogUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogFilter implements Filter {
    private static final String TAG = "LogFilter";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LogUtil.d(TAG, "## init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url = "";
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest r = (HttpServletRequest) servletRequest;
            url = r.getServletPath();
        }
        if(url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg")){
            // 图片的log 就不记录了
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        LogUtil.d(TAG, "## url=%s --- STARTING!", url);

        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        LogUtil.d(TAG, String.format("## url=%s --- ENDED! cost=%s", url, (System.currentTimeMillis() - start)));
        LogUtil.newLine();
    }

    @Override
    public void destroy() {

    }
}