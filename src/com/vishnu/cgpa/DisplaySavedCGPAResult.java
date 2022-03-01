package com.vishnu.cgpa;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class DisplaySavedCGPAResult extends Activity implements OnClickListener {

	ArrayList<String> result;
	int myid;
	TextView sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8,cgpa,savedname,reg,dept;
	Button back,delete,exportAsHtml;
	CgpaDatabaseHelper dbhelp;
	File sdCardFile,folder;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_saved_cgparesult);
		myid = getIntent().getExtras().getInt("id");
		
		//Initialize
		savedname = (TextView) findViewById(R.id.tvSavedCgpaName);
		reg= (TextView) findViewById(R.id.tvStoredRegulation);
		dept= (TextView) findViewById(R.id.tvStoredDept);
		sem1 = (TextView) findViewById(R.id.tvStoredSem1);
		sem2 = (TextView) findViewById(R.id.tvStoredSem2);
		sem3 = (TextView) findViewById(R.id.tvStoredSem3);
		sem4 = (TextView) findViewById(R.id.tvStoredSem4);
		sem5 = (TextView) findViewById(R.id.tvStoredSem5);
		sem6 = (TextView) findViewById(R.id.tvStoredSem6);
		sem7 = (TextView) findViewById(R.id.tvStoredSem7);
		sem8 = (TextView) findViewById(R.id.tvStoredSem8);
		cgpa = (TextView) findViewById(R.id.tvStoredCgpa);
		back = (Button) findViewById(R.id.btGraphCgpa);
		delete = (Button) findViewById(R.id.btDeleteEntry);
		exportAsHtml = (Button) findViewById(R.id.btExportCgpaHtml);
		back.setOnClickListener(this);
		delete.setOnClickListener(this);
		exportAsHtml.setOnClickListener(this);
		displayResult();
		
		
	}
	private void displayResult() {
		// TODO Auto-generated method stub
		dbhelp = new CgpaDatabaseHelper(this);
		result= dbhelp.getFullSavedCgpa(myid);
		for (String s : result){
			System.out.println(s);
		}
		//Print the obtained results in TextView
		savedname.setText(result.get(0));
		dept.setText("Deptartment : "+result.get(1));
		reg.setText("Regulation : "+result.get(2));
		sem1.setText(result.get(3));
		sem2.setText(result.get(4));
		sem3.setText(result.get(5));
		sem4.setText(result.get(6));
		sem5.setText(result.get(7));
		sem6.setText(result.get(8));
		sem7.setText(result.get(9));
		sem8.setText(result.get(10));
		cgpa.setText(result.get(11));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	
	case R.id.btExportCgpaHtml:
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
						Toast.makeText(DisplaySavedCGPAResult.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
						finish();
						Intent i = new Intent(DisplaySavedCGPAResult.this, ListSavedCgpa.class);
						startActivity(i);
					}else Toast.makeText(DisplaySavedCGPAResult.this, "Delete Failed", Toast.LENGTH_SHORT).show();
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
			Intent i = new Intent(this, CgpaGraph.class);
			i.putExtra("id", myid);
			startActivity(i);
			break;
	
		}	
	}
	private void saveToFile() throws IOException {
		// TODO Auto-generated method stub
		
		FileWriter fWriter;
		//Boolean folderExists=true;
		folder = new File(Environment.getExternalStorageDirectory()+"/KEC GPA CGPA Calc/Saved CGPA");
		if(!folder.exists()){
			//Check if folder exists or not..Otherwise create it
			if(folder.mkdirs())
					Toast.makeText(this, "New Folder Created", Toast.LENGTH_SHORT).show();
		}
			
		sdCardFile = new File(Environment.getExternalStorageDirectory()+"/KEC GPA CGPA Calc/Saved CGPA/cgpa_" +savedname.getText().toString()+".html");
		
		fWriter = new FileWriter(sdCardFile, false);
		fWriter.write("<!DOCTYPE html><html><head><center><h1>Kongu Engineering College</h1></center>"
				+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
				"<head><body>");
		fWriter.append("<p>"+ dept.getText()+"</p>");
		fWriter.append("<p>"+ reg.getText()+"</p>");
		fWriter.append("<table width=\"100%\" border=\"1\" cellpadding=\"1\">");
		fWriter.append("<thead><tr><th>Semester</th><th>Gpa</th></tr><thead>");
		fWriter.append("<tbody>");
		fWriter.append("<tr><td>semester1</td><td>"+sem1.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester2</td><td>"+sem2.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester3</td><td>"+sem3.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester4</td><td>"+sem4.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester5</td><td>"+sem5.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester6</td><td>"+sem6.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester7</td><td>"+sem7.getText().toString()+"</td></tr>");
		fWriter.append("<tr><td>semester8</td><td>"+sem8.getText().toString()+"</td></tr>");
		fWriter.append("</tbody>");
		fWriter.append("<tfoot><tr><th>Cgpa</th><th>"+cgpa.getText().toString()+"</th></tr></tfoot></table>");
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
