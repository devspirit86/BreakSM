package com.simplelight.app;

import android.app.Application;

import com.dev.mybreak.BuildHookHelper;
import com.dev.mybreak.HardHookHelper;
import com.dev.mybreak.LogHelper;

/**
 * Created by huaichen on 2018/3/31.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogHelper.enableFileWriter(this);
    }


}
