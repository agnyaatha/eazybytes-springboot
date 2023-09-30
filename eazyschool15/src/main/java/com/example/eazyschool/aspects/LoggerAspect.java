package com.example.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around( "execution(* com.example.eazyschool..*.*(..))" )
    public Object log( ProceedingJoinPoint joinPoint ) throws Throwable{
        log.info(joinPoint.getSignature().toString() + " -> method execution start");
        Instant start = Instant.now();
        Object retunObject = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between( start, finish ).toMillis();
        log.info( "Time elapsed = " + timeElapsed );
        log.info(joinPoint.getSignature().toString() + " -> method execution end");
        return retunObject;
    }

    @AfterThrowing(value = "execution(* com.example.eazyschool.*.*(..))", throwing = "exception")
    public void logException( JoinPoint joinPoint, Exception exception ){
        log.error( joinPoint.getSignature().toString()+ " -> An exception occured due to : "+ exception.getMessage() );
    }
}
