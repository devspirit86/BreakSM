package com.dev.mybreak;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by huaichen on 2018/5/5.
 */

public class HookInfoCenter {
    ArrayList<HookInfo> allHookInfo  ;

    public HookInfoCenter(String deviceInfoPath){
        allHookInfo = new ArrayList<>();
    }


    public ArrayList<HookInfo> getAllHookInfo() {
        return allHookInfo;
    }

    public void doTest(){
        HookInfo hi = new HookInfo();
        hi.setAndroidId("beb157b91ccff1b9");
        hi.setBrand("BAH");
        hi.setDeviceId("x6gnu18122105773");
        hi.setDeviceName("W09_01");
        hi.setHardware("W09");
        hi.setImei("x6gnu18122105773");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("35510cc250e8c378");
        hi.setBrand("HUAWEI");
        hi.setDeviceId("tjfdu16330001047");
        hi.setDeviceName("M2_02");
        hi.setHardware("801W");
        hi.setImei("tjfdu16330001047");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("801W");
        hi.setSdk_int(23);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);


        hi = new HookInfo();
        hi.setAndroidId("76ad9fffc353365c");
        hi.setBrand("HUAWEI");
        hi.setDeviceId("tjf6r15c28000195");
        hi.setDeviceName("M2_03");
        hi.setHardware("801W");
        hi.setImei("tjf6r15c28000195");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("801W");
        hi.setSdk_int(22);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);


        hi = new HookInfo();
        hi.setAndroidId("80654d78d8e9f144");
        hi.setBrand("BTV");
        hi.setDeviceId("slydu17525003412");
        hi.setDeviceName("W09_04");
        hi.setHardware("W09");
        hi.setImei("slydu17525003412");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("5627da2fc36a3fdf");
        hi.setBrand("BTV");
        hi.setDeviceId("slydu17513000295");
        hi.setDeviceName("W09_05");
        hi.setHardware("W09");
        hi.setImei("slydu17513000295");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("ed0c424db9d84841");
        hi.setBrand("BTV");
        hi.setDeviceId("slydu17419000641");
        hi.setDeviceName("W09_06");
        hi.setHardware("W09");
        hi.setImei("slydu17419000641");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("332897193bbb7bb3");
        hi.setBrand("BTV");
        hi.setDeviceId("slydu17222000355");
        hi.setDeviceName("W09_07");
        hi.setHardware("W09");
        hi.setImei("slydu17222000355");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("7C552d5e0dfa7e8d7b");
        hi.setBrand("ZTE");
        hi.setDeviceId("null");
        hi.setDeviceName("BA603_07");
        hi.setHardware("BA603");
        hi.setImei("null");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("BA603");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("50b27d9feb0a178");
        hi.setBrand("BTV");
        hi.setDeviceId("sly6r17301001036");
        hi.setDeviceName("W09_08");
        hi.setHardware("W09");
        hi.setImei("sly6r17301001036");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("W09");
        hi.setSdk_int(24);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("ff9d266ab5046ddc");
        hi.setBrand("MI");
        hi.setDeviceId("null");
        hi.setDeviceName("5C_09");
        hi.setHardware("5C");
        hi.setImei("null");
        hi.setMacaddr("null");
        hi.setMeid("null");
        hi.setModel("5C");
        hi.setSdk_int(25);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        hi = new HookInfo();
        hi.setAndroidId("f402f17a5a6a890b");
        hi.setBrand("vivo");
        hi.setDeviceId("a100005a5b06ad");
        hi.setDeviceName("Y55L_10");
        hi.setHardware("Y55L");
        hi.setImei("a100005a5b06ad");
        hi.setMacaddr("null");
        hi.setMeid("460009562945107");
        hi.setModel("Y55L");
        hi.setSdk_int(23);
        hi.setSubscriberId("null");
        allHookInfo.add(hi);

        LogHelper.print("add Hookinfo count:%d",allHookInfo.size());
    }
}
