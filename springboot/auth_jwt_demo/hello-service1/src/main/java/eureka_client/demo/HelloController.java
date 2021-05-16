package eureka_client.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    private AuthorizationClient2 authorizationClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam String name) throws InterruptedException {
        String verifyToken = authorizationClient.verifyToken("一个写死的token");
        log.info("## verifyToken, result = " + verifyToken);
        log("## hi, " + name);
        return "hi, " + name;
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi(@RequestParam String name) throws InterruptedException {
        log("## hi, " + name);
        return "hi, " + name;
    }

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
