package com.simplelight.app;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts.Phones;

public class QueryContract {
	private static Uri phone = null;
	public static String getDisplayNameFromPhone(Activity activity,String phoneNum){
		if(phoneNum==null||phoneNum.trim().equals("")){
			return null ;
		}
		phone = Uri.withAppendedPath(Phones.CONTENT_FILTER_URL, phoneNum);
		if(phone==null){
			//不是电话号码
			return null;
		}
		//获取person—id
		Cursor phoneCursor = activity.getContentResolver().query(phone, new String[]{Phones._ID,Phones.DISPLAY_NAME}, null, null, null);
		if(phoneCursor.moveToFirst()){
			//有联系人
			return phoneCursor.getString(1) ;
		}
		//电话号码未保存
		return null ;
	}
}
