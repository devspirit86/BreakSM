package com.simplelight.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.huijimuhe.monolog.R;

public class Setting extends PreferenceActivity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.addPreferencesFromResource(R.xml.setting);
	 }
}
