package vir56k.logdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import obd.mapbar.com.logbackdemo2.R;


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

    private float doDemo() {
        TimeCounter timeCounter = new TimeCounter("doDemo");
        timeCounter.begin();

        for (int i = 0; i < 100; i++) {
            //LogUtil.debug(TAG, "debug 输出");

            logger.debug("debug Some log message. Details: {}", "debug 输出" + i);
            logger.info("info Some log message. Details: {}", "debug 输出" + i);
            logger.error("error Some log message. Details: {}", "debug 输出" + i);

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
