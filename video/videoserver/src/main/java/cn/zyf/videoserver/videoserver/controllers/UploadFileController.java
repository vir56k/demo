package cn.zyf.videoserver.videoserver.controllers;

import cn.zyf.videoserver.utils.LogUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/4/25 19:22
 */
@RestController
@RequestMapping()
public class UploadFileController {
    public static final String IMAGE_STORE_DIR = "/Users/zhangyunfei/Downloads/video/public";
    private static final String TAG = UploadFileController.class.getSimpleName();

    @PostMapping(value = "/uploadfile", name = "接收上传文件")
    public String cleanAlarm(String name, String data) {
        LogUtil.d(TAG, "name=%s, data=%s", name, data);
        saveFile(data);
        return "ok";
    }

    private void saveFile(String data) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes1 = decoder.decode(data);
            String fileName = UUID.randomUUID().toString() + ".png";
            File path = new File(IMAGE_STORE_DIR, fileName);
            FileOutputStream writer = new FileOutputStream(path);
            writer.write(bytes1);
            writer.close();
            LogUtil.d(TAG, "## 保存文件成功，路径=%s", path.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "## 保存文件失败，%s", e);
        }
    }

}
