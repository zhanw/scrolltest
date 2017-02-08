package com.zhanw.scrolltest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	
	private String[] strs = new String[]{"ListView item","ListView item","ListView item",
			"ListView item","ListView item","ListView item","ListView item","ListView item",
			"ListView item","ListView item","ListView item","ListView item","ListView item"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		PullToRefreshView refreshView = (PullToRefreshView) findViewById(R.id.refresh_view);
		refreshView.getListView().setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs));
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
