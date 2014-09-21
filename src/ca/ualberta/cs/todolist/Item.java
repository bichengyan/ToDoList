package ca.ualberta.cs.todolist;

public class Item {
	
	protected String itemName;
	
	public Item(String itemName){
		this.itemName = itemName;
	}
	
	public String getName(){
		return this.itemName;
	}

	public String toString(){
		return getName();
	}
	
	
}
