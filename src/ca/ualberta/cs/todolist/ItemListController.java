package ca.ualberta.cs.todolist;

public class ItemListController {
	private static ItemList itemList = null;
	private static ItemList archivedItemList = null;
	
	static public ItemList getItemList(){
		if (itemList == null) {
			itemList = new ItemList();
		}
		return itemList;
	}

	static public ItemList getArchivedItemList(){
		if (archivedItemList == null) {
			archivedItemList = new ItemList();
		}
		return archivedItemList;
	}
	
	public void addItem(Item item) {
		getItemList().addItem(item);
	}

	public void removeItem(Item item) {
		getItemList().removeItem(item);
	}

	public void addArchivedItem(Item item) {
		getArchivedItemList().addArchivedItem(item);
	}

	public void removeArchivedItem(Item item) {
		getArchivedItemList().removeArchivedItem(item);
	}
	
	public void changeStatus(Item item) {
		getItemList().changeStatus(item);
	}

	public void selectAll(Item item) {
		getItemList().selectAll(item);
	}

	public void selectNone(Item item) {
		getItemList().selectNone(item);
	}

	public void selectInverse(Item item) {
		getItemList().selectInverse(item);
	}
	
}
