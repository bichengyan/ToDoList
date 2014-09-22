package ca.ualberta.cs.todolist;

public class ItemListController {
	private static ItemList itemList = null;
	static public ItemList getItemList(){
		if (itemList == null) {
			itemList = new ItemList();
		}
		return itemList;
	}
	
	public void addItem(Item item) {
		getItemList().addItem(item);
	}

	public void removeItem(Item item) {
		getItemList().removeItem(item);
	}

	public void changeStatus(Item item) {
		getItemList().changeStatus(item);
	}
	
}
