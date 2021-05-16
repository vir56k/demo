package eureka_client.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        log("/hello, from hello-service-2");
        return "Hello World from hello-service-2";

    }

    private void log(String str) {
        System.out.println(str);
    }
}
