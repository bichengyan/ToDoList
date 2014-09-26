package ca.ualberta.cs.todolist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import 	android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ItemListManager {
	//modified the following code based on 
	//https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListManager.java
	static final String prefFile = "ItemListFile";
	static final String ilKey = "itemList"; 
	static final String prefFile2 = "ArchivedItemListFile";
	static final String ailKey = "archivedItemList";
	Context context;
	
	static private ItemListManager itemListManager = null;
	
	public ItemListManager(Context context) {
		this.context = context;
	}
	
	public static void initManager(Context context) {
		if (itemListManager == null) {
			if (context==null) {
				throw new RuntimeException("missing context for ItemListManager");
			}
			itemListManager = new ItemListManager(context);
		}	
	}
	
	public static ItemListManager getManager() {
		if (itemListManager == null) {
			throw new RuntimeException("Did not initialize Manager");
		}
		return itemListManager;
	}
	
	public ItemList loadItemList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String ItemListData = settings.getString(ilKey, "");
		if (ItemListData.equals("")){
			return new ItemList();
		}
		else{
			return itemListFromString(ItemListData);
		}	 
	}
	
	public void saveItemList(ItemList item_list) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(ilKey, itemListToString(item_list));
		editor.commit();
	}
	
	public ItemList loadArchivedItemList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile2, Context.MODE_PRIVATE);
		String ArchivedItemListData = settings.getString(ailKey, "");
		if (ArchivedItemListData.equals("")){
			return new ItemList();
		}
		else{
			return itemListFromString(ArchivedItemListData);
		}	 
	}
	
	public void saveArchivedItemList(ItemList item_list) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile2, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(ailKey, itemListToString(item_list));
		editor.commit();
	}
	
	private ItemList itemListFromString(String itemListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(itemListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ItemList)oi.readObject();
	}
	
	private String itemListToString(ItemList item_list) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(item_list);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
}
