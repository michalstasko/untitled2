/**
 * Copyright (c) 2013 Cleverlance Enterprise Solutions a.s.
 * http://www.cleverlance.com
 * All Rights Reserved.
 */

package untitled2;

import com.vaadin.ui.Notification;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author tsenko
 */
@Aspect
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionAspect {

    @AfterThrowing(
            pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)",
            //pointcut = "execution(* *(..))",
            throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + e.getMessage());
        //e.printStackTrace();
        System.out.println("******");

        //Notification.show("exception");
    }
}
