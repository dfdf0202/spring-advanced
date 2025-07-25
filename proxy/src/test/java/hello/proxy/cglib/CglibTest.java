package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService service = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(service));
        ConcreteService proxy = (ConcreteService) enhancer.create(); // ConcreteService 를 상속 받아 프록시를 생성함
        log.info("targetClass={}", service.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }

    @Test
    void cglib2() {

        ServiceInterface service = new ServiceImpl();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceInterface.class);
        enhancer.setCallback(new TimeMethodInterceptor(service));
        ServiceInterface proxy = (ServiceInterface) enhancer.create(); // ServiceInterface 를 상속 받아 프록시를 생성함
        log.info("targetClass={}", service.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();
        proxy.find();
    }

}
