package com.vishnu.cgpa;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class Display_GPA_Prediction extends Activity {
TextView predictext;
String tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display__gpa__prediction);
		predictext=(TextView)findViewById(R.id.tvarrear);
		tv = getIntent().getExtras().getString("RA");
		predictext.setTextColor(Color.BLACK);
		predictext.setText(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display__gpa__prediction, menu);
		return true;
	}

}
