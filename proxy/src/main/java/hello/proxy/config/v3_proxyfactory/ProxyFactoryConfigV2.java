package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 target = new OrderRepositoryV2();
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 target = new OrderServiceV2(orderRepository(logTrace));
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();

        return proxy;
    }

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        OrderControllerV2 target = new OrderControllerV2(orderService(logTrace));
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();

        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        // pointCut : 부가 기능(어드바이스) 의 여부를 어디에 적용할지 구분
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // advice : 프록시가 호출하는 부가 기능
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        // Advisor : 포인트컷 + 어드바이스
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
    
}
