package com.vishnu.cgpa;

import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;

public class CgpaBarGraph extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cgpa_bar_graph);
		float [] allgpa = getIntent().getExtras().getFloatArray("allgpa");
		BarGraph bar = new BarGraph();
    	GraphicalView gView = bar.getView(this,allgpa);
        
    	LinearLayout layout = (LinearLayout) findViewById(R.id.cgpabargraphdisplay);
    	layout.addView(gView);
	}

	

}
