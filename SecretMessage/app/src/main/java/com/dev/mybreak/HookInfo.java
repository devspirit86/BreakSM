package com.dev.mybreak;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by huaichen on 2018/4/22.
 */

public class HookInfo {
    static HookInfo instance ;

    private String androidId = "androidId" ;
    private String imei = "imei" ;
    private String meid = "meid" ;
    private String deviceId = "deviceId" ;
    private String subscriberId = "subscriberId" ;
    private String macaddr = "getMacAddress" ;

    private String brand = "brand" ;
    private String deviceName = "DEVICE" ;
    private String hardware = "HARDWARE" ;
    private String model = "MODEL" ;

    private int sdk_int = 23 ;


    public synchronized static HookInfo getInstance(){
        if(instance == null){
            instance = new HookInfo();
        }

       return instance ;
    }

    public synchronized  static void setInstance(HookInfo info){
        instance = info ;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFingerprint() {

        String fingerPrint = getBrand()+"/"+getModel()+
                "/"+getDeviceName()+":8.0.0/"+getBrand()
                +getModel()+"/343(C00):user/release-keys";

        return fingerPrint;
    }


    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSdk_int() {
        return sdk_int;
    }

    public void setSdk_int(int sdk_int) {
        this.sdk_int = sdk_int;
    }

    public Bundle transferToBundle(){
        Bundle b = new Bundle();
        b.putString("androidId" , androidId);
        b.putString("imei" , imei);
        b.putString("meid" , meid);
        b.putString("deviceId" , deviceId);
        b.putString("subscriberId" , subscriberId);
        b.putString("macaddr" , macaddr);

        b.putString("brand" , brand);
        b.putString("deviceName" , deviceName);
        b.putString("hardware" , hardware);
        b.putString("model" , model);
        b.putInt("sdk_int" ,sdk_int);
        return b ;
    }

    public static HookInfo clonefromBundle(Bundle bundle){
        if(bundle == null)
            return  null ;

        HookInfo  hi = new HookInfo() ;
        hi.setAndroidId(bundle.getString("androidId"));
        hi.setBrand(bundle.getString("brand"));
        hi.setDeviceId(bundle.getString("deviceId"));
        hi.setDeviceName(bundle.getString("deviceName"));
        hi.setHardware(bundle.getString("hardware"));
        hi.setImei(bundle.getString("imei"));
        hi.setMacaddr(bundle.getString("macaddr"));
        hi.setMeid(bundle.getString("meid"));
        hi.setModel(bundle.getString("model"));
        hi.setSdk_int(bundle.getInt("sdk_int"));
        hi.setSubscriberId(bundle.getString("subscriberId"));
        return hi ;
    }
}
