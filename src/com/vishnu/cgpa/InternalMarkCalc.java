package com.vishnu.cgpa;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class InternalMarkCalc extends Activity implements OnClickListener,
		TextWatcher {

	AdView adView;
	Button calcinternal, getattendancehelp;
	EditText mod1, mod2, mod3, assin, quiz, attendance;
	TextView dispinternal;
	Boolean check;
	float tot = 0, module1 = 0, module2 = 0, module3 = 0, assinmark = 0,
			quizmark = 0, attendance_mark = 0;

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
		setContentView(R.layout.activity_internal_mark_calc);

		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		calcinternal = (Button) findViewById(R.id.btDisplayInternalMark);
		getattendancehelp = (Button) findViewById(R.id.btGetAttendanceHelp);
		mod1 = (EditText) findViewById(R.id.etModule1);
		mod2 = (EditText) findViewById(R.id.etModule2);
		mod3 = (EditText) findViewById(R.id.etModule3);
		assin = (EditText) findViewById(R.id.etAssignmentMark);
		quiz = (EditText) findViewById(R.id.etQuizMark);
		attendance = (EditText) findViewById(R.id.etAttendanceMark);
		mod1.addTextChangedListener(this);
		mod2.addTextChangedListener(this);
		mod3.addTextChangedListener(this);
		assin.addTextChangedListener(this);
		quiz.addTextChangedListener(this);
		attendance.addTextChangedListener(this);

		dispinternal = (TextView) findViewById(R.id.tvInternalMarkResult);
		calcinternal.setOnClickListener(this);
		getattendancehelp.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option_select, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_gethelp:
			Intent i = new Intent(this, GettingStarted.class);
			startActivity(i);
			break;
		case R.id.menu_feedback:
			Intent i1 = new Intent(this, FeedBackMail.class);
			startActivity(i1);
			break;
		case R.id.menu_aboutus:
			Intent i12 = new Intent(this, AboutUs.class);
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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btGetAttendanceHelp:
			new AlertDialog.Builder(this)
					.setTitle("Attendance Details")
					.setMessage(
							"Attendance  80%- 1 mark" + "\n"
									+ "Attendance >80 and <=85%- 2 marks"
									+ "\n"
									+ "Attendance >85 and <=90%- 3 marks"
									+ "\n"
									+ "Attendance >90 and <=95%- 4 marks"
									+ "\n"
									+ "Attendance >95 and <=100%- 5 marks")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
			break;
		case R.id.btDisplayInternalMark:
			if (!(mod1.getText().toString().equals("")
					|| mod2.getText().toString().equals("")
					|| mod3.getText().toString().equals("")
					|| assin.getText().toString().equals("")
					|| quiz.getText().toString().equals("") || attendance
					.getText().toString().equals(""))) {
				module1 = (Float.parseFloat(mod1.getText().toString())) / 5;
				module2 = (Float.parseFloat(mod2.getText().toString())) / 5;
				module3 = (Float.parseFloat(mod3.getText().toString())) / 5;
				assinmark = Float.parseFloat(assin.getText().toString());
				quizmark = Float.parseFloat(quiz.getText().toString());
				attendance_mark = Float.parseFloat(attendance.getText()
						.toString());
				if (checkvalidation())
					calculateInternal();
			} else {
				new AlertDialog.Builder(this)
						.setTitle("ERROR!!!")
						.setMessage("Fill all Details")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
			}

			break;
		}
	}

	private boolean checkvalidation() {
		// TODO Auto-generated method stub
		check = true;
		String errormsg = "";
		if (module1 > 50 || module1 < 0) {
			errormsg = "Module 1 Mark must be between 0 and 50";
			check = false;
		}
		if (module2 > 50 || module2 < 0) {
			errormsg += "\n" + "Module 1 Mark must be between 0 and 50";
			check = false;
		}
		if (module3 > 50 || module3 < 0) {
			errormsg += "\n" + "Module 1 Mark must be between 0 and 50";
			check = false;
		}
		if (assinmark > 10 || assinmark < 0) {
			errormsg += "\n" + "Assignment Mark must be between 0 and 10";
			check = false;
		}
		if (quizmark > 5 || assinmark < 0) {
			errormsg += "\n" + "QUIZ Mark must be between 0 and 5";
			check = false;
		}
		if (attendance_mark > 5 || attendance_mark < 0) {
			errormsg += "\n" + "ATTENDANCE Mark must be between 0 and 5";
			check = false;
		}

		if (!check) {
			new AlertDialog.Builder(this)
					.setTitle("WRONG INPUT!!!")
					.setMessage(errormsg)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
		}
		return check;
	}

	private void calculateInternal() {
		// TODO Auto-generated method stub

		tot = module1 + module2 + module3 + assinmark + quizmark
				+ attendance_mark;
		dispinternal.setTextColor(Color.RED);
		dispinternal.setText("Internal Mark is : " + tot + "/50");
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (!(s.toString().equals("") || s.toString().equals("."))) {
			float value = Float.parseFloat(s.toString());
			if (mod1.getText().hashCode() == s.hashCode()) {
				if (value > 50)
					s.replace(0, s.length(), "50.0");
			} else if (mod2.getText().hashCode() == s.hashCode()) {
				if (value > 50)
					s.replace(0, s.length(), "50.0");
			} else if (mod3.getText().hashCode() == s.hashCode()) {
				if (value > 50)
					s.replace(0, s.length(), "50.0");
			} else if (assin.getText().hashCode() == s.hashCode()) {
				if (value > 10)
					s.replace(0, s.length(), "10.0");
			} else if (quiz.getText().hashCode() == s.hashCode()) {
				if (value > 5)
					s.replace(0, s.length(), "05");
			} else if (attendance.getText().hashCode() == s.hashCode()) {
				if (value > 5)
					s.replace(0, s.length(), "05");
			}
		}

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

}
