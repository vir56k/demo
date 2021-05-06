package cn.zyf.videoserver.videoserver.controllers;

import cn.zyf.videoserver.utils.LogUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/4/25 19:22
 */
@RestController
@RequestMapping("/api/v1")
public class CallbackController {
    private static final String TAG = CallbackController.class.getSimpleName();

    //当客户端连接到指定的vhost和app时
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public int clients(@RequestBody String bodyString) {
        LogUtil.d(TAG, "# 触发事件 [ 客户端连接 ], msg = %s", bodyString);
        return 0;
    }

    @RequestMapping(value = "/streams", method = RequestMethod.POST)
    public int streams(@RequestBody String bodyString) {
        LogUtil.d(TAG, "# 触发事件 [ 客户端发布流 ], msg = %s", bodyString);
        return 0;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    public int sessions(@RequestBody String bodyString) {
        LogUtil.d(TAG, "# 触发事件 [ 播放流 ], msg = %s", bodyString);
        return 0;
    }

    @RequestMapping(value = "/dvrs", method = RequestMethod.POST)
    public int dvrs(@RequestBody String bodyString) {
        LogUtil.d(TAG, "# 触发事件 [ dvrs ], msg = %s", bodyString);
        return 0;
    }

    @RequestMapping(value = "/hls", method = RequestMethod.POST)
    public int hls(@RequestBody String bodyString) {
        LogUtil.d(TAG, "# 触发事件 [ hls ], msg = %s", bodyString);
        return 0;
    }

    // 类似这样： /api/v1/hls/[app]/[stream]/[ts_url][param];
    @RequestMapping(value = "/hls/{app}/{stream}/{ts_url}", method = RequestMethod.GET)
    public int on_hls_notify(@PathVariable String app,
                             @PathVariable String stream,
                             @PathVariable String ts_url,
                             @RequestParam Map<String, Object> param) {
        LogUtil.d(TAG, "# 触发事件 [ hls ], app=%s, stream=%s, ts_url=%s, param=%s",
                app, stream, ts_url, param);
        return 0;
    }


}
