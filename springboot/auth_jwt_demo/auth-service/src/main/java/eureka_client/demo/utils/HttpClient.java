package eureka_client.demo.utils;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Map;

public class HttpClient {

    /**
     * 向目的URL发送post请求
     *
     * @param url    目的url
     * @param params 发送的参数
     * @return AdToutiaoJsonTokenData
     */
    public static String sendPostRequest(String url, MultiValueMap<String, String> params) {
        RestTemplate client = new RestTemplate();
        //新建Http头，add方法可以添加参数
        HttpHeaders headers = new HttpHeaders();
        //设置请求发送方式
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用String 类格式化（可设置为对应返回值格式的类）
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);

        return response.getBody();
    }

    /**
     * 向目的URL发送get请求
     *
     * @param url     目的url
     * @param params  发送的参数
     * @param headers 发送的http头，可在外部设置好参数后传入
     * @return String
     */
    public static String sendGetRequest(String url, MultiValueMap<String, String> params, HttpHeaders headers) {
        RestTemplate client = new RestTemplate();

        HttpMethod method = HttpMethod.GET;
        if (headers == null) {
            headers = new HttpHeaders();
        }
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用String 类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);

        return response.getBody();
    }

    /**
     * 下载大文件,使用流接收
     *
     * @param url
     * @param targetDir
     */
    public static String downloadBigFileToPath(String url, String targetDir, Map<String, String> params) {

        String filename = UUIDUtil.makeUUID() + FileUtil.getExtensionNameWithDot(url);
        String path = FileUtil.combine(targetDir, filename);

        LogUtil.d("DOWNLOAD", "# 准备下载:" + url);
        LogUtil.d("DOWNLOAD", "# 准备下载到:" + path);
        try {
            download(url, path);
            File f = new File(path);
            if (f.exists())
                LogUtil.d("DOWNLOAD", "# 下载结束，成功:" + path + ",len=" + f.length());
            else
                LogUtil.d("DOWNLOAD", "# 下载结束，失败:" + path);
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("DOWNLOAD", "# 下载结束，失败:" + path);
            return null;
        }
    }

    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename  本地文件名
     * @throws Exception 各种异常
     */
    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Safari/605.1.15");

        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
