package com.vishnu.cgpa;

import java.lang.reflect.Field;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

public class OptionSelect extends Activity implements OnClickListener,
		OnItemSelectedListener {

	Button cgpa, gpa, predictext, internal;
	Spinner dept_select, reg_select;
	String regulation, department;
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK && isTaskRoot())) {
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Exit Application")
					.setMessage("Are you sure you want to Quit?")
					.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// Stop the activity
									finish();
								}
							}).setNegativeButton("NO", null).show();

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_select);
		// Display a permanent menu key on the Action bar
		getOverflowMenu();

		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Show these options only at first time of the app
		SharedPreferences localSharedPreferences = getSharedPreferences(
				"vishnucgpa", 0);
		Boolean localBoolean = Boolean.valueOf(localSharedPreferences
				.getBoolean("promoted", false));
		localSharedPreferences.edit().putBoolean("promoted", true).commit();
		if (!localBoolean.booleanValue()) {
			final AlertDialog localAlertDialog = new AlertDialog.Builder(this)
					.create();
			localAlertDialog.setTitle("Rate it");
			localAlertDialog
					.setMessage("If you like this application,please rate it on Play Store.If you find any bugs,report it to me through email(balavishnu1993@gmail.com).\n\nSupport my work by  "
							+ "rating this app and suggesting it to your friends.");

			localAlertDialog.setButton(-3, "Rate it",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramDialogInterface,
								int paramInt) {
							Uri localUri = Uri
									.parse("market://details?id=com.vishnu.cgpa");
							Intent i1 = new Intent(
									"android.intent.action.VIEW", localUri);
							startActivity(i1);
						}
					});
			localAlertDialog.setButton(-2, "Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramDialogInterface,
								int paramInt) {
							localAlertDialog.cancel();
						}
					});
			localAlertDialog.show();
			startActivity(new Intent(this, GettingStarted.class));
		}

		dept_select = (Spinner) findViewById(R.id.spDept);
		reg_select = (Spinner) findViewById(R.id.spReg);
		dept_select.setOnItemSelectedListener(this);
		reg_select.setOnItemSelectedListener(this);
		gpa = (Button) findViewById(R.id.btGoCalcGPA);
		cgpa = (Button) findViewById(R.id.btGoCalcCGPA);
		internal = (Button) findViewById(R.id.btCalcInternal);
		predictext = (Button) findViewById(R.id.btPredictExternal);
		gpa.setOnClickListener(this);
		cgpa.setOnClickListener(this);
		predictext.setOnClickListener(this);
		internal.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option_select, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btGoCalcCGPA:

			Intent i = new Intent(OptionSelect.this, CGPACalc.class);
			i.putExtra("dept", department);
			i.putExtra("reg", regulation);
			startActivity(i);
			break;
		case R.id.btGoCalcGPA:
			Intent i1 = new Intent(OptionSelect.this, GPACalc.class);
			i1.putExtra("dept", department);
			i1.putExtra("reg", regulation);
			startActivity(i1);
			break;
		case R.id.btPredictExternal:
			Intent i2 = new Intent(OptionSelect.this, PredictExternal.class);
			i2.putExtra("dept", department);
			i2.putExtra("reg", regulation);
			startActivity(i2);
			break;
		case R.id.btCalcInternal:
			Intent i21 = new Intent(OptionSelect.this, InternalMarkCalc.class);
			startActivity(i21);
			break;

		default:
			break;
		}
	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long arg3) {

		switch (parent.getId()) {
		case R.id.spDept:
			department = parent.getItemAtPosition(pos).toString();
			break;

		case R.id.spReg:
			regulation = parent.getItemAtPosition(pos).toString();
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_gethelp:
			Intent i = new Intent(OptionSelect.this, GettingStarted.class);
			startActivity(i);
			break;
		case R.id.menu_feedback:
			Intent i1 = new Intent(OptionSelect.this, FeedBackMail.class);
			startActivity(i1);
			break;
		case R.id.menu_aboutus:
			Intent i12 = new Intent(OptionSelect.this, AboutUs.class);
			startActivity(i12);
			break;
		case R.id.menu_exit:
			AppExit();
			break;
		case R.id.menu_rateus:
			Uri localUri = Uri.parse("market://details?id=com.vishnu.cgpa");
			Intent i11 = new Intent("android.intent.action.VIEW", localUri);
			startActivity(i11);
			break;

		}

		return false;
	}

	public void AppExit() {

		this.finish();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		/*
		 * use this if you want to kill your activity. But its not a good one to
		 * do.
		 */

	}
}
