package com.dev.mybreak;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by huaichen on 2018/4/17.
 */

public class BuildHookHelper {

    public static void modifyBuild(HookInfo hookInfo){


        try {
            modify(Build.class,"BRAND",hookInfo.getBrand());
            modify(Build.class,"DEVICE",hookInfo.getDeviceName());

            modify(Build.class,"FINGERPRINT",hookInfo.getFingerprint());
            modify(Build.class,"HARDWARE",hookInfo.getHardware());
            modify(Build.class,"MODEL",hookInfo.getModel());
            modify(Build.class,"PRODUCT",hookInfo.getDeviceName());
            modify(Build.class,"SERIAL","null"); //写死了WGY7N16C07001288
       //     modify(Build.VERSION.class,"SDK",""+hookInfo.getSdk_int());
       //     modify(Build.VERSION.class,"SDK_INT",hookInfo.getSdk_int());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTest(){
        LogHelper.print("+++++++++++++++++++++printTest in BuildHookHelper++++++++++++++++++++++++++");
        LogHelper.print("+++++++++++++++++++++++++++++++++++++++++++++++");
        LogHelper.print("BRAND:"+Build.BRAND);
        LogHelper.print("DEVICE:"+Build.DEVICE);
        LogHelper.print("FINGERPRINT:"+Build.FINGERPRINT);
        LogHelper.print("HARDWARE:"+Build.HARDWARE);
        LogHelper.print("MODEL:"+Build.MODEL);
        LogHelper.print("VERSION.SDK:"+Build.VERSION.SDK);
        LogHelper.print("VERSION.SDK_INT:"+Build.VERSION.SDK_INT);
    }

    //还需要验证下是否可以修改Build
    public static void modify(Object object, String fieldName, Object newFieldValue) throws Exception {
        Class cls = null ;
        if(!Class.class.isInstance(object)){
            cls = object.getClass() ;
        }else{
            cls = (Class)object ;
        }
        Field field = cls.getDeclaredField(fieldName);

//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true); //Field 的 modifiers 是私有的
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        if(!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(object, newFieldValue);
    }
}
