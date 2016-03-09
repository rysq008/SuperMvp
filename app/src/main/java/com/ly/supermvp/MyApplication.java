package com.ly.supermvp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * <Pre>
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 10:47
 */
public class MyApplication extends Application {
    private static MyApplication instance;
//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init().logLevel(LogLevel.FULL);
        instance = (MyApplication) getApplicationContext();

        this.enabledStrictMode();
        //LeakCanary检测OOM
        LeakCanary.install(this);
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll()  //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
    // 获取ApplicationContext
    public static Context getContext() {
        return instance;
    }
}
