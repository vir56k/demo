package vir56k.logdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vir56k.logdemo.logbackdemo2.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TimeCounter timeCounterActivity;
    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);
    //private final org.apache.log4j.Logger log2 = org.apache.log4j.Logger.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeCounterActivity = new TimeCounter("MainActivity");
        timeCounterActivity.begin();

        TextView txt = (TextView) findViewById(R.id.txt);
        float f = doDemo();
        txt.setText("时长:" + f+"毫秒");

    }

    @Override
    protected void onStart() {

        logger.info("onStart");

        //LogConfigurator logConfigurator = new LogConfigurator();
        timeCounterActivity.end();
        timeCounterActivity.print();
        super.onStart();
    }

    /**
     * 注意： 在使用android studio连接连接真机查看控制台日志时，根据手机不同，
     *       可能仅仅看到 error日志。这不是日志框架出了问题，这是手机的设置"默认只显示 error日志"，
     *       实际上真实的日志还是会写入到日志文件中的，请查看日志文件。
     * @return
     */
    private float doDemo() {
        TimeCounter timeCounter = new TimeCounter("doDemo");
        timeCounter.begin();

        for (int i = 0; i < 100; i++) {
            //LogUtil.debug(TAG, "debug 输出");

            logger.trace("trace Some log message. Details: {}", "trace 输出" + i);
            logger.debug("debug Some log message. Details: {}", "debug 输出" + i);
            logger.info("info Some log message. Details: {}", "info 输出" + i);
            logger.error("error Some log message. Details: {}", "error 输出" + i);
            logger.warn("warn Some log message. Details: {}", "warn 输出" + i);

            //log2.debug("xxxxxxxxx");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        timeCounter.end();
        timeCounter.print();
        return timeCounter.getHowLong();
    }


}
