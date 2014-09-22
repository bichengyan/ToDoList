package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ViewArchivedActivity extends Activity {
	ListAdapter ItemAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_archivement);
		
		ListView listview = (ListView) findViewById(R.id.archivedItemListview);
		Collection<Item> items = ItemListController.getArchivedItemList().getArchivedItems();
		final ArrayList<Item> list = new ArrayList<Item>(items);
		ItemAdapter = new ListAdapter(this, list);
		listview.setAdapter(ItemAdapter);
		
		//update observer
		ItemListController.getArchivedItemList().addListener(new Listener() {		
			@Override
			public void update() {
				list.clear();
				Collection<Item> items = ItemListController.getArchivedItemList().getArchivedItems();
				list.addAll(items);
				ItemAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_archived, menu);
		return true;
	}

	public void removeItemAction(View v){
		Toast.makeText(this, "removing an archived item!", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		      if (i.box){
		    	  ic.removeArchivedItem(i);
		      }
		}
	}
}
