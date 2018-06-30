package com.tievoli.sbfuse.framework.xss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤器.
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);

    /**
     * Called by the web container to indicate to a xss that it is being
     * placed into service. The servlet container calls the init method exactly
     * once after instantiating the xss. The init method must complete
     * successfully before the xss is asked to do any filtering work.
     * <p>
     * The web container cannot place the xss into service if the init method
     * either:
     * <ul>
     * <li>Throws a ServletException</li>
     * <li>Does not return within a time period defined by the web
     * container</li>
     * </ul>
     *
     * @param filterConfig The configuration information associated with the
     *                     xss instance being initialised
     * @throws ServletException if the initialisation fails
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init XssFilter...");
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following
     * pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to
     * xss content or headers for input filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to
     * xss content or headers for output filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using
     * the FilterChain object (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the
     * next entity in the xss chain to block the request processing<br>
     * 5. Directly set headers on the response after invocation of the next
     * entity in the xss chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next xss in the chain for this
     *                 xss to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this xss's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest =
                new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    /**
     * Called by the web container to indicate to a xss that it is being
     * taken out of service. This method is only called once all threads within
     * the xss's doFilter method have exited or after a timeout period has
     * passed. After the web container calls this method, it will not call the
     * doFilter method again on this instance of the xss. <br>
     * <br>
     *
     * This method gives the xss an opportunity to clean up any resources
     * that are being held (for example, memory, file handles, threads) and make
     * sure that any persistent state is synchronized with the xss's current
     * state in memory.
     */
    @Override
    public void destroy() {
        logger.info("destroy XssFilter...");
    }
}
