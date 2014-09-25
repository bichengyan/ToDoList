package ca.ualberta.cs.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ItemList implements Serializable{

	/**
	 * ItemList serialization ID
	 */
	private static final long serialVersionUID = -6647489627875174030L;
	protected ArrayList<Item> ItemList;
	protected ArrayList<Item> ArchivedItemList;
	protected transient ArrayList<Listener> listeners = null ;
	
	public ItemList() {
		ItemList = new ArrayList<Item>();	
		listeners = new ArrayList<Listener>();
		ArchivedItemList = new ArrayList<Item>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public Collection<Item> getItems() {
		return ItemList;	
	}
	
	public Collection<Item> getArchivedItems() {
		return ArchivedItemList;	
	}
	
	public void addItem(Item testItem) {
		ItemList.add(testItem);
		NotifyListeners();
	}
	
	public void addArchivedItem(Item testItem) {
		ArchivedItemList.add(testItem);
		testItem.box = false;
		NotifyListeners();
	}
	
	public void removeItem(Item testItem) {
		ItemList.remove(testItem);
		NotifyListeners();
	}
	
	public void removeArchivedItem(Item testItem) {
		ArchivedItemList.remove(testItem);
		NotifyListeners();
	}
	
	public void NotifyListeners() {
		for (Listener listener : getListeners()) {
			listener.update();
		}	
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
	public void changeStatus (Item testItem){	
		if (testItem.getStatus().equals("TODO")){
			testItem.itemStatus = "DONE"; 
			testItem.image = R.drawable.ic_launcher;
		}
		else{
			testItem.itemStatus = "TODO";
			testItem.image = R.drawable.ic_todo;
		}
		testItem.box = false;
		NotifyListeners();
	}

	public void selectAll(Item testitem) {
		testitem.box = true;
		NotifyListeners();
	}

	public void selectNone(Item testitem) {
		testitem.box = false;
		NotifyListeners();
	}

	public void selectInverse(Item testitem) {
		if(testitem.box){
			testitem.box = false;
		}
		else{
			testitem.box = true;
		}
		NotifyListeners();
	}

	public int getTotalArchivedNum() {
		return ArchivedItemList.size();
	}
}
