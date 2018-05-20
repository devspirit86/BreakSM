package com.simplelight.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huijimuhe.monolog.R;


public class Decode extends Activity implements OnClickListener,TextWatcher{
	//=======button
	 private ImageButton encode ;//加密 
	 private ImageButton decode ;//解密
	 private ImageButton send ;//回信
	 //========text
	 private TextView nameanddate ;//显示发件人
	 private TextView text ;//显示内容
	 private EditText key ;
	 //=======date
	 private Intent intentCome ;//输入的intent
	 private int codeTime = 0;//加密次数
	 private int button_size = 0;//按钮大小
	 private int windowHeight = 0;
	 private int windowWidth = 0;
	 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //初始化
	        setContentView(R.layout.decode);
	        encode = (ImageButton)this.findViewById(R.id.encode);
	        decode = (ImageButton)this.findViewById(R.id.decode);
	        send = (ImageButton)this.findViewById(R.id.send);
	        text = (TextView)this.findViewById(R.id.messageinput);
	        nameanddate = (TextView)this.findViewById(R.id.nameanddate);
	        intentCome = this.getIntent();
	        key = (EditText)this.findViewById(R.id.keyinput);
	        if(codeTime<1){//未解密
	        	encode.setVisibility(View.GONE);
	        }
	        if(intentCome!=null){//有额外信息
	        	//获取信息
	            String name = intentCome.getStringExtra("name");
		        String date = intentCome.getStringExtra("date");
		        String content = intentCome.getStringExtra("body");
		        //显示信息
		        nameanddate.setText(name+"\n"+date) ;
		        text.setText(intentCome.getStringExtra("body"));						
	        }
	        //设监听
	        encode.setOnClickListener(this);
	        decode.setOnClickListener(this);
	        send.setOnClickListener(this);
	        //======设备
	        Display display = this.getWindowManager().getDefaultDisplay();
			this.windowHeight = display.getHeight();
			this.windowWidth = display.getWidth();
			//======init
			this.init();
	        
	        
	    }
	 public void initButton(ImageButton button){
			if(this.windowHeight>=this.windowWidth){
				this.button_size = this.windowWidth/4 ;
			}
			else{
				this.button_size = this.windowHeight/4 ;
			}
			//初始化按钮
			button.setPadding(0, 0, 0, 0);
			button.setMaxHeight(this.button_size);
			button.setMaxWidth(this.button_size);
			button.setMinimumHeight(this.button_size);
			button.setMinimumWidth(this.button_size);
	} 
	 public void init(){
			//自适应button
			//==================button
		 if(this.windowHeight>=this.windowWidth){
				this.button_size = this.windowWidth/4 ;
			}
			else{
				this.button_size = this.windowHeight/4 ;
			}
			this.initButton(this.encode);
			this.initButton(this.decode);
			this.initButton(this.send);
			//==================button	
			this.key.setText(this.getPreferrentKey());
			
		}
	 public String getPreferrentKey(){
		 return PreferenceManager.getDefaultSharedPreferences(this).getString("key", this.getString(R.string.keyvalue));
	 }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
			case R.id.encode :{
				Code code = new Code(key.getText().toString());
				String textCode = code.encode(text.getText().toString());
				text.setText(textCode);
				this.codeTime --;//加密一次
				if(codeTime<1){//不可加密
					this.encode.setVisibility(View.GONE);
					this.decode.setVisibility(View.VISIBLE);
				}
			}break;
			case R.id.decode :{
				Code code = new Code(key.getText().toString());
				String textCode = code.decode(text.getText().toString());
		        text.setText(textCode);
				this.codeTime ++;//解密一次
				if(codeTime>0){//解密
					this.encode.setVisibility(View.VISIBLE);
					this.decode.setVisibility(View.GONE);
				}
			}break;
			case R.id.send :{
				//使用默认的短信发送activity
				Uri uri = Uri.parse("smsto:");
				Intent sendIntent = new Intent(Intent.ACTION_VIEW,uri);
				//内容传不过去
				//sendIntent.setType("vnd.android-dir/mms-sms");
				sendIntent.putExtra("sms_body", text.getText().toString());
				this.startActivity(sendIntent);
			}break ;
		}
	}
	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int before) {
		// TODO Auto-generated method stub
		//String sadd = s.subSequence(start, start+count); 
	}

}
