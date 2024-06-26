package org.dkcorp.vktesttask.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.dkcorp.vktesttask.service.AuditService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditService auditService;

    private void record(String status) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous";
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        String requestParams = request.getQueryString();
        auditService.recordAudit(username, requestUri, method, requestParams, status);

    }

    @Before("execution(* org.dkcorp.vktesttask.controller.*.*(..))")
    public void before() {
        record("IN_PROGRESS");
    }

    @AfterReturning(pointcut = "execution(* org.dkcorp.vktesttask.controller.*.*(..))")
    public void afterReturning() {
        record("SUCCESS");
    }
}
