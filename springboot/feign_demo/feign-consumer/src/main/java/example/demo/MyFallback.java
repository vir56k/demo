package example.demo;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/12 17:46
 */
public class MyFallback implements HelloClient {
    @Override
    public String hello() {
        //throw new NoFallbackAvailableException("Boom!", new RuntimeException());
        return "出错了";
    }
}
