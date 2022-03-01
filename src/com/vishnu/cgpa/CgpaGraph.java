package com.vishnu.cgpa;

import org.achartengine.GraphicalView;
import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;

public class CgpaGraph extends Activity {

	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cgpa_graph);
		
		id= getIntent().getExtras().getInt("id");
		LineGraph line = new LineGraph();
		GraphicalView gView = line.getView(this,id);
        
    	LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
    	layout.addView(gView);
	}
	
	
	

}
