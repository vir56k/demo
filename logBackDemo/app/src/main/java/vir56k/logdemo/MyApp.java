package vir56k.logdemo;

import android.app.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangyunfei on 15/9/9.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        configLog("log");
        final Logger logger = LoggerFactory.getLogger(MainActivity.class);
        logger.info("My Application Created");

    }

    private void configLog(String logFileNamePrefix) {
        LogConfigurator.confifure();
    }
}
