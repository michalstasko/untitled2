/**
 * Copyright (c) 2013 Cleverlance Enterprise Solutions a.s.
 * http://www.cleverlance.com
 * All Rights Reserved.
 */

package untitled2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author tsenko
 */
@Aspect
public class LoggingAspect {

    @Before("execution(* untitled2.BusinessLogic.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

}
