package vir56k.logdemo;

import android.app.Application;
import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by zhangyunfei on 15/9/9.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        configLog("log");
        Logger log = Logger.getLogger(MyApp.class);
        log.info("My Application Created");

    }

    private void configLog(String logFileNamePrefix) {
        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory()
                + File.separator + "logdemo" + File.separator + "logs"
                + File.separator + (logFileNamePrefix == null ? "" : logFileNamePrefix) + "log4j.txt");
        logConfigurator.setRootLevel(Level.DEBUG);
        logConfigurator.setLevel("org.apache", Level.ERROR);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 2);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
    }
}
