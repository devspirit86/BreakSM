package com.dev.mybreak;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.simplelight.app.SplashActivity;

import java.util.ArrayList;

/**
 * Created by huaichen on 2018/5/4.
 */

public class HookService extends Service {
    HookInfoCenter hookCenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogHelper.print("HookService is onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogHelper.print("HookService is started");
        hookCenter = new HookInfoCenter(null);
        hookCenter.doTest();
        Thread thread = new Thread() {
            public void run() {

                ArrayList<HookInfo> array = hookCenter.getAllHookInfo();
                for(int count = 1 ;count < 100 ; count ++){
                    for (int i = 0; i < array.size(); i++) {
                        LogHelper.print("to start a new SplanAD Activity");
                        Intent intent = new Intent(HookService.this, SplashActivity.class);
                        intent.putExtra("HookInfo", array.get(i).transferToBundle());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        try {
                            Thread.sleep(20 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        thread.start();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogHelper.print("HookService is destroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
