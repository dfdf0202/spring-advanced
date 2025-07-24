package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator operation");
        var startTime = System.currentTimeMillis();
        String result = component.operation();
        var endTime = System.currentTimeMillis();

        var realTime = endTime - startTime;

        log.info("TimeDecorator 종료 resultTime={}ms", realTime);
        return result;
    }
}
