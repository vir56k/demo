package ribbonconsumer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController
public class ReadController {
    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    private RestTemplate restTemplate;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String index() {
        // 当 大于2000的时候，就会返回error,
//        String str = restTemplate.getForEntity("http://HELLO-SERVICE-1/hello", String.class).getBody();

        return cbFactory.create("slow").run(() -> {
                    long start = System.currentTimeMillis();
                    String str = restTemplate.getForObject("http://HELLO-SERVICE-1/hello", String.class);
                    long end = System.currentTimeMillis();
                    log("Spend time : " + (end - start));
                    return str;
                },
                throwable -> "on error");

    }


    private static void log(String str) {
        System.out.println(str);
    }
}
