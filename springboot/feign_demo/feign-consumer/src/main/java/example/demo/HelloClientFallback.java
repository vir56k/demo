package example.demo;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/12 17:38
 */
@Component
public class HelloClientFallback implements HelloClient {

    @Override
    public String hello() {
        log("失败了");
        return "失败了";
    }

    private static void log(String str) {
        System.out.println(str);
    }

}
