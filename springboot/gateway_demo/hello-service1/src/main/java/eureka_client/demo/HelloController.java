package eureka_client.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() throws InterruptedException {
        //让处理线程等待几秒钟
        int sleepTime = new Random().nextInt(3000);
        log("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);

        return "Hello World from hello-service-1";

    }

    private static void log(String str) {
        System.out.println(str);
    }
}
