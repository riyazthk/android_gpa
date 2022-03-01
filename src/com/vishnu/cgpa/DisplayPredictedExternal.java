package com.vishnu.cgpa;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

public class DisplayPredictedExternal extends Activity {

	String mymsg="Minimum Required Marks : "+"\n";
	TextView mytext;
	
	int temp_ret2,temp_ret3,temp_ret4,print_check=0 ,print_check1=0;
	int m1,temp1,temp11,temp2,temp22,temp3,temp33,temp4,temp44,temp5,temp55,temp6,temp66;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_predicted_external);
		mytext = (TextView) findViewById(R.id.tvDisplayExtSuggestion);
		m1 = getIntent().getExtras().getInt("m1");
		if(m1!=-1){
			mymsg += "\n"+"Subject 1 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("m2");	
		if(m1!=-1){
			mymsg += "\n"+"Subject 2 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("m3");	
		if(m1!=-1){
			mymsg += "\n"+"Subject 3 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("m4");	
		if(m1!=-1){
			mymsg += "\n"+"Subject 4 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("m5");	
		if(m1!=-1){
			mymsg += "\n"+"Subject 5 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("m6");	
		if(m1!=-1){
			mymsg += "\n"+"Subject 6 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("labm1");	
		if(m1!=-1){
			mymsg += "\n"+"Lab 1 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("labm2");	
		if(m1!=-1){
			mymsg += "\n"+"Lab 2 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}	
		m1 = getIntent().getExtras().getInt("labm3");	
		if(m1!=-1){
			mymsg += "\n"+"Lab 3 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
		m1 = getIntent().getExtras().getInt("labm4");	
		if(m1!=-1){
			mymsg += "\n"+"Lab 4 (Internal: "+ m1+" )"+"\n";
			externMarkCalculation();
		}
	
	mytext.setTextColor(Color.BLACK);
	mytext.setText(mymsg);

			
	}

	

	private void externMarkCalculation() {
		// TODO Auto-generated method stub
		
				
			
			temp11 = (90-m1)*2;
		
			if(temp11<=100 && temp11>=50)	
				mymsg += "Required for S grade : "+temp11 +"\n";
		
			else
			{ 
			temp11=	callback(temp11);
			if(temp11==0)
				mymsg += "Required for S grade : NA" +"\n";
			else if(temp11==50)
			{
		
				terminate1();
			
			}	
			else
			mymsg += "Required for S grade : "+temp11 +"\n";
			}
			
			
			
			
			
			
				temp22 = (80-m1)*2;

			if(temp22<=100 && temp22>=50)	
				mymsg += "Required for A grade : "+temp22 +"\n";

      		 else 
		      { 
		     temp22=	callback(temp22);
		     if(temp22==0)
			  mymsg += "Required for A grade : NA" +"\n";
		      else if(temp22==50)
		      {
			
			    terminate2();
		
		      }
	    	else
	     	mymsg += "Required for A grade : "+temp22 +"\n";
	     	}
		
			
			
			
		 if(m1>26 && m1<30)
					temp33 = (70-m1)*2;
					
				else
			temp33 = (70-m1)*2;
			
			if(temp33<=100 && temp33>=50)
			{
				System.out.println("Came main at temp33 or B");
				mymsg += "Required for B grade : "+temp33 +"\n";
				print_check=0;
				if(temp33==50 && print_check==0)
					terminate3();
					
			}
			else
			{ 
			temp33=	callback(temp33);
		
			if(temp33==0 )
			{
	          
				if(print_check==1)
				{
				print_check=0;
				
				
				mymsg += "Required for B grade : NA" +"\n";
				}
			}
			else if(temp33==50 && print_check==0 )
			{
				System.out.println("Came temp33");
				mymsg += "Required for B grade : "+temp33 +"\n";
				print_check=1;
				terminate3();
			
			}
			else
			mymsg += "Required for B grade : "+temp33 +"\n";
			}	
				
			
			
				if(m1>26 && m1<30)
				{
					temp44 = (60-m1)*2;
				}
				
				else
				{
			     temp44 = (60-m1)*2;
				}
			if(temp44<=100 && temp44>=50)	
			{
				
				System.out.println("Came temp44 at main or C");
				mymsg += "Required for C grade : "+temp44 +"\n";
				print_check=0;
				if(temp44==50 && print_check==0)
					terminate4();
			}
			else
			{ 
			temp44=	callback(temp44);
			System.out.println("Came temp44 else");
			//print_check=0;
			if(temp44==0)
			{
				System.out.println("Came temp44 else inside if");
			if(print_check==1)
				{
				print_check=1;
				System.out.println("Came temp44 else inside if print_check");
				
				mymsg += "Required for C grade : NA" +"\n";
				}
			}
			else if(temp44==50 && print_check==0)
			{
				System.out.println("Came temp44 else if");
				
				mymsg += "Required for C grade : "+temp44 +"\n";
				
				terminate4();
			
			}	
			
			}
			
			
			if(m1<26)
			{
			
			temp55 = (55-m1)*2;
			}
				else if(m1<30)
				{
					temp55 = (55-m1)*2;
				}	
			
				else
				{
					temp55 = (55-m1)*2;
				}
			
			
			if(temp55<=100 && temp55>=50)
			{
				System.out.println("Came temp55 at main or C");
			
				mymsg += "Required for D grade : "+temp55 +"\n";
				print_check=0;
				if(temp55==50 && print_check==0)
					terminate5();
			}
			
			else
			{ 
				System.out.println("Came temp55 else ");
			temp55=	callback(temp55);
			if(temp55==0)
			{
				System.out.println("Came temp55 else inside if");
				if(print_check==1)
				{
				print_check=1;
				
				mymsg += "Required for D grade : NA" +"\n";
				}
			}
				
			else if(temp55==50 && print_check==0)
			{
				System.out.println("Came temp55 else if");
				mymsg += "Required for D grade : "+temp55 +"\n";
				print_check=1;
				terminate5();
			
			}
			else
			System.out.println("Summa 1");
				
			} 		
	
			
			
			
		
			if(m1<26)
				temp66 = (50-m1)*2;
			else if(m1<30)
				temp66=25*2;
				
			else
		     temp66=(50-m1)*2;
			
			
			if(temp66<=100 && temp66>=50)	
			{
				
				System.out.println("Came temp66 at main or E");
				mymsg += "Required for E grade : "+temp66 +"\n";
				
					
			}
			
			else
			{ 
			temp66=	callback(temp66);
			if(temp66==0 )
			{
				System.out.println("Came temp66 inside if");
				if(print_check==1)
				{
				print_check=1;
				
				mymsg += "Required for E grade : NA" +"\n";
				}
			}
			else if(temp66==50 && print_check==0)
			{
				System.out.println("Came temp66 elseif");
				mymsg += "Required for E grade : NA" +"\n";
				
			
			}
		
			}
					
				
		

	}


	private void terminate5() {
		// TODO Auto-generated method stub
	 
		mymsg+=	"Required for E grade : NA" +"\n";
		print_check=1;
	mytext.setTextColor(Color.BLACK);
		mytext.setText(mymsg);

		
	}



	private void terminate4() {
		// TODO Auto-generated method stub
		
	mymsg+=	"Required for D grade : NA"+"\n"+"Required for E grade : NA" +"\n" ;

		print_check=1;
	
		mytext.setTextColor(Color.BLACK);
		mytext.setText(mymsg);
	}



	private void terminate3() {
		// TODO Auto-generated method stub
		  mymsg += "Required for C grade : NA" +"\n";
	
			  print_check=1;
			  mymsg+="Required for D grade : NA" +"\n"+"Required for E grade : NA" +"\n";
		
		mytext.setTextColor(Color.BLACK);
		mytext.setText(mymsg);
		
	}



	private void terminate2() {
		// TODO Auto-generated method stub

		mymsg+="Required for B grade : NA" +"\n";
		
		print_check=1;
		mymsg+="Required for C grade : NA" +"\n"+"Required for D grade : NA" +"\n"+"Required for E grade : NA" +"\n";
		
		mytext.setTextColor(Color.BLACK);
		mytext.setText(mymsg);
		
	}



	private void terminate1() {
		// TODO Auto-generated method stub
		mymsg += "Required for A grade : NA" +"\n"+"Required for B grade : NA" +"\n"+"Required for C grade : NA" +"\n"+"Required for D grade : NA" +"\n"+"Required for E grade : NA" +"\n";
		mytext.setTextColor(Color.BLACK);
		mytext.setText(mymsg);
	}







	public int callback(int temp_ret1) {
		// TODO Auto-generated method stub
	
		if(temp_ret1>=0 && temp_ret1<50  )
		{
			
		temp_ret2=50-temp_ret1;
		temp_ret3=temp_ret2+temp_ret1;
		return temp_ret3;
		}
	    
		else
		{
		
		 
			return 0;
		}
		 
		
		
	}
	

	

}
