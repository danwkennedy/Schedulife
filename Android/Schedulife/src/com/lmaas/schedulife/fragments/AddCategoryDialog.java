package com.lmaas.schedulife.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.lmaas.schedulife.R;

public class AddCategoryDialog extends SherlockDialogFragment {
	
	private EditText mCategoryName;

	public AddCategoryDialog() {
		// Required for DialogFragment
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		View view = inflater.inflate(R.layout.fragment_add_category, container);
		mCategoryName = (EditText) view.findViewById(R.id.txt_category_name);
		getDialog().setTitle("Add Category");
		
		return view;
	}
	
}
