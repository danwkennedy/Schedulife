package com.lmaas.schedulife.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.lmaas.schedulife.R;

public class ScheduleFragment extends SherlockFragment {
	
	public static final String NAME_ARG = "name";
	
	private String _name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Note: saved instance state != args
		Bundle args = getArguments();
		
		if (args != null)
			_name = args.getString(NAME_ARG);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		
		if (container == null)
			return null;
		
		System.out.println("New demo fragment created");
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_schedule, container, false);
		TextView textView = new TextView(getActivity());
		textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		textView.setText(_name);
		view.addView(textView);
		return view;
	}
	
	public static ScheduleFragment newInstance() {
		return newInstance("Default");
	}
	
	public static ScheduleFragment newInstance(String name) {
		ScheduleFragment fragment = new ScheduleFragment();
		
		Bundle args = new Bundle();
		args.putString(NAME_ARG, name);
		fragment.setArguments(args);
		
		return fragment;
	}

}
