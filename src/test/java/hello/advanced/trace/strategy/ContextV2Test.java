package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("로직 1");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("로직 2");
            }
        });
    }

    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("로직 1"));
        context.execute(() -> log.info("로직 2"));
    }

}
