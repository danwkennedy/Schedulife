package com.lmaas.schedulife;

import java.util.List;

import com.lmaas.schedulife.sqlite.entities.Category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class PrioritizeAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private List<Category> prioritiesList;
	
	public PrioritizeAdapter(Context context, List<Category> data){
	    this.inflater = LayoutInflater.from(context);
	    this.prioritiesList = data;
	}
	
	@Override
	public int getCount() {
		return this.prioritiesList.size();
	}

	@Override
	public Category getItem(int index) {
		return this.prioritiesList.get(index);
	}

	@Override
	public long getItemId(int index) {
		return this.prioritiesList.get(index).getCategoryId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Category currentCategory = getItem(position);           

        if(convertView == null){
            // Inflates the Common View from XML file
            convertView = this.inflater.inflate(R.layout.row_prioritize, null);
        }
        CheckBox box = (CheckBox) convertView.findViewById(R.id.prioritize_category_checkbox);
        
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				currentCategory.setPriority(isChecked);
				
			}
		});

        box.setText(currentCategory.getName());
        box.setChecked(currentCategory.getPriority());

        return convertView;
	}
	
	public void onCheckboxClicked(View view) {
		Log.d("PrioritizeAdapter","Checked!!");
	}

}
