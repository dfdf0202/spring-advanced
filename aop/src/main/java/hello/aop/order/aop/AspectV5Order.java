package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV5Order {

    /**
     * Aspect 의 순서 (@Order) 는 @Aspect 단위로 적용 시킬수 있음
     * 그렇기 떄문에 클래스 단위로 나눠야 함
     */

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.PointCuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        @Around("hello.aop.order.aop.PointCuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[transaction start] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[transaction commit] {}", joinPoint.getSignature());

                return result;
            } catch (Exception e) {
                log.info("[transaction rollback] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[resource release] {}", joinPoint.getSignature());
            }
        }
    }

}
