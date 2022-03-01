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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CGPACalc extends Activity implements OnClickListener, TextWatcher {

	AdView adView;
	EditText g1,g2,g3,g4,g5,g6,g7,g8;
	TextView cgpa_result,mydept;
	Button b1,savecgpa,viewstoredcgpa,bargraph;
	float cgpa;
	String[]creditArray;
	String regulation,department;
	TextView dispc1,dispc2,dispc3,dispc4,dispc5,dispc6,dispc7,dispc8;
	int d1,d2,d3,d4,d5,d6,d7,d8;
	float a1,a2,a3,a4,a5,a6,a7,a8;
	
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
        setContentView(R.layout.activity_cgpa);
        
        adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
        regulation = getIntent().getExtras().getString("reg");
        department = getIntent().getExtras().getString("dept");
        g1=(EditText)findViewById(R.id.etSem1);
        g2=(EditText)findViewById(R.id.etSem2);
        g3=(EditText)findViewById(R.id.etSem3);
        g4=(EditText)findViewById(R.id.etSem4);
        g5=(EditText)findViewById(R.id.etSem5);
        g6=(EditText)findViewById(R.id.etSem6);
        g7=(EditText)findViewById(R.id.etSem7);
        g8=(EditText)findViewById(R.id.etSem8);
        //Set text watches for Edittexts
        g1.addTextChangedListener(this);
        g2.addTextChangedListener(this);
        g3.addTextChangedListener(this);
        g4.addTextChangedListener(this);
        g5.addTextChangedListener(this);
        g6.addTextChangedListener(this);
        g7.addTextChangedListener(this);
        g8.addTextChangedListener(this);
        
        //Initialize credits textview
        dispc1 = (TextView) findViewById(R.id.tvDispCredit1);
        dispc2 = (TextView) findViewById(R.id.tvDispCredit2);
        dispc3 = (TextView) findViewById(R.id.tvDispCredit3);
        dispc4 = (TextView) findViewById(R.id.tvDispCredit4);
        dispc5 = (TextView) findViewById(R.id.tvDispCredit5);
        dispc6 = (TextView) findViewById(R.id.tvDispCredit6);
        dispc7 = (TextView) findViewById(R.id.tvDispCredit7);
        dispc8 = (TextView) findViewById(R.id.tvDispCredit8);
        
        mydept = (TextView) findViewById(R.id.tvmydept);
        cgpa_result=(TextView)findViewById(R.id.tvCGPA);
        b1=(Button)findViewById(R.id.btCalcCgpa);
        viewstoredcgpa =(Button)findViewById(R.id.btViewSavedCgpa);
        savecgpa = (Button) findViewById(R.id.btSaveCgpaToDb);
        bargraph = (Button) findViewById(R.id.btDisplayBarGraph);
        b1.setOnClickListener(this);
        savecgpa.setOnClickListener(this);
        viewstoredcgpa.setOnClickListener(this);
        bargraph.setOnClickListener(this);
        //Initialize grades as per the selected Dept
        initializegrades();
      //Display a permanent menu  key on the Action bar
      getOverflowMenu();
      mydept.setText(department+"("+regulation+")");
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cgpacalc, menu);
        return true;
    }
    
    private void getOverflowMenu() {

	     try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public void AppExit()
	{

	    this.finish();
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    startActivity(intent);

	    int pid = android.os.Process.myPid();
	    android.os.Process.killProcess(pid);
	    /* use this if you want to kill your activity. 
	     * But its not a good one to do.
	     */
	   	   

	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    switch (item.getItemId()) {
	case R.id.menu_aboutus:
		Intent i = new Intent(CGPACalc.this, AboutUs.class);
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
	
	private void initializegrades() {
		// TODO Auto-generated method stub
		
		if (regulation.equals("2009")) {
			
			if (department.equals("Civil Engineering")) {
				d1=21;d2=22;d3=24;d4=24;d5=24;d6=25;d7=22;d8=22;
			} else if (department.equals("Computer Science and Engineering")){
				//Toast.makeText(CGPACalc.this, "CSE-2009",Toast.LENGTH_LONG).show();
				d1=21;d2=22;d3=24;d4=25;d5=23;d6=23;d7=21;d8=21;
			}else if (department.equals("Chemical Engineering")){
				d1=21;d2=22;d3=25;d4=23;d5=25;d6=25;d7=21;d8=21;
			}else if (department.equals("Electronics and Communication Engineering")){
				d1=21;d2=23;d3=25;d4=25;d5=25;d6=23;d7=21;d8=21;
			}else if (department.equals("Electrical Engineering")){
				d1=21;d2=23;d3=25;d4=26;d5=23;d6=22;d7=23;d8=21;
			}else if (department.equals("Electronics and Instrumentation")){
				d1=21;d2=23;d3=23;d4=25;d5=22;d6=23;d7=22;d8=21;
			}else if (department.equals("Information Technology")){
				d1=21;d2=22;d3=24;d4=25;d5=24;d6=23;d7=23;d8=21;
			}else if (department.equals("Food Technology")){
				d1=21;d2=22;d3=24;d4=25;d5=24;d6=22;d7=23;d8=21;
			}else if (department.equals("Mechanical Engineering")){
				d1=21;d2=22;d3=25;d4=25;d5=24;d6=24;d7=23;d8=21;
			}else if (department.equals("Mechatronics")){
				d1=21;d2=22;d3=26;d4=25;d5=23;d6=22;d7=22;d8=21;
			}else if (department.equals("Master of Business Admn.(MBA)")){
				d1=27;d2=26;d3=25;d4=12;d5=0;d6=0;d7=0;d8=0;
			}else if (department.equals("Master of Computer Appl.(MCA)")){
				d1=22;d2=19;d3=18;d4=20;d5=19;d6=12;d7=0;d8=0;
			}				
			
			
			
		} else if(regulation.equals("2011")) {
			
			if (department.equals("Civil Engineering")) {
				d1=21;d2=22;d3=23;d4=24;d5=26;d6=24;d7=22;d8=22;
			} else if (department.equals("Computer Science and Engineering")){
				d1=21;d2=22;d3=24;d4=25;d5=24;d6=22;d7=21;d8=21;
			}else if (department.equals("Chemical Engineering")){
				d1=21;d2=23;d3=25;d4=23;d5=25;d6=23;d7=23;d8=21;
			}else if (department.equals("Electronics and Communication Engineering")){
				d1=21;d2=22;d3=25;d4=25;d5=25;d6=24;d7=20;d8=21;
			}else if (department.equals("Electrical Engineering")){
				d1=21;d2=23;d3=25;d4=26;d5=23;d6=24;d7=22;d8=21;
			}else if (department.equals("Electronics and Instrumentation")){
				d1=21;d2=23;d3=25;d4=24;d5=22;d6=24;d7=22;d8=21;
			}else if (department.equals("Information Technology")){
				d1=21;d2=22;d3=24;d4=24;d5=24;d6=22;d7=23;d8=21;
			}else if (department.equals("Food Technology")){
				d1=21;d2=22;d3=24;d4=25;d5=23;d6=24;d7=21;d8=21;
			}else if (department.equals("Mechanical Engineering")){
				d1=21;d2=22;d3=25;d4=25;d5=25;d6=25;d7=21;d8=21;
			}else if (department.equals("Mechatronics")){
				d1=22;d2=22;d3=26;d4=26;d5=23;d6=22;d7=23;d8=21;
			}else if (department.equals("Master of Business Admn.(MBA)")){
				d1=24;d2=21;d3=25;d4=20;d5=0;d6=0;d7=0;d8=0;
			}else if (department.equals("Master of Computer Appl.(MCA)")){
				d1=20;d2=20;d3=20;d4=20;d5=20;d6=12;d7=0;d8=0;
			}
			
		}
		
		//Set the corresponding grades to the text views
		dispc1.setText(""+d1);
		dispc2.setText(""+d2);
		dispc3.setText(""+d3);
		dispc4.setText(""+d4);
		dispc5.setText(""+d5);
		dispc6.setText(""+d6);
		dispc7.setText(""+d7);
		dispc8.setText(""+d8);
	}


	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btDisplayBarGraph:
			float allgpa[]= new float[8];
			
			 if(!(g1.getText().toString().equals("")))    
				 allgpa[0]=Float.valueOf(g1.getText().toString());
		        if(!(g2.getText().toString().equals("")))
		        allgpa[1]=Float.valueOf(g2.getText().toString());
		        if(!(g3.getText().toString().equals("")))
		        	allgpa[2]=Float.valueOf(g3.getText().toString());
		        if(!(g4.getText().toString().equals("")))
		        	allgpa[3]=Float.valueOf(g4.getText().toString());
		        if(!(g5.getText().toString().equals("")))
		        	allgpa[4]=Float.valueOf(g5.getText().toString());
		        if(!(g6.getText().toString().equals("")))
		        	allgpa[5]=Float.valueOf(g6.getText().toString());
		        if(!(g7.getText().toString().equals("")))
		        	allgpa[6]=Float.valueOf(g7.getText().toString());
		        if(!(g8.getText().toString().equals("")))
		        	allgpa[7]=Float.valueOf(g8.getText().toString());
			
			Intent i2 = new Intent(CGPACalc.this, CgpaBarGraph.class);
			i2.putExtra("allgpa", allgpa);
			startActivity(i2);
			
			break;
		case R.id.btViewSavedCgpa:
			Intent i = new Intent(CGPACalc.this, ListSavedCgpa.class);
			startActivity(i);
			break;
		case R.id.btSaveCgpaToDb:
			
			//Store the calculated CGPA into the DB
			final CgpaDatabaseHelper myhelper = new CgpaDatabaseHelper(this);
			
			//Input a name from the user to save it
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Save CGPA");
			alert.setMessage("Entert Name to Store it");
			// Set an EditText view to get user input 
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Do something with value!
				String CgpaEntryName  = input.getText().toString();
				if(!CgpaEntryName.equals("")){
					//If the returned result is not empty then Proceed
					if(myhelper.addCgpa(CgpaEntryName,department, regulation, a1, a2, a3, a4, 
							a5, a6, a7, a8, cgpa)>0){
						Toast.makeText(CGPACalc.this, "Entry Saved Successfully", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(CGPACalc.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
					}
						
					
				}else{
					//Empty String Returned
					Toast.makeText(CGPACalc.this, "Name Cannot be Empty", Toast.LENGTH_LONG).show();
				}
				
			
			  }
			});

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
				  dialog.cancel();
			  }
			}).show();

			
			
	
			
			break;
		case R.id.btCalcCgpa:
			initializegrades();
        	a1=0;a2=0;a3=0;a4=0;a5=0;a6=0;a7=0;a8=0;
        	
        if(!(g1.getText().toString().equals("")))    
            a1=Float.valueOf(g1.getText().toString());
        if(!(g2.getText().toString().equals("")))
            a2=Float.valueOf(g2.getText().toString());
        if(!(g3.getText().toString().equals("")))
            a3=Float.valueOf(g3.getText().toString());
        if(!(g4.getText().toString().equals("")))
            a4=Float.valueOf(g4.getText().toString());
        if(!(g5.getText().toString().equals("")))
            a5=Float.valueOf(g5.getText().toString());
        if(!(g6.getText().toString().equals("")))
            a6=Float.valueOf(g6.getText().toString());
        if(!(g7.getText().toString().equals("")))
            a7=Float.valueOf(g7.getText().toString());
        if(!(g8.getText().toString().equals("")))
            a8=Float.valueOf(g8.getText().toString());
      
        
            if(a1==0)d1=0;
            if(a2==0)d2=0;
            if(a3==0)d3=0;
            if(a4==0)d4=0;
            if(a5==0)d5=0;
            if(a6==0)d6=0;
            if(a7==0)d7=0;
            if(a8==0)d8=0;
            cgpa_result.setVisibility(View.VISIBLE);
            cgpa_result.setTextColor(Color.RED);
            if((a1==0 || (a1>=5 && a1<=10)) && (a2==0 || ( a2>=5 && a2<=10)) && 
           (a3==0 || (a3>=5 && a3<=10))  &&(a4==0 || (a4>=5 && a4<=10)) 
           && (a5==0 || (a5>=5 && a5<=10)) && (a6==0 || (a6>=5 && a6<=10)) 
           && (a7==0 || (a7>=5 && a7<=10)) && (a8==0 || (a8>=5 && a8<=10))){
                cgpa=((a1*d1)+(a2*d2)+(a3*d3)+(a4*d4)+(a5*d5)+(a6*d6)+(a7*d7)+(a8*d8))
                        /(d1+d2+d3+d4+d5+d6+d7+d8);
                System.out.println("cgps is"+cgpa);
                //Rounding off to 2 decimals
                cgpa = Math.round(cgpa*100F)/100F;
                cgpa_result.setText("Your CGPA  is : " + cgpa);
                if(cgpa>0)
                	savecgpa.setEnabled(true);
                else savecgpa.setEnabled(false);
            }
            
            else
            	cgpa_result.setText("GPA should be not be >10 and <5");
			
			
			
			break;

		
		}
		
		
	}


	@Override
	public void afterTextChanged(Editable edit) {
		// TODO Auto-generated method stub
	//Automatically set maximum to 10 in case of invalid GPA
	if(!(edit.toString().equals("") || edit.toString().equals("."))){
		float value = Float.parseFloat(edit.toString());
		try{
			if(value>10)
				edit.replace(0, edit.length(), "10.0");
			
		}catch(NumberFormatException e){}
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
