package cn.zyf.videoserver.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 将 保存在 request.setAttribute 上下中。这是个辅助工具类
 */
public class UserIDHelper {
    public static void setAttribute(String userID, HttpServletRequest request) {
        request.setAttribute("USER_ID", userID);
    }

    public static String getAttribute(HttpServletRequest req) {
        String userID = (String) req.getAttribute("USER_ID");
        return userID;
    }
}
