package com.lmaas.schedulife.fragments;

import com.lmaas.schedulife.sqlite.entities.Category;

public interface AddCategoryDialogListener {
	public void onFinishAddCategory(boolean success, Category category);	
}
