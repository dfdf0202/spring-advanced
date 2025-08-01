package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class CallServiceV3Test {

    private final CallServiceV3 callServiceV3;

    @Test
    void external() {
        callServiceV3.external();
    }
}