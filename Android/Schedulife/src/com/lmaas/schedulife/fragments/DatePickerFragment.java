package com.lmaas.schedulife.fragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.lmaas.schedulife.DateTimeFormatters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceStaBundle) {
		final Calendar calendar = getInitialCalendar(getArguments());
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	private Calendar getInitialCalendar(Bundle bundle) {
		Calendar calendar = Calendar.getInstance();
		
		DateFormat dateFormater = DateTimeFormatters.getDefaultDateFormat();
		Date initialDate;
		
		try {
			initialDate = dateFormater.parse(bundle.getString(INITIAL_DATE_KEY));
		} catch (ParseException ex) {
			initialDate = new Date();
		}
		
		calendar.setTime(initialDate);
		return calendar;
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, monthOfYear);
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		
		DatePickerDialogListener activity = (DatePickerDialogListener) getActivity();
		activity.datePicked(calendar.getTime());
	}
	
	/**
	 * The key the fragment will look for when setting the initial date
	 */
	public static final String INITIAL_DATE_KEY = "initialDate";
	
	/**
	 * Creates a DatePickerFragment with the given arguments
	 * 
	 * @param initialDate The day to initialize the picker to
	 * @return A new DatePickerFragment initialized to the provided day
	 */
	public static DatePickerFragment CreateDatePickerWithDefaults(Date initialDate) {
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(createArgumentsBundle(initialDate));
		return fragment;
	}
	
	/**
	 * Creates the arguments bundle to pass to a new DatePickerFragment
	 * @param initialTime The day to initialize the picker to
	 * @return A Bundle with all the arguments required for initializing a DatePickerFragment
	 */
	public static Bundle createArgumentsBundle(Date initialDate) {
		Bundle argsBundle = new Bundle();
		argsBundle.putString(INITIAL_DATE_KEY, (String) DateTimeFormatters.formatDate(initialDate));
		return argsBundle;
	}
	
}
