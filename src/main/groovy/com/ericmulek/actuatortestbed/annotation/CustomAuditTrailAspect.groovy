package com.ericmulek.actuatortestbed.annotation


import groovy.util.logging.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CustomAuditTrail {
}

@Aspect
@Component
@Slf4j
@Order(1)
class CustomAuditTrailAspect {

    @Autowired
    HttpServletRequest request
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher

    @Around('@annotation(CustomAuditTrail)')
    void around(ProceedingJoinPoint joinPoint) {
        def auditObject = new AuditEvent('', '', [:])
        applicationEventPublisher.publishEvent(auditObject)
        joinPoint.proceed()
    }
}
