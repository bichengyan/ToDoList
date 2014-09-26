package ca.ualberta.cs.todolist;

import java.io.IOException;

public class ItemListController {
	private static ItemList itemList = null;
	private static ItemList archivedItemList = null;
	
	static public ItemList getItemList(){
		if (itemList == null) {
			try {
				itemList = ItemListManager.getManager().loadItemList();
				itemList.addListener(new Listener() {
					@Override
					public void update() {
						saveItemList();
					}
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList from ItemListManager");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList from ItemListManager");
			}
		}
		return itemList;
	}
	
	static public ItemList getArchivedItemList(){
		if (archivedItemList == null) {
			try {
				archivedItemList = ItemListManager.getManager().loadArchivedItemList();
				archivedItemList.addListener(new Listener() {
					@Override
					public void update() {
						saveArchievdItemList();
					}
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ArchivedItemList from ItemListManager");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ArchivedItemList from ItemListManager");
			}
		}
		return archivedItemList;
	}
	
	static public void saveItemList() {
		try {
			ItemListManager.getManager().saveItemList(getItemList());
		} catch (IOException e) {
			e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList from StudentListManager");
		}	
	}
	
	static public void saveArchievdItemList() {
		try {
			ItemListManager.getManager().saveArchivedItemList(getArchivedItemList());
		} catch (IOException e) {
			e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList from StudentListManager");
		}	
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
	
	public void changeArchivedStatus(Item item) {
		getArchivedItemList().changeStatus(item);
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

	public int getTotalArchivedNum() {
		return getArchivedItemList().getTotalArchivedNum();
	}

	public void selectAll_archived(Item item) {
		getArchivedItemList().selectAll(item);
	}
	
	public void selectNone_archived(Item item) {
		getArchivedItemList().selectNone(item);
	}

	public void selectInverse_archived(Item item) {
		getArchivedItemList().selectInverse(item);
	}
}
