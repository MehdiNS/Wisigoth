package com.project.map;

import org.mapsforge.android.maps.overlay.OverlayItem;

import com.project.wisigoth.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class WebviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		 WebView webview = new WebView(this);
		 setContentView(webview);
		 webview.loadUrl(getIntent().getExtras().getString("url"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.webview, menu);
		return true;
	}
	
	public static void start(OverlayItem item) {
		
	}

}
