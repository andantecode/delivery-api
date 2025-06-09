package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 1. Request를 읽으면 실제로 뒤에 못 읽으므로 Wrapper class로 형변환
        var req = new ContentCachingRequestWrapper( (HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper( (HttpServletResponse) response);

        // TODO 가장 좋은 것은 Header, Body 정보를 찍어주는 것 (별도 Caching class를 만들어서 처리해야 함)

        // 2. Wrapping 된 객체를 넘겨줌 (뒷단에 Mapper, Controller, ...)
        chain.doFilter(req, res);

        // 3. logging
        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            // authorization-token: ???, user-agent: ???
            headerValues.append("[").append(headerKey).append(": ").append(headerValue).append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());

        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info("Request> URI: {}, METHOD: {}, HEADER: {}, BODY: {}", uri, method, headerValues, requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues.append("[").append(headerKey).append(": ").append(headerValue).append("] ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("Response< URI: {}, METHOD: {}, HEADER: {}, BODY: {}", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse(); // responseBody 읽은 값 다시 copy (없으면, responseBody 비어있음)
    }

}
