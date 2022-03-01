package com.vishnu.cgpa;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class FeedBackMail extends Activity implements OnClickListener {

	Spinner dept,reg;
	EditText text;
	Button mail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back_mail);
		text = (EditText) findViewById(R.id.etInputFeedback);
		dept = (Spinner) findViewById(R.id.spFeedbackDept);
		reg = (Spinner) findViewById(R.id.spFeedbackRegl);
		mail = (Button) findViewById(R.id.btMailFeedBack);
		mail.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btMailFeedBack:
			String sub = "CGPA App Feedback "+dept.getSelectedItem().toString()+ "-"+
							reg.getSelectedItem().toString();
			String body = text.getText().toString();
			if(body.equals("")){
				new AlertDialog.Builder(this)
			    .setTitle("ERROR!!!")
			    .setMessage("Please Fill the Feedback")
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	dialog.cancel();
			        }
			     })
			    .show();
			}else{
				//Mail id
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"balavishnu1993@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, sub);
				i.putExtra(Intent.EXTRA_TEXT   , body);
				try {
				    startActivity(Intent.createChooser(i, "Send mail via..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(FeedBackMail.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
			
			break;
		}
	}

	

}
