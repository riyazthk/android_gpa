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
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GPACalc extends Activity implements OnClickListener,
		OnItemSelectedListener {

	AdView adView;
	String regulation, department;
	int semester;
	Boolean isthisanarrear;
	float arrearexceptions;
	int whgradezero, noofarrear;
	int[] arrears = new int[11];
	int[] cc = new int[11];
	int[] gg = new int[11];
	float gpa;
	TextView mydept, mygpa;
	Spinner credit1, credit2, credit3, credit4, credit5, credit6, labcredit1,
			labcredit2, labcredit3, labcredit4;
	Spinner grade1, grade2, grade3, grade4, grade5, grade6, labgrade1,
			labgrade2, labgrade3, labgrade4;
	Button calc_gpa, savegpa, viewgpa;
	int g1, g2, g3, g4, g5, g6, glab1, glab2, glab3, glab4, c1, c2, c3, c4, c5,
			c6, clab1, clab2, clab3, clab4;
	int first_count = 0;

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
	protected void onResume() {
		if (adView != null) {
			adView.resume();
		}
		super.onResume();
		mygpa.setVisibility(View.INVISIBLE);
		savegpa.setEnabled(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpacalculator);

		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		regulation = getIntent().getExtras().getString("reg");
		department = getIntent().getExtras().getString("dept");
		// Display a permanent menu key on the Action bar
		getOverflowMenu();
		mydept = (TextView) findViewById(R.id.tvGPAMyDept);
		mygpa = (TextView) findViewById(R.id.tvMyGpa);
		mydept.setText(department + "( " + regulation + " )");

		calc_gpa = (Button) findViewById(R.id.btCalcGPA);
		savegpa = (Button) findViewById(R.id.btSaveGpaToDb);
		viewgpa = (Button) findViewById(R.id.btViewSavedGpa);
		calc_gpa.setOnClickListener(this);
		savegpa.setOnClickListener(this);
		viewgpa.setOnClickListener(this);

		// mysemester = (Spinner) findViewById(R.id.spGPASem);
		credit1 = (Spinner) findViewById(R.id.spCredit1);
		credit2 = (Spinner) findViewById(R.id.spCredit2);
		credit3 = (Spinner) findViewById(R.id.spCredit3);
		credit4 = (Spinner) findViewById(R.id.spCredit4);
		credit5 = (Spinner) findViewById(R.id.spCredit5);
		credit6 = (Spinner) findViewById(R.id.spCredit6);
		labcredit1 = (Spinner) findViewById(R.id.splabCredit1);
		labcredit2 = (Spinner) findViewById(R.id.splabCredit2);
		labcredit3 = (Spinner) findViewById(R.id.splabCredit3);
		labcredit4 = (Spinner) findViewById(R.id.splabCredit4);
		// mysemester.setOnItemSelectedListener(this);
		credit1.setOnItemSelectedListener(this);
		credit2.setOnItemSelectedListener(this);
		credit3.setOnItemSelectedListener(this);
		credit4.setOnItemSelectedListener(this);
		credit5.setOnItemSelectedListener(this);
		credit6.setOnItemSelectedListener(this);
		labcredit1.setOnItemSelectedListener(this);
		labcredit2.setOnItemSelectedListener(this);
		labcredit3.setOnItemSelectedListener(this);
		labcredit4.setOnItemSelectedListener(this);

		// Set grade listener
		grade1 = (Spinner) findViewById(R.id.spGrade1);
		grade2 = (Spinner) findViewById(R.id.spGrade2);
		grade3 = (Spinner) findViewById(R.id.spGrade3);
		grade4 = (Spinner) findViewById(R.id.spGrade4);
		grade5 = (Spinner) findViewById(R.id.spGrade5);
		grade6 = (Spinner) findViewById(R.id.spGrade6);
		labgrade1 = (Spinner) findViewById(R.id.splabGrade1);
		labgrade2 = (Spinner) findViewById(R.id.splabGrade2);
		labgrade3 = (Spinner) findViewById(R.id.splabGrade3);
		labgrade4 = (Spinner) findViewById(R.id.splabGrade4);
		grade1.setOnItemSelectedListener(this);
		grade2.setOnItemSelectedListener(this);
		grade3.setOnItemSelectedListener(this);
		grade4.setOnItemSelectedListener(this);
		grade5.setOnItemSelectedListener(this);
		grade6.setOnItemSelectedListener(this);
		labgrade1.setOnItemSelectedListener(this);
		labgrade2.setOnItemSelectedListener(this);
		labgrade3.setOnItemSelectedListener(this);
		labgrade4.setOnItemSelectedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option_select, menu);
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

		/*
		 * use this if you want to kill your activity. But its not a good one to
		 * do.
		 */

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_gethelp:
			Intent i = new Intent(GPACalc.this, GettingStarted.class);
			startActivity(i);
			break;
		case R.id.menu_aboutus:
			Intent i11 = new Intent(GPACalc.this, AboutUs.class);
			startActivity(i11);
			break;
		case R.id.menu_exit:
			AppExit();
			break;
		case R.id.menu_rateus:
			Uri localUri = Uri.parse("market://details?id=com.vishnu.cgpa");
			Intent i1 = new Intent("android.intent.action.VIEW", localUri);
			startActivity(i1);
			break;
		case R.id.menu_feedback:
			Intent i111 = new Intent(this, FeedBackMail.class);
			startActivity(i111);
			break;

		}
		return false;
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btSaveGpaToDb:
			// Store the calculated CGPA into the DB
			final GpaDatabaseHelper myhelper = new GpaDatabaseHelper(this);

			// Input a name from the user to save it
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Save GPA");
			alert.setMessage("Entert Name to Store it");
			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Do something with value!
							String CgpaEntryName = input.getText().toString();
							if (!CgpaEntryName.equals("")) {
								// If the returned result is not empty then
								// Proceed
								if (myhelper.addCgpa(CgpaEntryName, department,
										regulation, g1, g2, g3, g4, g5, g6,
										glab1, glab2, glab3, glab4, gpa) > 0) {
									Toast.makeText(GPACalc.this,
											"Entry Saved Successfully",
											Toast.LENGTH_SHORT).show();
								} else {
									Toast.makeText(GPACalc.this,
											"Insertion Failed",
											Toast.LENGTH_SHORT).show();
								}

							} else {
								// Empty String Returned
								Toast.makeText(GPACalc.this,
										"Name Cannot be Empty",
										Toast.LENGTH_LONG).show();
							}

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
							dialog.cancel();
						}
					}).show();

			break;

		case R.id.btViewSavedGpa:
			Intent i1 = new Intent(GPACalc.this, ListSavedGpa.class);
			startActivity(i1);

			break;

		case R.id.btCalcGPA:

			mygpa.setTextColor(Color.RED);
			noofarrear = 0;
			for (int i = 1; i < 11; i++) {
				if (gg[i] == 0) {
					arrears[++noofarrear] = i;
				}

			}
			if (noofarrear > 0) {
				float ranumerator = 0;
				float ratotcredit = 0;
				String arrearboystext = "";
				float ragpa;
				for (int ii = 1; ii < 11; ii++) {
					isthisanarrear = false;
					ratotcredit += cc[ii];
					for (int k = 1; k < noofarrear; k++) {
						if (arrears[k] == ii) {
							isthisanarrear = true;
						}
					}
					if (!isthisanarrear) {

						ranumerator = ranumerator + (cc[ii] * gg[ii]);
					}

				}

				arrearboystext += "Your grade is 0.0 " + "\n"
						+ " Some sugestions " + "\n" + "for grade \t"
						+ "Expected Gpa" + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 5);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "E                          " + ragpa + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 6);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "D                          " + ragpa + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 7);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "C                          " + ragpa + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 8);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "B                          " + ragpa + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 9);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "A                          " + ragpa + "\n";
				arrearexceptions = 0;
				for (int h = 1; h <= noofarrear; h++) {
					arrearexceptions += ((cc[arrears[h]]) * 10);

				}
				ragpa = (ranumerator + arrearexceptions) / ratotcredit;
				ragpa = Math.round(ragpa * 100F) / 100F;

				arrearboystext += "S                          " + ragpa + "\n";

				Intent i = new Intent(GPACalc.this,
						Display_GPA_Prediction.class);
				i.putExtra("RA", arrearboystext);
				startActivity(i);
			} else {
				float totcredit = c1 + c2 + c3 + c4 + c5 + c6 + clab1 + clab2
						+ clab3 + clab4;
				float numerator = (c1 * g1) + (c2 * g2) + (c3 * g3) + (c4 * g4)
						+ (c5 * g5) + (c6 * g6) + (clab1 * glab1)
						+ (clab2 * glab2) + (clab3 * glab3) + (clab4 * glab4);

				gpa = numerator / totcredit;
				// Round off to two Decimal
				gpa = Math.round(gpa * 100F) / 100F;
				mygpa.setVisibility(View.VISIBLE);
				mygpa.setText("Your GPA is : " + gpa);
				if (gpa > 0)
					savegpa.setEnabled(true);
				else
					savegpa.setEnabled(false);
			}

			break;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long arg3) {
		// TODO Auto-generated method stub

		switch (parent.getId()) {

		case R.id.spCredit1:
			c1 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c1 == 0) {
				grade1.setEnabled(false);
				g1 = 0;
			} else {

				grade1.setEnabled(true);
				g1 = getGrade(grade1.getSelectedItem().toString());
			}
			cc[1] = c1;
			break;
		case R.id.spCredit2:
			c2 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c2 == 0) {
				grade2.setEnabled(false);
				g2 = 0;
			} else {
				grade2.setEnabled(true);
				g2 = getGrade(grade2.getSelectedItem().toString());
			}
			cc[2] = c2;
			break;
		case R.id.spCredit3:
			c3 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c3 == 0) {
				grade3.setEnabled(false);
				g3 = 0;
			} else {
				grade3.setEnabled(true);
				g3 = getGrade(grade3.getSelectedItem().toString());
			}
			cc[3] = c3;
			break;
		case R.id.spCredit4:
			c4 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c4 == 0) {
				grade4.setEnabled(false);
				g4 = 0;
			} else {
				grade4.setEnabled(true);
				g4 = getGrade(grade4.getSelectedItem().toString());
			}
			cc[4] = c4;
			break;
		case R.id.spCredit5:
			c5 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c5 == 0) {
				grade5.setEnabled(false);
				g5 = 0;
			} else {
				grade5.setEnabled(true);
				g5 = getGrade(grade5.getSelectedItem().toString());
			}
			cc[5] = c5;
			break;
		case R.id.spCredit6:
			c6 = getCredit(parent.getItemAtPosition(pos).toString());
			if (c6 == 0) {
				grade6.setEnabled(false);
				g6 = 0;
			} else {
				grade6.setEnabled(true);
				g6 = getGrade(grade6.getSelectedItem().toString());
			}
			cc[6] = c6;
			break;
		case R.id.splabCredit1:
			clab1 = getCredit(parent.getItemAtPosition(pos).toString());
			if (clab1 == 0) {
				labgrade1.setEnabled(false);
				glab1 = 0;
			} else {
				labgrade1.setEnabled(true);
				glab1 = getGrade(labgrade1.getSelectedItem().toString());
			}
			cc[7] = clab1;
			break;
		case R.id.splabCredit2:
			clab2 = getCredit(parent.getItemAtPosition(pos).toString());
			if (clab2 == 0) {
				labgrade2.setEnabled(false);
				glab2 = 0;
			} else {
				labgrade2.setEnabled(true);
				glab2 = getGrade(labgrade2.getSelectedItem().toString());
			}
			cc[8] = clab2;
			break;
		case R.id.splabCredit3:
			clab3 = getCredit(parent.getItemAtPosition(pos).toString());
			if (clab3 == 0) {
				labgrade3.setEnabled(false);
				glab3 = 0;
			} else {
				labgrade3.setEnabled(true);
				glab3 = getGrade(labgrade3.getSelectedItem().toString());
			}
			cc[9] = clab3;
			break;
		case R.id.splabCredit4:

			clab4 = getCredit(parent.getItemAtPosition(pos).toString());
			if (clab4 == 0) {
				labgrade4.setEnabled(false);
				glab4 = 0;
			} else {
				labgrade4.setEnabled(true);
				glab4 = getGrade(labgrade4.getSelectedItem().toString());
			}
			cc[10] = clab4;
			break;
		case R.id.spGrade1:
			g1 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[1] = g1;

			if (g1 == 0) {

				whgradezero = 1;
			}
			break;
		case R.id.spGrade2:
			g2 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[2] = g2;
			if (g2 == 0) {

				whgradezero = 2;
			}

			break;
		case R.id.spGrade3:
			g3 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[3] = g3;
			if (g3 == 0) {

				whgradezero = 3;
			}

			break;
		case R.id.spGrade4:
			g4 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[4] = g4;
			if (g4 == 0) {

				whgradezero = 4;
			}

			break;
		case R.id.spGrade5:
			g5 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[5] = g5;
			if (g5 == 0) {

				whgradezero = 5;
			}

			break;
		case R.id.spGrade6:
			g6 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[6] = g6;
			if (g6 == 0) {

				whgradezero = 6;
			}

			break;
		case R.id.splabGrade1:
			glab1 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[7] = glab1;
			if (glab1 == 0) {

				whgradezero = 7;
			}

			break;
		case R.id.splabGrade2:
			glab2 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[8] = glab2;
			if (glab2 == 0) {

				whgradezero = 8;
			}

			break;
		case R.id.splabGrade3:
			glab3 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[9] = glab3;
			if (glab3 == 0) {

				whgradezero = 9;
			}

			break;
		case R.id.splabGrade4:
			glab4 = getGrade(parent.getItemAtPosition(pos).toString());
			gg[10] = glab4;
			if (glab4 == 0) {

				whgradezero = 10;
			}

			break;
		default:
			break;

		}
	}

	private int getGrade(String mygrade) {
		// TODO Auto-generated method stub

		if (mygrade.equals("S"))
			return 10;
		else if (mygrade.equals("A"))
			return 9;
		else if (mygrade.equals("B"))
			return 8;
		else if (mygrade.equals("C"))
			return 7;
		else if (mygrade.equals("D"))
			return 6;
		else if (mygrade.equals("E"))
			return 5;
		else
			return 0;

	}

	private int getCredit(String mycredit) {
		// TODO Auto-generated method stub
		if (mycredit.equals("NA"))
			return 0;
		else
			return Integer.parseInt(mycredit);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
