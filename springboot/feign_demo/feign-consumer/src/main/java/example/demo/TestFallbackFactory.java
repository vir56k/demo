package example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/12 17:46
 */

@Component
public class TestFallbackFactory implements FallbackFactory<HelloClient> {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Override
    public HelloClient create(Throwable cause) {
        logger.info("**********");
        cause.printStackTrace();

        return new MyFallback();
    }

}
