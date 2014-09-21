package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import java.util.Collection;


public class ItemList {
	
	protected ArrayList<Item> ItemList;
	protected ArrayList<Listener> listeners;
	
	public ItemList() {
		ItemList = new ArrayList<Item>();	
		listeners = new ArrayList<Listener>();
	}
		
	public Collection<Item> getItems() {
		return ItemList;	
	}
	public void addItem(Item testItem) {
		ItemList.add(testItem);
		NotifyListeners();
	}
	
	private void NotifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
		
	}

	public void addListener(Listener l) {
		listeners.add(l);
	}
	
	public void removeListener(Listener l) {
		listeners.remove(l);
	}
	
	public void removeItem(Item testItem) {
		ItemList.remove(testItem);
	}
}
