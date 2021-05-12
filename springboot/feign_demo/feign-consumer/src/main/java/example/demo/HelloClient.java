package example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/12 17:37
 */
@FeignClient(value = "HELLO-SERVICE-1", fallbackFactory = TestFallbackFactory.class)
public interface HelloClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();

}