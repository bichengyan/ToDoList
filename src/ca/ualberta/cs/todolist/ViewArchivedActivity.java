package ca.ualberta.cs.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewArchivedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_archivement);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_archived, menu);
		return true;
	}

}
