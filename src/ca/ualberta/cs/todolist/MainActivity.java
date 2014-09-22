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
		setContentView(R.layout.activity_main);
		
		ListView listview = (ListView) findViewById(R.id.todoItemListView);
		Collection<Item> items = ItemListController.getItemList().getItems();
		final ArrayList<Item> list = new ArrayList<Item>(items);
		ItemAdapter = new ListAdapter(this, list);
		listview.setAdapter(ItemAdapter);
		
		//update observer
		ItemListController.getItemList().addListener(new Listener() {		
			@Override
			public void update() {
				list.clear();
				Collection<Item> items = ItemListController.getItemList().getItems();
				list.addAll(items);
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
		Toast.makeText(this, "add New item", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this,AddNewItemActivity.class);
		startActivity(intent);
	}
	
	public void viewArchivedItem(MenuItem menu){
		Toast.makeText(this, "view archived ithem", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this,ViewArchivedActivity.class);
		startActivity(intent);
	}
	
	public void summarizeData(MenuItem menu){
		Toast.makeText(this, "summarize data", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this,SummarizedDataActivity.class);
		startActivity(intent);
	}
	
	public void finishItem(View v){
		ItemListController ic = new ItemListController();
	    String result = "Selected items are :";
	    
	    for (Item i : ItemAdapter.getBox()) {
	      if (i.box){
	        result += "\n" + i.getName();
	        ic.changeStatus(i);
	        i.box = false;
	      }
	    }
	    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
	
	public void removeItem(View v){
		Toast.makeText(this, "removing item", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getBox()) {
		      if (i.box){
		    	  ic.removeItem(i);
		      }
		    }	
	}
	
	public void archiveItem(View v){
		Toast.makeText(this, "archive item", Toast.LENGTH_SHORT).show();
	}
	
	public void emailItem(View v){
		Toast.makeText(this, "email item", Toast.LENGTH_SHORT).show();
	}
	
}
