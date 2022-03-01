package com.vishnu.cgpa;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.app.Activity;

public class GettingStarted extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);
		 Button localButton = (Button)findViewById(R.id.btStartedOk);
		    ((WebView)findViewById(R.id.webview)).loadUrl("file:///android_asset/gettingstarted.html");
		    localButton.setOnClickListener(new View.OnClickListener()
		    {
		      public void onClick(View paramView)
		      {
		        GettingStarted.this.onDestroy();
		      }
		    });
	}

	@Override
	protected void onDestroy()
	  {
	    super.onDestroy();
	    finish();
	  }

}
