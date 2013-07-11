package com.lmaas.schedulife.fragments;

import android.os.Bundle;
import android.view.*;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;


import com.actionbarsherlock.app.SherlockDialogFragment;
import com.lmaas.schedulife.R;

public class AddCategoryDialog extends SherlockDialogFragment implements android.view.View.OnClickListener {
	
	private EditText mCategoryName;
	private Button mButtonCancel, mButtonSave;

	public AddCategoryDialog() {
		// Required for DialogFragment
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		View view = inflater.inflate(R.layout.fragment_add_category, container);
		mCategoryName = (EditText) view.findViewById(R.id.txt_category_name);
		getDialog().setTitle("New Category");
		
		// Make the keyboard appear when the dialog loads
		mCategoryName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        
        mButtonCancel = (Button)view.findViewById(R.id.add_category_button_cancel);
        mButtonCancel.setOnClickListener(this);
        mButtonSave = (Button)view.findViewById(R.id.add_category_button_save);
        mButtonSave.setOnClickListener(this);
        //mEditText.setOnEditorActionListener(this);
		
		return view;
	}

	@Override
	public void onClick(View view) {
		
		AddCategoryDialogListener activity = (AddCategoryDialogListener) getActivity();
		
		if (view.equals(mButtonSave)) {
			activity.onFinishAddCategory(true, null);
		} else {
			activity.onFinishAddCategory(false, null);
		}
		
		this.dismiss();
		
	}

	
}
