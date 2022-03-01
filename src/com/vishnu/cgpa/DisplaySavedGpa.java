package com.vishnu.cgpa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySavedGpa extends Activity implements OnClickListener {

	ArrayList<String> result;
	int myid;
	TextView sub1,sub2,sub3,sub4,sub5,sub6,lab1,lab2,lab3,lab4,savedname,reg,dept,gpa;
	Button back,delete,export;
	GpaDatabaseHelper dbhelp;
	File sdCardFile,folder;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_saved_gpa);
myid = getIntent().getExtras().getInt("id");
		
		//Initialize
		savedname = (TextView) findViewById(R.id.tvSavedGpaName);
		reg= (TextView) findViewById(R.id.tvStoredRegulation);
		dept= (TextView) findViewById(R.id.tvStoredDept);
		sub1 = (TextView) findViewById(R.id.tvStoredSem1);
		sub2 = (TextView) findViewById(R.id.tvStoredSem2);
		sub3 = (TextView) findViewById(R.id.tvStoredSem3);
		sub4 = (TextView) findViewById(R.id.tvStoredSem4);
		sub5 = (TextView) findViewById(R.id.tvStoredSem5);
		sub6 = (TextView) findViewById(R.id.tvStoredSem6);
		lab1 = (TextView) findViewById(R.id.tvStoredLab1);
		lab2 = (TextView) findViewById(R.id.tvStoredLab2);
		lab3 = (TextView) findViewById(R.id.tvStoredLab3);
		lab4 = (TextView) findViewById(R.id.tvStoredLab4);
		gpa = (TextView) findViewById(R.id.tvStoredGpa);
		back = (Button) findViewById(R.id.btGraphCgpa);
		delete = (Button) findViewById(R.id.btDeleteEntry);
		export = (Button) findViewById(R.id.btExportGpaAsHtml);
		back.setOnClickListener(this);
		delete.setOnClickListener(this);
		export.setOnClickListener(this);
		displayResult();
		
	}
	
	private void displayResult() {
		// TODO Auto-generated method stub
		dbhelp = new GpaDatabaseHelper(this);
		result= dbhelp.getFullSavedGpa(myid);
		for (String s : result){
			System.out.println(s);
		}
		//Print the obtained results in TextView
		savedname.setText(result.get(0));
		dept.setText("Dept: "+result.get(1));
		reg.setText("Regulation: "+result.get(2));
		sub1.setText(getGrade(result.get(3)));
		sub2.setText(getGrade(result.get(4)));
		sub3.setText(getGrade(result.get(5)));
		sub4.setText(getGrade(result.get(6)));
		sub5.setText(getGrade(result.get(7)));
		sub6.setText(getGrade(result.get(8)));
		lab1.setText(getGrade(result.get(9)));
		lab2.setText(getGrade(result.get(10)));
		lab3.setText(getGrade(result.get(11)));
		lab4.setText(getGrade(result.get(12)));
		gpa.setText(result.get(13));
	}

	private String getGrade(String point) {
		// TODO Auto-generated method stub
		int pt = Integer.parseInt(point);
		if(pt==10)
			return "S";
		else if(pt==9)
			return "A";
		else if(pt==8)
			return "B";
		else if(pt==7)
			return "C";
		else if(pt==6)
			return "B";
		else if(pt==5)
			return "E";
		
		return "NA";
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.option_select, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
		case R.id.btExportGpaAsHtml:
			try {
				saveToFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "Write Failed", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				
			}
			
			break;
	
		case R.id.btDeleteEntry:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

		    builder.setTitle("Confirm Delete");
		    builder.setMessage("Are you sure, you want to delete?");

		    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

		        public void onClick(DialogInterface dialog, int which) {
		        	if(dbhelp.deleteEntry(myid)>0){
						Toast.makeText(DisplaySavedGpa.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
						finish();
						onBackPressed();
						/*Intent i = new Intent(DisplaySavedGpa.this, ListSavedGpa.class);
						startActivity(i);*/
					}else Toast.makeText(DisplaySavedGpa.this, "Delete Failed", Toast.LENGTH_SHORT).show();
		        }

		    });

		    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing
		            dialog.dismiss();
		        }
		    });

		    AlertDialog alert = builder.create();
		    alert.show();
			
			
			
			break;
	
		case R.id.btGraphCgpa:
			super.onBackPressed();
			break;
	
		}	
	}

	private void saveToFile() throws IOException  {
		FileWriter fWriter;
		//Boolean folderExists=true;
		folder = new File(Environment.getExternalStorageDirectory()+"/KEC GPA CGPA Calc/Saved GPA");
		if(!folder.exists()){
			//Check if folder exists or not..Otherwise create it
			if(folder.mkdirs())
					Toast.makeText(this, "New Folder Created", Toast.LENGTH_SHORT).show();
		}
			
		sdCardFile = new File(Environment.getExternalStorageDirectory()+"/KEC GPA CGPA Calc/Saved GPA/gpa_" +savedname.getText().toString()+".html");
		// TODO Auto-generated method stub
		fWriter = new FileWriter(sdCardFile, false);
		fWriter.write("<!DOCTYPE html><html><head><center><h1>Kongu Engineering College</h1></center>"
				+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
				"<head><body>");
		fWriter.append("<p>"+ dept.getText()+"</p>");
		fWriter.append("<p>"+ reg.getText()+"</p>");
		fWriter.append("<table width=\"100%\" border=\"1\" cellpadding=\"1\">");
		fWriter.append("<thead><tr><th>Subject</th><th>Grade</th></tr><thead>");
		fWriter.append("<tbody>");
		fWriter.append("<tr><td>semester1</td><td>"+sub1.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester2</td><td>"+sub2.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester3</td><td>"+sub3.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester4</td><td>"+sub4.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester5</td><td>"+sub5.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester6</td><td>"+sub6.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td colspan=\"2\" align=\"center\"><b>Labs</b></td></tr>");
		fWriter.append("<tr><td><b>lab</b></td><td><b>Grade</b></td></tr>");
		fWriter.append("<tr><td>lab1</td><td>"+lab1.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>lab2</td><td>"+lab2.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>lab3</td><td>"+lab3.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>lab4</td><td>"+lab4.getText().toString()+"</td></tr>");
		fWriter.append("</tbody>");
		fWriter.append("<tfoot><tr><th>GPA</th><th>"+gpa.getText().toString()+"</th></tr></tfoot></table>");
		fWriter.append("<p>Check out our apps: <a href=\"https://play.google.com/store/apps/developer?id=BalaVishnu\">visit now</a></p>");
		fWriter.append("</body></html>");
		fWriter.flush();
	    fWriter.close();
	    Toast.makeText(this, "File Written Successfully to "+sdCardFile.getPath(), Toast.LENGTH_LONG).show();
	 
	    //Ask to open the file
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);

	    builder.setTitle("Open the file?");
	    builder.setMessage("Are you sure, You want to open the file?");

	    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {
	        	 Intent localIntent = new Intent("android.intent.action.VIEW");
	             MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
	             localIntent.setDataAndType(Uri.fromFile(sdCardFile), localMimeTypeMap.getMimeTypeFromExtension("html"));
	             localIntent = Intent.createChooser(localIntent, "Choose");
	             startActivity(localIntent);
	             dialog.dismiss();
	             
	        }

	    });

	    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            // Do nothing
	            dialog.dismiss();
	        }
	    });

	    AlertDialog alert = builder.create();
	    alert.show();
	}

}
