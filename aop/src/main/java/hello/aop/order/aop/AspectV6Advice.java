package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    /*@Around("hello.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before()
            log.info("[transaction start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();

            //@AfterReturning
            log.info("[transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {

            //@AfterThrowing
            log.info("[transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[resource release] {}", joinPoint.getSignature());
        }
    }*/

    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) throws Throwable {
        // result 의 결과를 수정할 순 없음
        log.info("[return] {} result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex);
    }

    @After(value = "hello.aop.order.aop.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
