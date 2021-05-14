package eureka_client.demo;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info( "## init");
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
        logger.info("## url={} --- STARTING!", url);

        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info( String.format("## url=%s --- ENDED! cost=%s", url, (System.currentTimeMillis() - start)));
    }

    @Override
    public void destroy() {

    }
}