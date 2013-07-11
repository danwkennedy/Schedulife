package com.lmaas.schedulife.fragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.lmaas.schedulife.DateTimeFormatters;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceStaBundle) {
		final Calendar calendar = getInitialCalendar(getArguments());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		return new TimePickerDialog(getActivity(), this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
	}
	
	private Calendar getInitialCalendar(Bundle bundle) {
		Calendar calendar = Calendar.getInstance();
		
		DateFormat dateFormater = DateTimeFormatters.getDefaultTimeFormat();
		Date initialTime;
		
		try {
			initialTime = dateFormater.parse(bundle.getString(INITIAL_Time_KEY));
		} catch (ParseException ex) {
			initialTime = new Date();
		}
		
		calendar.setTime(initialTime);
		return calendar;
	}
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		System.out.println("New time picked.");

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		
		TimePickerDialogListener activity = (TimePickerDialogListener) getActivity();
		activity.timePicked(calendar.getTime());
	}
	
	/**
	 * The key the fragment will look for when setting the initial time
	 */
	public static final String INITIAL_Time_KEY = "initialTime";
	
	/**
	 * Creates a DatePickerFragment with the given arguments
	 * 
	 * @param initialTime The day to initialize the picker to
	 * @return A new DatePickerFragment initialized to the provided day
	 */
	public static TimePickerFragment CreateDatePickerWithDefaults(Date initialTime) {
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(createArgumentsBundle(initialTime));
		return fragment;
	}
	
	/**
	 * Creates the arguments bundle to pass to a new TimePickerFragment
	 * @param initialTime The time to initialize the picker to
	 * @return A Bundle with all the arguments required for initializing a TimePickerFragment
	 */
	public static Bundle createArgumentsBundle(Date initialTime) {
		Bundle argsBundle = new Bundle();
		argsBundle.putString(INITIAL_Time_KEY, DateTimeFormatters.formatTime(initialTime));
		return argsBundle;
	}
	
}
