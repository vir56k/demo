package cn.zyf.videoserver.videoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan({"cn.zyf.videoserver.videoserver.config"})
public class VideoserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoserverApplication.class, args);
    }

}
