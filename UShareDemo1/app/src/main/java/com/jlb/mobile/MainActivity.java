package com.jlb.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import demo.mobile.ShareManager;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "近邻宝";
                String text = "我要分享";
                String targetURL = "http://www.baidu.com";
                String imageURL = "http://bbs.umeng.com/static/image/smiley/aliww/16-111129230454.gif";
                ShareManager.openShare(getActivity(), title, text, imageURL, targetURL, new ShareManager.ShareListener() {
                    @Override
                    public void onComplete(String platform) {
                        alert("分享成功");
                    }
                });
            }
        });

        ShareManager.init();
    }


    public void alert(String msg) {
        Toast.makeText(getActivity(), msg, 0).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ShareManager.getUMShareAPI(this).onActivityResult(requestCode, resultCode, data);
    }

    public Activity getActivity() {
        return this;
    }
}
