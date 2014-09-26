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
		ArchivedItemList = new ArrayList<Item>();
		listeners = new ArrayList<Listener>();
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
		NotifyDataChanged();
	}
	
	public void addArchivedItem(Item testItem) {
		ArchivedItemList.add(testItem);
		testItem.box = false;
		NotifyDataChanged();
	}
	
	public void removeItem(Item testItem) {
		ItemList.remove(testItem);
		NotifyDataChanged();
	}
	
	public void removeArchivedItem(Item testItem) {
		ArchivedItemList.remove(testItem);
		NotifyDataChanged();
	}
	
	public void NotifyDataChanged() {
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
			testItem.image = R.drawable.checked;
		}
		else{
			testItem.itemStatus = "TODO";
			testItem.image = R.drawable.unchecked;
		}
		testItem.box = false;
		NotifyDataChanged();
	}

	public void selectAll(Item testitem) {
		testitem.box = true;
		NotifyDataChanged();
	}

	public void selectNone(Item testitem) {
		testitem.box = false;
		NotifyDataChanged();
	}

	public void selectInverse(Item testitem) {
		if(testitem.box){
			testitem.box = false;
		}
		else{
			testitem.box = true;
		}
		NotifyDataChanged();
	}

	public int getTotalArchivedNum() {
		return ArchivedItemList.size();
	}
}
