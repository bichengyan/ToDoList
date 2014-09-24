package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SummarizedDataActivity extends Activity {

	ListAdapter ItemAdapter_one;
	ListAdapter ItemAdapter_two;
	int checked_todo_num = 0;
	int unchecked_todo_num = 0;
	int archived_checked_num = 0;
	int archived_unchecked_num = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summarize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summarized_data, menu);
		return true;
	}

	@Override
	protected void onResume(){
		super.onResume();
		
		TextView todo_checked_Num = (TextView)findViewById(R.id.todoCheckedNumTextview);
		TextView todo_uncheck_Num = (TextView)findViewById(R.id.todoUncheckNumTextview);
		TextView todo_archived_Num = (TextView)findViewById(R.id.todoArchivedNumTextview);
		TextView archived_checked_Num = (TextView)findViewById(R.id.archivedCheckedNumTextview);
		TextView archived_unchecked_Num = (TextView)findViewById(R.id.archivedUncheckedNumTextview);
		
		ItemListController ic = new ItemListController();
		int archived_total_num = ic.getTotalArchivedNum();
		
		Collection<Item> TodoItems = ItemListController.getItemList().getItems();
		Collection<Item> ArchivedItems = ItemListController.getArchivedItemList().getArchivedItems();
		ArrayList<Item> TodoList = new ArrayList<Item>(TodoItems);
		ArrayList<Item> ArchivedList = new ArrayList<Item>(ArchivedItems);
		ItemAdapter_one = new ListAdapter(this, TodoList);
	    ItemAdapter_two = new ListAdapter(this, ArchivedList);
		
	    for (Item i : ItemAdapter_one.getBox()) {
		      if (i.itemStatus.equals("Done")){
		    	  checked_todo_num++;
		      }
		      else{
		    	  unchecked_todo_num++;
		      }
		}	
	    

	    for (Item a : ItemAdapter_two.getBox()) {
		      if (a.itemStatus.equals("Done")){
		    	  archived_checked_num++;
		      }
		      else{
		    	  archived_unchecked_num++;
		      }
		}	
	    
		todo_checked_Num.setText(""+checked_todo_num+"");
		todo_uncheck_Num.setText(""+unchecked_todo_num+"");
		todo_archived_Num.setText(""+archived_total_num+"");
		archived_checked_Num.setText(""+archived_checked_num);
		archived_unchecked_Num.setText(""+archived_unchecked_num+"");
	}
}
