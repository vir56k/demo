package example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);


        String[] beanNames = ctx.getBeanDefinitionNames();
        //String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
        logger.info("bean总数:{}", ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            logger.info("{},beanName:{}", ++i, str);
        }

    }


}
