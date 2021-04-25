package cn.zyf.videoserver.videoserver.config;


import cn.zyf.videoserver.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig {
    private static final String TAG = "## ResourceConfig";

    // 图片URL 路径映射的 根
    public static final String IMAGE_PATH_PATTERNS = "/img";
    public static final String IMAGE_STORE_DIR = "/Users/zhangyunfei/Downloads/video/public";

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

                LogUtil.d(TAG, "## 静态资源路径映射：%s --> %s", pathPatterns, locations);
            }
        };
    }
}
