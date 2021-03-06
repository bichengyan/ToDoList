package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddNewItemActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.add_new_item);
			
		ItemListManager.initManager(this.getApplicationContext());
		ListView listview2 = (ListView) findViewById(R.id.todoItemListView2);
		Collection<Item> items = ItemListController.getItemList().getItems();
		final ArrayList<Item> list = new ArrayList<Item>(items);
		final ArrayAdapter<Item> ItemAdapter;
		ItemAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, list);
		listview2.setAdapter(ItemAdapter);
		
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
	
	public void addItemAction(View v){
		Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
		EditText textview = (EditText) findViewById(R.id.newItemEditText);
		ItemListController ic = new ItemListController();
		if (textview.getText().toString().length() > 0){
			ic.addItem(new Item(textview.getText().toString()));
		}
		textview.setText("");
	}

}
