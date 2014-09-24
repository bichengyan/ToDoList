package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		
		ItemListManager.initManager(this.getApplicationContext());
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
	
	public void unarchiveItem(View v){
		Toast.makeText(this, "unarchiving item", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		      if (i.box){
		    	  i.box = false;
		    	  ItemListController.getItemList().NotifyListeners();
		    	  ic.addItem(i);
		    	  ic.removeArchivedItem(i);
		      }
		}
	}
	
	public void emailArchivedItem(View v){
	    String emailBody = "Selected items are :";
	    
	    for (Item i : ItemAdapter.getCheckedBox()) {
	      if (i.box){
	        emailBody += "\n" + i.getName();
	      }
	    }
	    
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
		Toast.makeText(this, emailBody, Toast.LENGTH_LONG).show();
	}
}
