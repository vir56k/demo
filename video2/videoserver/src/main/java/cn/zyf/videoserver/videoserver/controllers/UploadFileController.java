package cn.zyf.videoserver.videoserver.controllers;

import cn.zyf.videoserver.utils.LogUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/4/25 19:22
 */
@RestController
@RequestMapping()
public class UploadFileController {
    public static final String IMAGE_STORE_DIR = "/Users/zhangyunfei/git/demo/video2/public";
    private static final String TAG = UploadFileController.class.getSimpleName();


    @PostMapping(value = "/uploadvideo", name = "接收上传文件")
    public String uploadVideo(@RequestParam("file") MultipartFile uploadFile) {
        String name = uploadFile.getOriginalFilename();
        String size = String.format("%.2f MB", uploadFile.getSize() / 1024f / 1024f);
        System.out.println(String.format("# name=%s, size=%s", name, size));
        saveFile(uploadFile);
        return "ok";
    }

    private void saveFile(MultipartFile uploadFile) {
        try {
            String fileName = UUID.randomUUID().toString() + ".mp4";
            File path = new File(IMAGE_STORE_DIR, fileName);
            uploadFile.transferTo(path);
            LogUtil.d(TAG, "## 保存文件成功，路径=%s", path.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "## 保存文件失败，%s", e);
        }
    }

}
