package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator operation");
        var startTime = System.currentTimeMillis();
        //String result = concreteLogic.operation();
        String result = super.operation();
        var endTime = System.currentTimeMillis();

        var realTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime={}ms", realTime);
        return result;
    }
}
