package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepository(logTrace));
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();

        return proxy;
    }

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1 target = new OrderControllerV1Impl(orderService(logTrace));
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvisor(getAdvisor(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();

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
