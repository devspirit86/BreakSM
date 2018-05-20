package com.simplelight.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mybreak.BuildHookHelper;
import com.dev.mybreak.HardHookHelper;
import com.dev.mybreak.HookInfo;
import com.dev.mybreak.LogHelper;
import com.huijimuhe.monolog.R;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

import android.os.Build;

/**
 * Created by huaichen on 2018/4/7.
 */

public class SplashActivity extends Activity implements SplashADListener {
    //com.huijimuhe.monolog
    private final String APPID = "1104878885";
    private final String SplashPosID = "7040915046057449";

    private SplashAD splashAD;
    private ViewGroup container;
    private TextView skipView;
    private static final String SKIP_TEXT = "点击跳过 %d";

    public boolean canJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.print("SplanAD on Create");
        Bundle hB = getIntent().getBundleExtra("HookInfo");
        if (hB != null) {
            LogHelper.print(" ===================================================Current device Info ===================================================");
            BuildHookHelper.printTest();
            HardHookHelper.printTest(this);
            HookInfo hookInfo = null;
            hookInfo = HookInfo.clonefromBundle(hB);
            LogHelper.print("receive hook info to Hook");
            BuildHookHelper.modifyBuild(hookInfo);
            HardHookHelper hardHookHelper = new HardHookHelper();
            HardHookHelper.currentHookInfo = hookInfo;
        }


        setContentView(R.layout.activity_splash);
        container = (ViewGroup) this.findViewById(R.id.splash_container);
        skipView = (TextView) findViewById(R.id.skip_view);

        // 如果targetSDKVersion >= 23，就要申请好权限。如果您的App没有适配到Android6.0（即targetSDKVersion < 23），那么只需要在这里直接调用fetchSplashAD接口。
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission(this);
        } else {
            // 如果是Android6.0以下的机器，默认在安装时获得了所有权限，可以直接调用SDK
            fetchSplashAD(this, container, skipView, APPID, SplashPosID, this, 0);
        }
    }

    public void requestPermission(Activity context) {
        ActivityCompat.requestPermissions(context,
                new String[]{Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                },
                111);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    LogHelper.print("graint Permission succ");
                    fetchSplashAD(this, container, skipView, APPID, SplashPosID, this, 0);

                } else {
                    LogHelper.print("graint Permission fail");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * 拉取开屏广告，开屏广告的构造方法有3种，详细说明请参考开发者文档。
     *
     * @param activity      展示广告的activity
     * @param adContainer   展示广告的大容器
     * @param skipContainer 自定义的跳过按钮：传入该view给SDK后，SDK会自动给它绑定点击跳过事件。SkipView的样式可以由开发者自由定制，其尺寸限制请参考activity_splash.xml或者接入文档中的说明。
     * @param appId         应用ID
     * @param posId         广告位ID
     * @param adListener    广告状态监听器
     * @param fetchDelay    拉取广告的超时时长：取值范围[3000, 5000]，设为0表示使用广点通SDK默认的超时时长。
     */
    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer,
                               String appId, String posId, SplashADListener adListener, int fetchDelay) {
        HardHookHelper.printTest(this);
        BuildHookHelper.printTest();

//        splashAD = new SplashAD(activity, adContainer, skipContainer, appId, posId, adListener, fetchDelay);
        splashAD = new SplashAD(activity, adContainer, appId, posId, adListener);
    }

    @Override
    public void onADPresent() {
        LogHelper.print("AD onPresent tm="+ System.currentTimeMillis());

    }

    @Override
    public void onADClicked() {
        LogHelper.print("AD onClicked");
    }

    /**
     * 倒计时回调，返回广告还将被展示的剩余时间。
     * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
     *
     * @param millisUntilFinished 剩余毫秒数
     */
    @Override
    public void onADTick(long millisUntilFinished) {
        LogHelper.print(String.format(SKIP_TEXT, Math.round(millisUntilFinished / 1000f)));
        skipView.setText(String.format(SKIP_TEXT, Math.round(millisUntilFinished / 1000f)));
        skipView.postInvalidate();
        if(Math.round(millisUntilFinished / 1000f)  <= 0){
            LogHelper.print("AD should close tm="+ System.currentTimeMillis());
        }
    }

    @Override
    public void onADDismissed() {
        LogHelper.print("AD dismissed tm = "+System.currentTimeMillis());
        this.startActivity(new Intent(this, Menu.class));
        finish();
        new Thread(){
            public void run(){
                LogHelper.print("start kill Activity");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                android.os.Process.killProcess(android.os.Process.myPid());
                LogHelper.print("killed");
            }
        }.start();
    }

    @Override
    public void onNoAD(AdError error) {
        LogHelper.print(String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", error.getErrorCode(),
                error.getErrorMsg()));

        /** 如果加载广告失败，则直接跳转 */
        this.startActivity(new Intent(this, Menu.class));
        this.finish();
    }

    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。当从广告落地页返回以后，
     * 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
     */
    private void next() {

        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            next();
        }
        canJump = true;
    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
