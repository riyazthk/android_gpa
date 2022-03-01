package com.vishnu.cgpa;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListSavedCgpa extends ListActivity implements OnClickListener {

	Button backbtn;
	ArrayList<String> values,modvalues;
	int i;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		onCreate(null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CgpaDatabaseHelper dbhelp = new CgpaDatabaseHelper(this);
		values = dbhelp.getSavedCgpaNames();
		i=1;
		//Remove the actual ID and append a continuous index
		modvalues = new ArrayList<String>();
		for(String temp:values){
			modvalues.add(i+". "+temp.substring(2));
			i++;
		}
		if(modvalues.size()<=0){
			modvalues.add("No Records Found");
			values.add("No Records Found");
		}
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modvalues));
        setContentView(R.layout.activity_list_saved_cgpa);
        backbtn = (Button) findViewById(R.id.btBacktoCgpaCalc);
		backbtn.setOnClickListener(this);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String SelectedItem = values.get(position);
		if(!SelectedItem.equals("No Records Found")){
			String[] substr = SelectedItem.split("-");
			int idToRetrieve = Integer.parseInt(substr[0]);
			Intent i = new Intent(this,DisplaySavedCGPAResult.class);
			i.putExtra("id", idToRetrieve);
			startActivity(i);
		}
		
	}



	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btBacktoCgpaCalc:
			
			super.onBackPressed();
			break;

		}
		
	}

}
