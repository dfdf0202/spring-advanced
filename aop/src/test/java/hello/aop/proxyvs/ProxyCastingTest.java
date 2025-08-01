package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);    // JDK 동적 프록시

        // 프록시를 인터페이스로
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // 프록시는 MemberService 를 구현 했기 때문에 구현체를 알 수가 없음
        //MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);    // CGLIB 동적 프록시

        // 프록시를 인터페이스로
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // CGLIB 프록시는 구체 클래스를 구현하기 때문에 캐스팅 가능
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }

}
