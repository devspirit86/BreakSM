package com.simplelight.app;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class QueryMessage {
	private Uri uri ;// 查询地址
	private String uriString ;//uri 字符串
	public QueryMessage() {
		// TODO Auto-generated constructor stub
		this.uriString = "content://sms" ;//所有手机短信地址
		this.uri = Uri.parse(this.uriString);//所有手机短信
	}
	public QueryMessage(String uriString) {
		// TODO Auto-generated constructor stub
		this.uriString = uriString ;
		this.uri = Uri.parse(this.uriString);
	}
	
	public QueryMessage(Uri uri) {
		// TODO Auto-generated constructor stub
		this.uri = uri;
		this.uriString = this.uri.getEncodedPath() ;
	}
	
	public Cursor queryAll(Activity activity){ //无条件全部查找
		//select * from uri 
		Cursor cursor = this.queryByWhereOder(activity, null, null);
		return cursor ;
	}
	
	public Cursor queryByWhere(Activity activity,String[] whereAs){ //无条件全部查找
		//select * from uri Where whereAs
		Cursor cursor = this.queryByWhereOder(activity, whereAs, null);
		return cursor ;
	}
	
	public Cursor queryByWhereOder(Activity activity,String[] whereAs ,String orderBy){ //无条件全部查找
		ContentResolver cr = activity.getContentResolver();
		//select * from uri Where whereAs Order oder
		Cursor cursor = cr.query(this.uri, null, null, whereAs, orderBy);
		return cursor ;
	}

}
