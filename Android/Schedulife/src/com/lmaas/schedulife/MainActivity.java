package com.lmaas.schedulife;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends SherlockActivity implements OnNavigationListener {

	String[] mScheduleViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScheduleViews = getResources().getStringArray(R.array.scheduleViews);
        
        Context context = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.scheduleViews, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(list, (OnNavigationListener) this);
        actionBar.setDisplayShowTitleEnabled(false);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main,  menu);
        return true;
    }
	
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		
		switch(itemPosition) {
		case 0:
			break;
		case 1:
			break;
		}
		
		System.out.println("Changing view to: " + mScheduleViews[itemPosition]);
		
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
		case R.id.action_prioritize:
			System.out.println("Opening prioritize modal");
			startActivity(new Intent(this, PrioritizeActivity.class));
			break;
		case R.id.action_addTask:
			startActivity(new Intent(this, AddTaskActivity.class));
			System.out.println("Opening add task form");
			break;
		case R.id.action_showCategories:
			System.out.println("Navigating to the Categories activity");
			break;
		case R.id.action_settings:
			System.out.println("Navigating to the Settings activity");
		}		
		
		return false;
	}
	
    
}
