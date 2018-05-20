package com.simplelight.app;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.comm.util.AdError;
import com.huijimuhe.monolog.R;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.BannerView;

public class About extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	     //   this.setContentView(R.layout.about);

//com.simplelight.app
//		 final String APPID = "1105546573" ;
//		 final String BannerPosID = "8030818423775520" ;
//		 final String InterteristalPosID = " 1050715464137380" ;

		 //com.huijimuhe.monolog
		 final String APPID = "1104878885" ;
		 final String BannerPosID = "7040915046057449" ;
		 final String InterteristalPosID = " 4020417016750632" ;
//		 // 展示广告
		 setContentView(R.layout.about);
		 ViewGroup vg = (ViewGroup)findViewById(R.id.bannerContainer);
		 // 创建Banner广告AdView对象
		 // appId : 在 http://e.qq.com/dev/ 能看到的app唯一字符串
		 // posId : 在 http://e.qq.com/dev/ 生成的数字串，并非 appid 或者 appkey
		 BannerView banner = new BannerView(this, ADSize.BANNER, APPID, BannerPosID);
		 //设置广告轮播时间，为0或30~120之间的数字，单位为s,0标识不自动轮播
		 banner.setRefresh(30);
		 banner.setADListener(new AbstractBannerADListener() {

			 @Override
			 public void onNoAD(AdError error) {
				 Log.i(
						 "AD_DEMO",
						 String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
								 error.getErrorMsg()));
			 }

			 @Override
			 public void onADReceiv() {
				 Log.i("AD_DEMO", "ONBannerReceive");
			 }
		 });
        /* 发起广告请求，收到广告数据后会展示数据   */
		 banner.loadAD();
		 vg.addView(banner);

	 }
}
