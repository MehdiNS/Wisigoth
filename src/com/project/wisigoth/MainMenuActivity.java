package com.project.wisigoth;

import com.project.map.MyMapActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void obtenirPosition(View view) {
		
	}
	
	public void quitter(View view) {
		this.finish();
		
	}
	
	public void testMap(View view) {
		Intent intent = new Intent(this, MyMapActivity.class);
        startActivity(intent);
	}
	
	public void onStop() {
		Log.i("Wisigoth onStop","MainMenuActivity : passage dans onStop()");
		super.onStop();
	}
	
	public void gpsSetting(View view) {
		startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
	}

}
