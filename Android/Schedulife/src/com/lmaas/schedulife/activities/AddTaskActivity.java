package com.lmaas.schedulife.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.*;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.lmaas.schedulife.R;
import com.lmaas.schedulife.fragments.AddCategoryDialog;
import com.lmaas.schedulife.fragments.AddCategoryDialogListener;
import com.lmaas.schedulife.sqlite.entities.Category;
import com.lmaas.schedulife.sqlite.entities.Task;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import android.os.Bundle;

public class AddTaskActivity extends SherlockFragmentActivity implements AddCategoryDialogListener {
	
	private List<String> _mCategories;
	private Spinner _mDateSpinner, _mTimeSpinner, _mCategoriesSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setupCategoriesSpinner();
		setupDateTimeSpinners();
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

	public boolean onDueDateSpinnerClick(View view) {
		System.out.println("Due date spinner clicked");
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
		
		return false;
	}
	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceStaBundle) {
			final Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH);
			int year = calendar.get(Calendar.YEAR);
			
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			System.out.println("New date picked.");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			AddTaskActivity activity = (AddTaskActivity) getActivity();
			Spinner spinner = (Spinner) activity.findViewById(R.id.spinner_dueDate);
			DateFormat dateFormatter = DateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
			activity.setSpinnerString(spinner, dateFormatter.format(calendar.getTime()));
		}
		
	}
	
	public boolean onDueTimeSpinnerClick(View view) {
		System.out.println("Due time spinner clicked");
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
		
		return false;
	}
	
	public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceStaBundle) {
			final Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			
			return new TimePickerDialog(getActivity(), this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
		}
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			System.out.println("New time picked.");

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			calendar.set(Calendar.MINUTE, minute);
			
			AddTaskActivity activity = (AddTaskActivity) getActivity();
			Spinner spinner = (Spinner) activity.findViewById(R.id.spinner_dueTime);
			DateFormat timeFormatter = DateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.getDefault());
			activity.setSpinnerString(spinner, timeFormatter.format(calendar.getTime()));
		}
		
	}
	
	public void setSpinnerString(Spinner spinner, String text) {
		String[] string = { text };
		
		ArrayAdapter<CharSequence> adapter 
			= new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, string);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setFocusable(false);
	}
	
	protected void setupDateTimeSpinners() {
		_mDateSpinner = (Spinner) findViewById(R.id.spinner_dueDate);
		setSpinnerString(_mDateSpinner, DateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault()).format(new Date()));
		
		_mTimeSpinner = (Spinner)findViewById(R.id.spinner_dueTime);
		setSpinnerString(_mTimeSpinner, DateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.getDefault()).format(new Date()));
		
		_mDateSpinner.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_UP) {
					onDueDateSpinnerClick(v);
				}
				return false;
			}
		});
		
		_mDateSpinner.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
					onDueDateSpinnerClick(v);
					return true;
				} else {
					return false;
				}
			}
		});
		
		_mTimeSpinner.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_UP) {
					onDueTimeSpinnerClick(v);
				}
				return false;
			}
		});
		
		_mTimeSpinner.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
					onDueTimeSpinnerClick(v);
					return true;
				} else {
					return false;
				}
			}
		});
		
	}
	
	protected void setupCategoriesSpinner() {
		
		_mCategories = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.testCategories)));
		_mCategories.add("Create new category");
		
		_mCategoriesSpinner = (Spinner) findViewById(R.id.spinner_category);
		ArrayAdapter<CharSequence> adapter 
			= new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, _mCategories.toArray(new String[0]));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_mCategoriesSpinner.setAdapter(adapter);
		
		_mCategoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (position + 1 == _mCategories.size()) {
					showAddCategoryDialog();
					System.out.println("Show add category dialog");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}			
		});
		
	}
	
	protected void showAddCategoryDialog() {
		FragmentManager fm = getSupportFragmentManager();
		AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
		addCategoryDialog.show(fm, "fragment_add_category");
	}
	
	public void onFinishAddCategory(boolean success, Category category) {
		if (success) {
			
		} else {
			_mCategoriesSpinner.setSelection(0);
		}
	}
	
	public void onDueDateClick(View view) {
		view.setVisibility(View.GONE);
		View dueDateSection = findViewById(R.id.section_dueDate);
		dueDateSection.setVisibility(View.VISIBLE);
	}
	
	public void onSaveClick(View view) {
		
		Task task = new Task(this, 123456, "New Task", 1235456, 123456789, new Date(), null);
		task.save();
		
		System.out.println("Saved new task");
		
		NavUtils.navigateUpFromSameTask(this);
	}
	
	public void onCancelClick(View view) {
		NavUtils.navigateUpFromSameTask(this);
	}

}


