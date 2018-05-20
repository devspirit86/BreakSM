package com.dev.mybreak;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.Utils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.qq.e.comm.util.GDTLogger;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.MessageDigest;

import andhook.lib.AndHook;
import andhook.lib.HookHelper;

/**
 * Created by huaichen on 2018/3/31.
 */

public class HardHookHelper {

    public final static String LOG_TAG = "MY_HOOK";
    public static HookInfo currentHookInfo = null;

    public HardHookHelper() {
        LogHelper.print("\nhook test started.\n--------------------------------");
        AndHook.ensureNativeLibraryLoaded();
        HookHelper.applyHooks(HardHookHelper.class);
    }


    @HookHelper.Hook(clazz = android.app.Activity.class)
    private static void onCreate(final Object objActivity, final Bundle savedInstanceState) {
        LogHelper.print("Activity::onCreate start, this is " + objActivity);
        HookHelper.invokeVoidOrigin(objActivity, savedInstanceState);
        LogHelper.print("Activity::onCreate end");
    }

    @HookHelper.Hook(clazz = Settings.Secure.class)
    private static String getString(final Class<?> classSecure, final ContentResolver resolver,
                                    final String name) {
        LogHelper.print("hit name = " + name + ", class = " + classSecure);
        if (currentHookInfo != null || name.equals(Settings.Secure.ANDROID_ID))
            return currentHookInfo.getAndroidId();
        return HookHelper.invokeObjectOrigin(null, resolver, name);
    }

    @HookHelper.Hook(clazz = TelephonyManager.class, name = "getImei")
    public static String getImei(final Object obj) {
        LogHelper.print("hit getIMEI ");
        if (currentHookInfo != null)
            return currentHookInfo.getImei();
        return "8888888888";
    }

    @HookHelper.Hook(clazz = TelephonyManager.class, name = "getDeviceId")
    public static String getDeviceId(final Object obj) {
        LogHelper.print("hit getDeviceId ");
        if (currentHookInfo != null)
            return currentHookInfo.getDeviceId();
        return "8888888888";
    }

    @HookHelper.Hook(clazz = TelephonyManager.class, name = "getSubscriberId")
    public static String getSubscriberId(final Object obj) {
        LogHelper.print("hit getSubscriberId ");
        if (currentHookInfo != null)
            return currentHookInfo.getSubscriberId();
        return "8888888888";
    }

    @HookHelper.Hook(clazz = TelephonyManager.class, name = "getMeid")
    public static String getMeid(final Object obj) {
        LogHelper.print("hit getMeid ");

        if (currentHookInfo != null)
            return currentHookInfo.getMeid();
        return "8888888888";
    }

    @HookHelper.Hook(clazz = WifiInfo.class, name = "getMacAddress")
    public static String getMacAddress(final Object obj) {
        LogHelper.print("hit getMacAddress");

        if (currentHookInfo != null)
            return currentHookInfo.getMacaddr();
        return "8888888888";
    }

    @HookHelper.Hook(clazz = GDTLogger.class, name = "d")
    public static void gdtLogger_d(final Class<?> classSecure, final String info) {
        LogHelper.print("HOOK_GDT_DEBUG:%s", info);
    }

    @HookHelper.Hook(clazz = AdvertisingIdClient.Info.class, name = "getId")
    public static String getAdvertisingIdClient_Id(final Object obj) {
        LogHelper.print("HOOK_AdvertisingIdClient getId hit");
        return "null";
    }




    public static void printTest(final Context context) {
//        File file = new File("");
//        HttpURLConnection cnn = new HttpURLConnection() {
//            @Override
//            public void disconnect() {
//
//            }
//
//            @Override
//            public boolean usingProxy() {
//                return false;
//            }
//
//            @Override
//            public void connect() throws IOException {
//
//            }
//        }

        Utils.init(context);
        LogHelper.print("==============printTest in HardHookHelper");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LogHelper.print("DeviceID:" + PhoneUtils.getDeviceId());
        LogHelper.print("IMEI:" + PhoneUtils.getIMEI());
        LogHelper.print("IMEI_MD5" +  encode(PhoneUtils.getIMEI()));
        LogHelper.print("IMSI:" + PhoneUtils.getIMSI());
        LogHelper.print("MEID:" + PhoneUtils.getMEID());
        LogHelper.print("phoneSatues:-------------\n" + PhoneUtils.getPhoneStatus());

        LogHelper.print("DeviceSatues:-------------\n");
        LogHelper.print("AndroidID:" + DeviceUtils.getAndroidID());
        LogHelper.print("AndroidID_MD5" +  encode(DeviceUtils.getAndroidID()));
        LogHelper.print("MacAddress:" + DeviceUtils.getMacAddress());
        LogHelper.print("MacAddress_MD5" +  encode(DeviceUtils.getMacAddress()));
        LogHelper.print("Manufacturer:" + DeviceUtils.getManufacturer());
        LogHelper.print("Model:" + DeviceUtils.getModel());
        LogHelper.print("SDKVersionCode:" + DeviceUtils.getSDKVersionCode());
        LogHelper.print("SDKVersionName:" + DeviceUtils.getSDKVersionName());
        LogHelper.print("isDeviceRooted:" + DeviceUtils.isDeviceRooted());

        new Thread(){
            public void run(){
                try {
                    Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                    AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    LogHelper.print("gidentify:" + localInfo.getId());

                } catch (Throwable localThrowable) {
                    LogHelper.print("ExceptionAAID Exception:" + localThrowable.getMessage());
                }
            }
        }.start();

    }


    public static String encode(String paramString)
    {
        String str = null;
        try
        {
            str = new String(paramString);
            str = byteArrayToHexString((MessageDigest.getInstance("MD5")).digest(str.getBytes()));
        }
        catch (Exception localException) {}
        return str;
    }

    private static final String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    public static String byteArrayToHexString(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfByte.length; i++)
        {
            int j;
            if ((j = j = paramArrayOfByte[i]) < 0) {
                j += 256;
            }
            int k = j / 16;
            j %= 16;
            localStringBuffer.append(a[k] + a[j]);
        }
        return localStringBuffer.toString();
    }
}
