package com.example.pathforge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.ai.vertex.ai.enabled=false"
})
class PathForgeApplicationTests {

    @Test
    void contextLoads() {
    }

}
