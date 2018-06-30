package com.tievoli.sbfuse.framework.xss;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 跨站请求防范.
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * The default behavior of this method is to return getHeader(String name)
     * on the wrapped request object.
     *
     * @param name
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return HtmlUtils.htmlEscape(value);
    }

    /**
     * The default behavior of this method is to return getParameter(String
     * name) on the wrapped request object.
     *
     * @param name
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return HtmlUtils.htmlEscape(value);
    }

    /**
     * The default behavior of this method is to return
     * getParameterValues(String name) on the wrapped request object.
     *
     * @param name
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapseValues[i] = HtmlUtils.htmlEscape(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }
}
