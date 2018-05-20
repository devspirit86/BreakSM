 package com.simplelight.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dev.mybreak.HardHookHelper;
import com.dev.mybreak.HookService;
import com.huijimuhe.monolog.R;

 public class Menu extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	//===layout
	 private LinearLayout menu_bgLayout ;
	 private TableLayout menu_table ;
	 private TableRow menu_tablerow ;
	//===text
	 private TextView title ; 
	//===Button 
	 private ImageButton send ; 
	 private ImageButton read ; 
	 private ImageButton setting ; 
	 private ImageButton about ; 
	 private int button_size = 0;
	 private int windowHeight = 0;
	 private int windowWidth = 0;

	public void requestPermission(Activity context){

//		if (ContextCompat.checkSelfPermission(context,
//				Manifest.permission.READ_PHONE_STATE)
//				!= PackageManager.PERMISSION_GRANTED) {
//

			ActivityCompat.requestPermissions(context,
					new String[]{Manifest.permission.READ_PHONE_STATE ,
							Manifest.permission.INTERNET,
							Manifest.permission.ACCESS_NETWORK_STATE,
							Manifest.permission.ACCESS_WIFI_STATE,
							Manifest.permission.ACCESS_COARSE_LOCATION,
							Manifest.permission.WRITE_EXTERNAL_STORAGE,
					},
					111);
//		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case 111: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {


				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request
		}
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		requestPermission(this);

		startService(new Intent(this,HookService.class)) ;

        //初始化
	        setContentView(R.layout.main);
	        //========layout
	        menu_bgLayout =(LinearLayout)this.findViewById(R.id.menu_bglayout);
	        menu_table =(TableLayout)this.findViewById(R.id.menu_table);
	        menu_tablerow =(TableRow)this.findViewById(R.id.menu_tableRow);
	        //========text
	        this.title = (TextView)this.findViewById(R.id.title);
	        //========button
	        send = (ImageButton)this.findViewById(R.id.send) ;
	        read = (ImageButton)this.findViewById(R.id.read) ;
	        setting = (ImageButton)this.findViewById(R.id.setting) ;
	        about = (ImageButton)this.findViewById(R.id.about) ;
	        //设备
	        Display display = this.getWindowManager().getDefaultDisplay();
			this.windowHeight = display.getHeight();
			this.windowWidth = display.getWidth();
	        //初始
	        this.initMenu();
            //监听
	     	send.setOnClickListener(this);
	        read.setOnClickListener(this);
	        setting.setOnClickListener(this);
	        about.setOnClickListener(this);


    }
	public void initButton(ImageButton button){
		//初始化按钮
		button.setPadding(0, 0, 0, 0);
		button.setMaxHeight(this.button_size);
		button.setMaxWidth(this.button_size);
		button.setMinimumHeight(this.button_size);
		button.setMinimumWidth(this.button_size);
	} 
	public void initMenu(){
		//自适应menu界面
		//==================Text_title
		//==================Table
		this.menu_table.setPadding(10, 10, 10, 10);
		//==================button
		if(this.windowHeight>=this.windowWidth){
			this.button_size = this.windowWidth/4 ;
		}
		else{
			this.button_size = this.windowHeight/4 ;
		}
		this.initButton(this.send);
		this.initButton(this.read);
		this.initButton(this.setting);
		this.initButton(this.about);
		
		//==================button
		
		
	}
	public int getWidth(){
		return this.menu_bgLayout.getWidth() ;
	}
	public int getHeight(){
		return this.menu_bgLayout.getHeight() ;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Uri uri = null ;
		switch(arg0.getId()){
		case R.id.send :{
			Intent intent = new Intent(this,Encode.class ); 
			this.startActivity(intent);
		} break ;
		case R.id.read :{
			Intent intent = new Intent(this,MessageList.class);
			this.startActivity(intent);
			} break ;
		case R.id.setting :{
			Intent intent = new Intent(this,SplashActivity.class);
			this.startActivity(intent);
			}break ;
		case R.id.about :{
			Intent intent = new Intent(this,About.class);
			this.startActivity(intent);
			}break ;
		}
	}
	
    
}