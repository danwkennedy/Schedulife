package com.lmaas.schedulife.activities;

import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.lmaas.schedulife.R;
import com.lmaas.schedulife.sqlite.entities.Category;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrioritizeActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prioritize);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		 final ListView listview = (ListView) findViewById(R.id.prioritylistview);

		    final ArrayList<Category> list = new ArrayList<Category>();
		    //TODO replace with data retrieval from database
		    list.add(new Category(this, 0,"Cleaning", false));
		    list.add(new Category(this, 1,"Bills", true));
		    list.add(new Category(this, 2, "Shopping", false));
		    
		    final ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
		        android.R.layout.simple_list_item_1, list);
		    listview.setAdapter(adapter);

		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    	
		    	@Override
		    	public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
		    		final Category cat = (Category) parent.getItemAtPosition(0);
		    		list.remove(cat);
		    		adapter.notifyDataSetChanged();
		    	}

		    });
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.add_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	public void onSaveClick(View view) {
		
		//TODO save
		NavUtils.navigateUpFromSameTask(this);
		
	}
	
	public void onCancelClick(View view) {
		
		NavUtils.navigateUpFromSameTask(this);
		
	}
	
}
