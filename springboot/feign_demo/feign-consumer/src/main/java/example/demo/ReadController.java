package example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController
public class ReadController {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private HelloClient helloClient;

    public ReadController(HelloClient helloClient) {
        this.helloClient = helloClient;
    }




    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String index() {

        long start = System.currentTimeMillis();
        String str = helloClient.hello();
        long end = System.currentTimeMillis();
        logger.info("Spend time : " + (end - start));

        return str;
    }

}
