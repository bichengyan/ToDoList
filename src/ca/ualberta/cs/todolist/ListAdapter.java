package ca.ualberta.cs.todolist;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	Context context;
	LayoutInflater lInflater;
	ArrayList<Item> ItemLists;

	ListAdapter(Context context, ArrayList<Item> Items) {
		this.context = context;
		ItemLists = Items;
		lInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return ItemLists.size();
	}

	@Override
	public Object getItem(int position) {
		return ItemLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.item, parent, false);
		}

		Item i = get_Item(position);
		
		((TextView) view.findViewById(R.id.itemNames)).setText(i.getName());
		((TextView) view.findViewById(R.id.itemStatus)).setText(i.getStatus());
		((ImageView) view.findViewById(R.id.ivImage)).setImageResource(i.getImage());

		CheckBox checkbox = (CheckBox) view.findViewById(R.id.cbBox);
		checkbox.setOnCheckedChangeListener(myCheckChangeList);
		checkbox.setTag(position);
		checkbox.setChecked(i.box);
		return view;
	}

	Item get_Item(int position) {
		return ((Item) getItem(position));
	}
	
	ArrayList<Item> getBox() {
		ArrayList<Item> box = new ArrayList<Item>();
		for (Item i : ItemLists) {
				box.add(i);
		}
		return box;
	}
	
	ArrayList<Item> getCheckedBox() {
		ArrayList<Item> box = new ArrayList<Item>();
		for (Item i : ItemLists) {
			if (i.box)
				box.add(i);
		}
		return box;
	}
	
	ArrayList<Item> getUncheckedBox() {
		ArrayList<Item> box = new ArrayList<Item>();
		for (Item i : ItemLists) {
			if (!(i.box))
				box.add(i);
		}
		return box;
	}
	
	OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			get_Item((Integer) buttonView.getTag()).box = isChecked;
		}
	};
}