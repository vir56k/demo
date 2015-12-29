package demo.mobile;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;

/**
 * 分享控制类
 * Created by zhangyunfei on 15/12/28.
 */
public class ShareManager {
    private static final String TAG = ShareManager.class.getSimpleName();

    private static final String WX_APP_ID = "wx51b8febede7ca75b";
    private static final String WX_APP_SECRET = "c93f6c21e439130046ea993e1efb1d22";

    private static final String QQ_APP_ID = "1104178782";
    private static final String QQ_APP_KEY = "NakPA1sbPqEnKzQT";

    public static void init() {
//        Log.LOG = false;
//        Config.IsToastTip = false;

        //微信 appid appsecret
        PlatformConfig.setWeixin(WX_APP_ID, WX_APP_SECRET);

        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(QQ_APP_ID, QQ_APP_KEY);

        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("2733303695", "49596e51975c6e3f7cb7f0ae53ddfb9c");
    }

    /**
     * 获得分享API holder
     *
     * @param context
     * @return
     */
    public static UMShareAPI getUMShareAPI(Context context) {
        return UMShareAPI.get(context);
    }

    public static void openShare(Activity activity,
                                 String title,
                                 String text,
                                 String imageUrl,
                                 String targetURL,
                                 ShareListener onCompleteCallbacl) {
        UMImage umImage = null;
        if (!TextUtils.isEmpty(imageUrl))
            umImage = new UMImage(activity, imageUrl);
        openShare(activity, title,text, umImage, targetURL, onCompleteCallbacl);
    }

    public static void openShare(Activity activity,
                                 String title,
                                 String text,
                                 int imageResource,
                                 String targetURL,
                                 ShareListener onCompleteCallbacl) {
        UMImage umImage = new UMImage(activity,
                BitmapFactory.decodeResource(activity.getResources(), imageResource));
        openShare(activity, title, text, umImage, targetURL, onCompleteCallbacl);
    }

    public static void openShare(Activity activity,
                                 String title,
                                 String text,
                                 File imageFile,
                                 String targetURL,
                                 ShareListener onCompleteCallbacl) {
        UMImage umImage = null;
        if (imageFile != null)
            umImage = new UMImage(activity,
                    BitmapFactory.decodeFile(imageFile.getPath()));
        openShare(activity, title, text, umImage, targetURL, onCompleteCallbacl);
    }

    private static void openShare(Activity activity,
                                  String title,
                                  String text,
                                  UMImage umImage,
                                  String targetURL,
                                  final ShareListener onCompleteCallbacl) {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA
                };
        ShareAction shareAction = new ShareAction(activity).setDisplayList(displaylist);
        shareAction.withText(text == null ? "" : text);
        if (!TextUtils.isEmpty(targetURL))
            shareAction.withTargetUrl(targetURL);
        if (umImage != null)
            shareAction.withMedia(umImage);
        if (!TextUtils.isEmpty(title))
            shareAction.withTitle(title);
        shareAction.setCallback(new MyUMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                super.onResult(platform);
                if (onCompleteCallbacl != null)
                    onCompleteCallbacl.onComplete(platform.toString());
            }
        }).open();
    }

    private static class MyUMShareListener implements UMShareListener {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.i(TAG, platform + " 分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Log.i(TAG, platform + " 分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Log.i(TAG, platform + " 分享取消了");
        }
    }

    public interface ShareListener {
        void onComplete(String platform);
    }
}
