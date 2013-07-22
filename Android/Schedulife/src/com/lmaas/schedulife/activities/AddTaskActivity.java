package com.lmaas.schedulife.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.lmaas.schedulife.DateTimeFormatters;
import com.lmaas.schedulife.R;
import com.lmaas.schedulife.fragments.AddCategoryDialog;
import com.lmaas.schedulife.fragments.AddCategoryDialogListener;
import com.lmaas.schedulife.fragments.DatePickerDialogListener;
import com.lmaas.schedulife.fragments.DatePickerFragment;
import com.lmaas.schedulife.fragments.TimePickerDialogListener;
import com.lmaas.schedulife.fragments.TimePickerFragment;
import com.lmaas.schedulife.sqlite.entities.Category;
import com.lmaas.schedulife.sqlite.entities.Task;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;

public class AddTaskActivity extends SherlockFragmentActivity implements AddCategoryDialogListener, DatePickerDialogListener, TimePickerDialogListener {
	
	private List<String> _mCategories;
	private Spinner _mCategoriesSpinner;
	private Button _mDateButton, _mTimeButton;
	private SeekBar _mIncrementSeekBar;
	private TextView _mIncrementLabel, _mIntervalLabel;
	
	
	private Date _taskDate;
	private long _numberOfSeconds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		if (_taskDate == null) {
			_taskDate = new Date();
		}
				
		setupCategoriesSpinner();
		setupDateTimeSpinners();
		setupIncrementSeekbar();
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

	public boolean onDueDateClick(View view) {
		DialogFragment newFragment = DatePickerFragment.CreateDatePickerWithDefaults(_taskDate);
		newFragment.show(getSupportFragmentManager(), "datePicker");
		
		return false;
	}
	
	public boolean onDueTimeClick(View view) {
		DialogFragment newFragment = TimePickerFragment.CreateDatePickerWithDefaults(_taskDate);
		newFragment.show(getSupportFragmentManager(), "timePicker");
		
		return false;
	}
	
	public void onFinishAddCategory(boolean success, Category category) {
		if (success && (category != null)) {
			category.save();
		} else {
			_mCategoriesSpinner.setSelection(0);
		}
	}
	
	public void onAddDueDateClick(View view) {
		view.setVisibility(View.GONE);
		View dueDateSection = findViewById(R.id.section_dueDate);
		dueDateSection.setVisibility(View.VISIBLE);
	}
	
	public void onCancelClick(View view) {
		NavUtils.navigateUpFromSameTask(this);
	}
	
	public void onSaveClick(View view) {
		Task task = new Task(this, 123456, "New Task", 1235456, 123456789, new Date(), null);
		task.save();
		
		System.out.println("Saved new task");
		
		NavUtils.navigateUpFromSameTask(this);
	}
	
	protected void setupDateTimeSpinners() {
		_mDateButton = (Button) findViewById(R.id.button_setDueDate);
		updateDueDateButton(DateTimeFormatters.formatDate(_taskDate));
		
		_mTimeButton = (Button) findViewById(R.id.button_setDueTime);
		updateDueTimeButton(DateTimeFormatters.formatTime(_taskDate));
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
	
	protected void setupIncrementSeekbar() {
		_mIncrementSeekBar = (SeekBar) findViewById(R.id.seek_intervals);
		_mIncrementLabel = (TextView) findViewById(R.id.label_intervals);
		_mIntervalLabel = (TextView) findViewById(R.id.label_numberOfSprints);
		updateIncrementLabel(_mIncrementSeekBar.getProgress() + 1);
		
		_mIncrementSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				updateIncrementLabel(progress + 1);
			}
		});
	}
	
	protected void updateIncrementLabel(int increments) {
		int numberOfMinutes = increments * 15;
		int hours = numberOfMinutes / 60;
		int minutes = numberOfMinutes - (hours * 60);
		StringBuilder builder = new StringBuilder(150);
		
		if (hours > 0)
			builder.append(hours + (hours > 1 ? " hours " : " hour "));
		if (minutes > 0)
			builder.append(minutes + (minutes > 1 ? " minutes " : " minute "));
		
		_mIncrementLabel.setText(builder.toString());
		_mIntervalLabel.setText(increments + " sprints of 15 minutes");
		_numberOfSeconds = numberOfMinutes * 60;
	}
	
	@Override
	public void datePicked(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		calendar.setTime(_taskDate);
		calendar.set(year, month, day);
		_taskDate = calendar.getTime();
		
		updateDueDateButton(DateTimeFormatters.formatDate(_taskDate));		
	}
	
	public void updateDueDateButton(String text) {
		_mDateButton.setText(text);
	}
	
	@Override
	public void timePicked(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		calendar.setTime(_taskDate);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		_taskDate = calendar.getTime();
		
		updateDueTimeButton(DateTimeFormatters.formatTime(_taskDate));
	}
	
	public void updateDueTimeButton(String text) {
		_mTimeButton.setText(text);
	}
	
	protected void showAddCategoryDialog() {
		FragmentManager fm = getSupportFragmentManager();
		AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
		addCategoryDialog.show(fm, "fragment_add_category");
	}
	
}


