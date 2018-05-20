package com.simplelight.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.huijimuhe.monolog.R;


public class MessageList extends Activity implements OnItemClickListener , SimpleCursorAdapter.ViewBinder{
	private ListView messagelist ;//显示列表
	private QueryMessage queryMessage ;//查询短信对象
	private Cursor querySMS ;//查询结果
    private DateFormat dateFormate ;//时间格式
    private String version ; //english 为英文，chinese 为中文 
    private String formateDateStr ;//日期格式
    private Intent intentDecode ;//解密
    
	
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	        //初始化
	    setContentView(R.layout.messagelist);
        //初始化
        this.setContentView(R.layout.messagelist);
        messagelist = (ListView)this.findViewById(R.id.messageList);
        queryMessage = new QueryMessage();//默认全部
		querySMS = queryMessage.queryAll(this);//搜索全部
		//==========================Test================================================
		String[] names =querySMS.getColumnNames();
		for(int i = 0;i<names.length;i++){
			Log.d("sms", names[i]);
		}
		
		
		
		version = this.getString(R.string.version);
		if(version.equals("chinese")){
			//中文：
			formateDateStr ="yyyy-MM-dd hh:mm";
		}else{
			//英文：
			formateDateStr ="MM/dd/yy h:mmaa";
		}
		dateFormate = new DateFormat();
		this.intentDecode = new Intent(this,Decode.class);
      //显示列图
		int[] showView = new int[3] ;
		showView[0] = R.id.dateText;
		showView[1] = R.id.nameText;
		showView[2] = R.id.contentText;
		//显示数据列名
		String[] showData = new String[3] ;
		showData[0] = "date" ;
		showData[1] = "address" ;
		showData[2] = "body" ;
		//生成列表
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.messagelabel,querySMS,showData,showView);
        simpleCursorAdapter.setViewBinder(this);
        messagelist.setAdapter(simpleCursorAdapter);
        messagelist.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		android.widget.LinearLayout layout1 = (android.widget.LinearLayout)arg1 ;
		android.widget.TextView text1 = (android.widget.TextView)layout1.getChildAt(0);
		android.widget.LinearLayout layout2 = (android.widget.LinearLayout)layout1.getChildAt(1); ;
		TextView text2 = (android.widget.TextView)layout2.getChildAt(0);
		TextView text3 = (android.widget.TextView)layout2.getChildAt(1);
		this.intentDecode.putExtra("name", text1.getText().toString());
		this.intentDecode.putExtra("body", text2.getText().toString());
		this.intentDecode.putExtra("date", text3.getText().toString());
		this.startActivity(this.intentDecode);
	}

	@Override
	public boolean setViewValue(View view, Cursor cursor, int index) {
		// TODO Auto-generated method stub
		if(index==cursor.getColumnIndex("rev_date")){
			//处理时间显示的问题
			String date = (String) this.dateFormate.format(this.formateDateStr, cursor.getLong(index));
			((TextView)view).setText(date) ;
			return true;
		}
		if(index==cursor.getColumnIndex("address")){
			//收件人名
			String address = cursor.getString(index) ;
			String name = QueryContract.getDisplayNameFromPhone(this,address);
			if(name != null && !name.equals("")){
				//是联系人，显示姓名
				((TextView)view).setText(name) ;
			}else{
				//不是联系人，显示地址
				((TextView)view).setText(address) ;
			}
			return true;
		}
		return false;
	}
	

}
