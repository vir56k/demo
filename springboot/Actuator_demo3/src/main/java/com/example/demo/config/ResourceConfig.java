package com.example.demo.config;


import com.example.demo.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig {
    private static final String TAG = "## ResourceConfig";

    // 图片URL 路径映射的 根
    public static final String IMAGE_PATH_PATTERNS = "/web";
    public static final String IMAGE_STORE_DIR = "/Users/zhangyunfei/git/demo/video_flv_js_demo/web";

    public ResourceConfig() {
        LogUtil.d(TAG, "instance !");
    }

    @Bean
    public WebMvcConfigurer resourceConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // 将整个 img 文件配置成静态资源
                String pathPatterns = IMAGE_PATH_PATTERNS + "/**";
                String locations = "file:" + IMAGE_STORE_DIR + "//";
                registry.addResourceHandler(pathPatterns)
                        .addResourceLocations(locations);

                LogUtil.d(TAG, "## 静态资源路径映射： %s ==> %s", IMAGE_PATH_PATTERNS, IMAGE_STORE_DIR);
            }
        };
    }
}
