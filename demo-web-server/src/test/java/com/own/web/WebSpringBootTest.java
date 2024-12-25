package com.own.web;

import com.own.web.processor.RemarkProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuChang
 * @date 2023/2/3 15:45
 * @describe
 */

@SpringBootTest
public class WebSpringBootTest {

    @Test
    public void remarkAnnotationTest() {
        System.out.println(RemarkProcessor.set);
    }
}
