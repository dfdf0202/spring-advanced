package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callbackV1() {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(new Callback() {

            @Override
            public void call() {
                log.info("로직 1 실행");
            }
        });

        timeLogTemplate.execute(new Callback() {

            @Override
            public void call() {
                log.info("로직 2 실행");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("람다 로직 1 실행"));
        timeLogTemplate.execute(() -> log.info("람다 로직 2 실행"));
    }

}
