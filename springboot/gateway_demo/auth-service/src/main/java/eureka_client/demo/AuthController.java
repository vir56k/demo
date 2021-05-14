package eureka_client.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AuthController {
    Logger logger = LoggerFactory.getLogger("AuthController");

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String login(@RequestParam String token) throws InterruptedException {
        logger.info("## auth" + token);
        return "your token is " + token;

    }

}
