package com.lmaas.schedulife;

import java.lang.reflect.Field;

import android.view.ViewConfiguration;

import com.orm.SugarApp;

public class SchedulifeApp extends SugarApp {
	
	@Override
	public void onCreate() {
		super.onCreate();
		getOverflowMenu();
	}
	
	private void getOverflowMenu() {

        try {
           ViewConfiguration config = ViewConfiguration.get(this);
           Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
           if(menuKeyField != null) {
               menuKeyField.setAccessible(true);
               menuKeyField.setBoolean(config, false);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}
