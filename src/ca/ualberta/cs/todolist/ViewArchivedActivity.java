package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ViewArchivedActivity extends Activity {
	ListAdapter ItemAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.view_archivement);
		
		ItemListManager.initManager(this.getApplicationContext());
		ListView listview = (ListView) findViewById(R.id.archivedItemListview);
		Collection<Item> items = ItemListController.getArchivedItemList().getArchivedItems();
		final ArrayList<Item> archivedItemList = new ArrayList<Item>(items);
		ItemAdapter = new ListAdapter(this, archivedItemList);
		listview.setAdapter(ItemAdapter);
		
		//update observer
		ItemListController.getArchivedItemList().addListener(new Listener() {		
			@Override
			public void update() {
				archivedItemList.clear();
				Collection<Item> items = ItemListController.getArchivedItemList().getArchivedItems();
				archivedItemList.addAll(items);
				ItemAdapter.notifyDataSetChanged();
			}
		});
	}

	public void finishItem(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
	        ic.changeArchivedStatus(i);
	    }
	}
	
	public void removeItemAction(View v){
		Toast.makeText(this, "Selected Items removed", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		    	  ic.removeArchivedItem(i);
		}
	}
	
	public void unarchiveItem(View v){
		Toast.makeText(this, "Selected Items unarchived", Toast.LENGTH_SHORT).show();
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
		    	  i.box = false;
		    	  ic.addItem(i);
		    	  ic.removeArchivedItem(i);
		}
	}
	
	public void emailArchivedItem(View v){
	    String emailBody = "Archived items are :";
	    
	    for (Item i : ItemAdapter.getCheckedBox()) {
	        emailBody += "\n" + i.getName();
	    }
	    
	    if (emailBody != "Archived items are :"){
	    	//got the following code from http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
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
	    	Toast.makeText(this, "No item selected", Toast.LENGTH_LONG).show();
	    }
	}
	
	public void selectAll_archived(View v){
		ItemListController ic = new ItemListController();
		for (Item i : ItemAdapter.getUncheckedBox()) {
			ic.selectAll_archived(i);
		}
	}
	
	public void selectNone_archived(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getCheckedBox()) {
	    	ic.selectNone_archived(i);
		}
	}
	
	public void selectInverse_archived(View v){
		ItemListController ic = new ItemListController();
	    for (Item i : ItemAdapter.getBox()) {
	    	ic.selectInverse_archived(i);
		}
	}
}
