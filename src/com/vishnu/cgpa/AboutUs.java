package com.vishnu.cgpa;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AboutUs extends Activity {

	TextView verno,abtdevs;
	String abt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		abt ="This app was developed by"+"\n"+
				"R.BalaVishnu (CSE-14)"+"\n"+
				"T.KarthikKumar (CSE-14)"+"\n"+
				"P.Murali Prasanth (CSE-14)"+"\n"+
				"S.Vidya Sagar (ECE-14)";
		
		verno = (TextView) findViewById(R.id.tvVersionNumber);
		abtdevs = (TextView) findViewById(R.id.tvAbtDevs);
		abtdevs.setText(abt);
		
		try {
			PackageManager manager = getBaseContext().getPackageManager();
		    PackageInfo info = manager.getPackageInfo("com.vishnu.cgpa", 0);
		    String name = info.versionName;
		    verno.setText("Version "+name);
        } catch (NameNotFoundException e) {
        	verno.setText("Version UNKNOWN");
        }
	}

	

}
