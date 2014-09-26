/*
ToDo List: Add,remove,archive and check ToDo Items.

Copyright (C) 2014 Bicheng Yan bicheng@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ListAdapter ItemAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.activity_main);
		
		ItemListManager.initManager(this.getApplicationContext());
		ListView listview = (ListView) findViewById(R.id.todoItemListView);
		Collection<Item> items = ItemListController.getItemList().getItems();
		final ArrayList<Item> itemList = new ArrayList<Item>(items);
		ItemAdapter = new ListAdapter(this, itemList);
		listview.setAdapter(ItemAdapter);

		//update observer
		ItemListController.getItemList().addListener(new Listener() {		
			@Override
			public void update() {
				itemList.clear();
				Collection<Item> items = ItemListController.getItemList().getItems();
				itemList.addAll(items);
				ItemAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addNewItem(MenuItem menu){
		Intent intent = new Intent(MainActivity.this,AddNewItemActivity.class);
		startActivity(intent);
	}
	
	public void viewArchivedItem(MenuItem menu){
		Intent intent = new Intent(MainActivity.this,ViewArchivedActivity.class);
		startActivity(intent);
	}
	
	public void summarizeData(MenuItem menu){
		Intent intent = new Intent(MainActivity.this,SummarizedDataActivity.class);
		startActivity(intent);
	}
	
	public void emailAllItem(MenuItem menu){
	    String emailBody_todo = "Todo items are :";
	    String emailBody_archived = "Archive items are :";
	    String emailBody = "";
	    String emailtest = emailBody_todo + "\n\n" + emailBody_archived;
	    
	    for (Item i : ItemAdapter.getBox()) {
	        emailBody_todo += "\n" + i.getName();
	    }
	    
		Collection<Item> items_archived = ItemListController.getArchivedItemList().getArchivedItems();
		ArrayList<Item> list_archived = new ArrayList<Item>(items_archived);
		ItemAdapter = new ListAdapter(this, list_archived);
		
	    for (Item i : ItemAdapter.getBox()) {
		        emailBody_archived += "\n" + i.getName();
		    }
	    
	    emailBody = emailBody_todo + "\n\n" + emailBody_archived;
	    if (!(emailBody.equals(emailtest))){
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("message/rfc822");
			intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
			intent.putExtra(Intent.EXTRA_SUBJECT, "TODO List");
			intent.putExtra(Intent.EXTRA_TEXT   , emailBody);
			try {
			    startActivity(Intent.createChooser(intent, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
	    }
	    else{
	    	Toast.makeText(this, "There are no items in the application", Toast.LENGTH_SHORT).show();
	    }
	}
	

	public void finishItem(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
	        ic.changeStatus(i);
	    }
	}

	public void removeItem(View v){
		Toast.makeText(this, "Selected Items Removed", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		    	  ic.removeItem(i);
		    	  i.box = false;
		}	
	}
	
	public void archiveItem(View v){	
	    Toast.makeText(this, "Selected Items Archived", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		    	  i.box = false;
		    	  ic.addArchivedItem(i);
		    	  ic.removeItem(i);
		}

	}
	
	public void emailItem(View v){
	    String emailBody = "Todo items are :";
	    for (Item i : ItemAdapter.getCheckedBox()) {
	        emailBody += "\n" + i.getName();
	    }
	    if (emailBody != "Todo items are :"){
	    	//get the following code from http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("message/rfc822");
			intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
			intent.putExtra(Intent.EXTRA_SUBJECT, "TODO List");
			intent.putExtra(Intent.EXTRA_TEXT   , emailBody);
			try {
			    startActivity(Intent.createChooser(intent, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
	    }
	    else{
	    	Toast.makeText(this, "No Item selected", Toast.LENGTH_SHORT).show();
	    }
	}
	
	public void selectAll(View v){
		ItemListController ic = new ItemListController();
		for (Item i : ItemAdapter.getUncheckedBox()) {
			ic.selectAll(i);
		}
	}
	
	public void selectNone(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
	    	ic.selectNone(i);
		}
	}
	
	public void selectInverse(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getBox()) {
	    	ic.selectInverse(i);
		}
	}
}
