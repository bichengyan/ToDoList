package ca.ualberta.cs.todolist;

public class Item {
	
	protected String itemName;
	public boolean box;
	public int image;
	public String itemStatus = "TODO";
	
	public Item(String itemName){
		this.itemName = itemName;
	}
	
	public String getName(){
		return this.itemName;
	}

	public String toString(){
		return getName();
	}

	public String getStatus() {
		return this.itemStatus;
	}	
}
