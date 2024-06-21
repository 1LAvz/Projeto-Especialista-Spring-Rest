package com.algaworks.algafood.core.validation;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AcceptHeaderValidationFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String acceptHeader = httpRequest.getHeader("Accept");
        if (acceptHeader != null && !isValidMediaType(acceptHeader)) {
            httpResponse.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Invalid 'Accept' header. Please provide a valid media type.");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

    private boolean isValidMediaType(String acceptHeader) {
        try {
            MediaType.parseMediaTypes(acceptHeader);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}