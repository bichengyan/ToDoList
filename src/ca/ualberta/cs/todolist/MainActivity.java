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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
}
