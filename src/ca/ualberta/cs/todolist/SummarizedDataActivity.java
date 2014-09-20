package ca.ualberta.cs.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SummarizedDataActivity extends Activity {

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

}
