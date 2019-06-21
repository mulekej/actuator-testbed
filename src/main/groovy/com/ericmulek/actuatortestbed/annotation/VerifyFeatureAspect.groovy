package com.ericmulek.actuatortestbed.annotation

import com.ericmulek.actuatortestbed.service.MyAuthService
import groovy.util.logging.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpServletRequest
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.lang.reflect.Method

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface VerifyFeature {
    String feature() default ''
}

@Aspect
@Component
@Slf4j
@Order(0)
class VerifyFeatureAspect {

    @Autowired
    MyAuthService authService
    @Autowired
    HttpServletRequest request

    //todo figure out how to included the annotation in the method args
    @Before('@annotation(VerifyFeature)')
    void doThingHere(VerifyFeature verifyFeature) {
        String feature = verifyFeature.feature()
        log.info("Step 3: VerifyFeature feature=$feature")
        if(!authService.verifyFeature(feature)) {
            throw new ResponseStatusException(HttpStatus.OK, 'Feature Not Excepted')
        }
    }
}
