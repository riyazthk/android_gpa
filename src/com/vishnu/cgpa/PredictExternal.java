package com.vishnu.cgpa;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PredictExternal extends Activity implements OnClickListener,
		TextWatcher {

	TextView mydept, intoverflow;
	String regulation, department;
	EditText intm1, intm2, intm3, intm4, intm5, intm6, intlab1, intlab2,
			intlab3, intlab4;
	int m1 = -1, m2 = -1, m3 = -1, m4 = -1, m5 = -1, m6 = -1, labm1 = -1,
			labm2 = -1, labm3 = -1, labm4 = -1;
	Button predictext;

	AdView adView;

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	public void onDestroy() {

		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_predict_external);

		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		mydept = (TextView) findViewById(R.id.tvPredictExtDeptReg);
		intoverflow = (TextView) findViewById(R.id.tvDisplayInternalOutOfBound);
		intm1 = (EditText) findViewById(R.id.etSub1Int);
		intm2 = (EditText) findViewById(R.id.etSub2Int);
		intm3 = (EditText) findViewById(R.id.etSub3Int);
		intm4 = (EditText) findViewById(R.id.etSub4Int);
		intm5 = (EditText) findViewById(R.id.etSub5Int);
		intm6 = (EditText) findViewById(R.id.etSub6Int);
		intlab1 = (EditText) findViewById(R.id.etLab1Int);
		intlab2 = (EditText) findViewById(R.id.etLab2Int);
		intlab3 = (EditText) findViewById(R.id.etLab3Int);
		intlab4 = (EditText) findViewById(R.id.etLab4Int);
		intm1.addTextChangedListener(this);
		intm2.addTextChangedListener(this);
		intm3.addTextChangedListener(this);
		intm4.addTextChangedListener(this);
		intm5.addTextChangedListener(this);
		intm6.addTextChangedListener(this);
		intlab1.addTextChangedListener(this);
		intlab2.addTextChangedListener(this);
		intlab3.addTextChangedListener(this);
		intlab4.addTextChangedListener(this);

		predictext = (Button) findViewById(R.id.btCalcExternal);
		predictext.setOnClickListener(this);

		regulation = getIntent().getExtras().getString("reg");
		department = getIntent().getExtras().getString("dept");
		mydept.setText(department + "(" + regulation + ")");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_predict_external, menu);
		return true;
	}

	public void AppExit() {

		this.finish();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_aboutus:
			Intent i = new Intent(PredictExternal.this, AboutUs.class);
			startActivity(i);
			break;
		case R.id.menu_exit:
			AppExit();
			break;
		case R.id.menu_rateus:
			Uri localUri = Uri.parse("market://details?id=com.vishnu.cgpa");
			Intent i1 = new Intent("android.intent.action.VIEW", localUri);
			startActivity(i1);
			break;

		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btCalcExternal:
			if (!(intm1.getText().toString().equals("")))
				m1 = Integer.parseInt(intm1.getText().toString());
			if (!(intm2.getText().toString().equals("")))
				m2 = Integer.parseInt(intm2.getText().toString());
			if (!(intm3.getText().toString().equals("")))
				m3 = Integer.parseInt(intm3.getText().toString());
			if (!(intm4.getText().toString().equals("")))
				m4 = Integer.parseInt(intm4.getText().toString());
			if (!(intm5.getText().toString().equals("")))
				m5 = Integer.parseInt(intm5.getText().toString());
			if (!(intm6.getText().toString().equals("")))
				m6 = Integer.parseInt(intm6.getText().toString());
			if (!(intlab1.getText().toString().equals("")))
				labm1 = Integer.parseInt(intlab1.getText().toString());
			if (!(intlab2.getText().toString().equals("")))
				labm2 = Integer.parseInt(intlab2.getText().toString());
			if (!(intlab3.getText().toString().equals("")))
				labm3 = Integer.parseInt(intlab3.getText().toString());
			if (!(intlab4.getText().toString().equals("")))
				labm4 = Integer.parseInt(intlab4.getText().toString());
			intoverflow.setVisibility(View.VISIBLE);
			if (m1 > 50 || m2 > 50 || m3 > 50 || m4 > 50 || m5 > 50 || m6 > 50
					|| labm1 > 50 || labm2 > 50 || labm3 > 50 || labm4 > 50) {
				intoverflow.setTextColor(Color.RED);
				intoverflow.setText("Internal must be from 0 to 50");

			} else {
				Intent i = new Intent(PredictExternal.this,
						DisplayPredictedExternal.class);
				i.putExtra("m1", m1);
				i.putExtra("m2", m2);
				i.putExtra("m3", m3);
				i.putExtra("m4", m4);
				i.putExtra("m5", m5);
				i.putExtra("m6", m6);
				i.putExtra("labm1", labm1);
				i.putExtra("labm2", labm2);
				i.putExtra("labm3", labm3);
				i.putExtra("labm4", labm4);
				startActivity(i);
			}

			break;
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (!(s.toString().equals("") || s.toString().equals("."))) {
			int value = Integer.parseInt(s.toString());
			if (value > 50)
				s.replace(0, s.length(), "50");
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

}
