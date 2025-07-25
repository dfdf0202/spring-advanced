package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy Start");
        var startTime = System.currentTimeMillis();

        //Object result = method.invoke(target, args);
        Object result = methodProxy.invoke(target, args);

        var endTime = System.currentTimeMillis();
        var resultTime = endTime - startTime;

        log.info("TimeProxy End Time: {}", resultTime);

        return result;
    }
}
