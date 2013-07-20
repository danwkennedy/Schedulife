package com.lmaas.schedulife.fragments;


import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.lmaas.schedulife.PrioritizeAdapter;
import com.lmaas.schedulife.R;
import com.lmaas.schedulife.sqlite.entities.Category;

public class PrioritizeDialogFragment extends SherlockDialogFragment implements android.view.View.OnClickListener {

	private Button mButtonCancel, mButtonSave;
	private ListView mList;
	private List<Category> list;
	private PrioritizeAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
		View view = inflater.inflate(R.layout.fragment_prioritize, container);
		
		getDialog().setTitle("Prioritize");
		
		mButtonCancel = (Button)view.findViewById(R.id.prioritize_button_cancel);
        mButtonCancel.setOnClickListener(this);
        mButtonSave = (Button)view.findViewById(R.id.prioritize_button_save);
        mButtonSave.setOnClickListener(this);
        
        mList = (ListView) view.findViewById(R.id.prioritylistview);
        list = Category.listAll(Category.class);
	    
	    adapter = new PrioritizeAdapter(this.getActivity(),list);
	    mList.setAdapter(adapter);

	    mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
	    		final String cat = (String) parent.getItemAtPosition(0);
	    		list.remove(cat);
	    		adapter.notifyDataSetChanged();
	    	}

	    });
		
		return view;
	}
	
	@Override
	public void onClick(View view) {
		
		PrioritizeDialogListener activity = (PrioritizeDialogListener) getActivity();
		
		if (view.equals(mButtonSave)) {
			savePriorities();
			activity.onSavePrioritize();
		}
		//cancel button only needs to close dialog
		this.dismiss();
		
	}

	private void savePriorities() {
		for(Category cat : list){
			cat.save();
		}
		
	}
	
}
