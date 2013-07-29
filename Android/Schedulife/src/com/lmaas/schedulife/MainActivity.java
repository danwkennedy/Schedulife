package com.lmaas.schedulife;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.lmaas.schedulife.R;
import com.lmaas.schedulife.activities.*;
import com.lmaas.schedulife.fragments.PrioritizeDialogFragment;
import com.lmaas.schedulife.fragments.ScheduleFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity {

	String[] mScheduleViews;
	private ViewPager mViewPager;
	private TabsAdapter mAdapter;
	private ActionBar actionBar;
	//private TabsAdapter
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mScheduleViews = getResources().getStringArray(R.array.scheduleViews);
		
		mAdapter = new TabsAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		
		mViewPager.setOnPageChangeListener(
	            new ViewPager.SimpleOnPageChangeListener() {
	                @Override
	                public void onPageSelected(int position) {
	                    // When swiping between pages, select the
	                    // corresponding tab.
	                    getSupportActionBar().setSelectedNavigationItem(position);
	                }
	            });
		
		mViewPager.setAdapter(mAdapter);
        
        //Context context = getSupportActionBar().getThemedContext();
//        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.scheduleViews, R.layout.sherlock_spinner_item);
//        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        
        //ActionBar actionBar = getSupportActionBar();
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setListNavigationCallbacks(list, (OnNavigationListener) this);
//        actionBar.setDisplayShowTitleEnabled(false);
        TabListener tabListener = new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mViewPager.setCurrentItem(tab.getPosition());
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {}
		};
		
		for (int i = 0; i < mScheduleViews.length; i++) {
			String viewString = mScheduleViews[i];
			Tab tab = actionBar.newTab();
			tab.setText(viewString);
			tab.setTabListener(tabListener);
			actionBar.addTab(tab, i == 0);
		}
        
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main,  menu);
        return true;
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
		case R.id.action_prioritize:
			System.out.println("Opening prioritize modal");
			//startActivity(new Intent(this, PrioritizeActivity.class));
			FragmentManager fm = getSupportFragmentManager();
			PrioritizeDialogFragment prioritizeDialog = new PrioritizeDialogFragment();
			prioritizeDialog.show(fm, "fragment_prioritize");
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

//	@Override
	public void onSavePrioritize() {
		// TODO reorganize schedule and todo list
		Toast toast = Toast.makeText(this, "reorganize schedule and todo list", Toast.LENGTH_SHORT);
		toast.show();
		
	}
	
    
}

class TabsAdapter extends FragmentPagerAdapter {

	private final Fragment[] _fragments = {
			ScheduleFragment.newInstance("Schedule"),
			ScheduleFragment.newInstance("Todo")
	};
	
	public TabsAdapter(FragmentManager fm) {
		super(fm);		
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = _fragments[i];
		return fragment;
	}

	@Override
	public int getCount() {
		return _fragments.length;
	}
	
}

class DemoFragment extends Fragment {
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		System.out.println("New demo fragment created");
//		LinearLayout layout = inflater.in
		return new View(getActivity());
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		System.out.println("Starting fragment");
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		
		System.out.println("Stopping fragment");
	}
}
