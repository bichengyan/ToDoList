package ca.ualberta.cs.todolist;

import java.io.Serializable;

public class Item implements Serializable{
	
	/**
	 * Item serialization ID
	 */
	private static final long serialVersionUID = 3139496345116943322L;
	protected String itemName;
	public boolean box;
	public int image = R.drawable.ic_todo;;
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

	public int getImage() {
		return this.image;
	}	
}
