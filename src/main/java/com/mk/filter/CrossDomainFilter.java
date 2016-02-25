package com.mk.filter;

import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CrossDomainFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);

    private List<String> crossDomainPatterns;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        String url = ((HttpServletRequest) request).getRequestURI();
        if (this.matchCrossDomainPatterns(request.getServletContext().getContextPath(), url)) {
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "POST");
            ((HttpServletResponse)response).setHeader("Access-Control-Max-Age", "1000");
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        String initcrossDomainParameter = arg0.getInitParameter("crossDomainPatterns");
        this.crossDomainPatterns = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(initcrossDomainParameter);
    }

    /**
     * @param contextpath
     * @param url
     * @return
     */
    public boolean matchCrossDomainPatterns(String contextpath, String url) {
        boolean isExist = false;
        for (String prefix : this.crossDomainPatterns) {
            if ("*".equals(prefix)) {
                return true;
            }
            if (url.startsWith(contextpath + prefix)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
