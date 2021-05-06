package cn.zyf.videoserver.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 创建 UUID
     *
     * @return 字符
     */
    public static String makeUUID() {
        return UUID.randomUUID().toString();
    }

}
