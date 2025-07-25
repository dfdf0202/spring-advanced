package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy Start");
        var startTime = System.currentTimeMillis();

        //Object result = method.invoke(target, args);
        Object result = invocation.proceed();

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;

        log.info("TimeProxy End Time: {}", resultTime);

        return result;
    }
}
