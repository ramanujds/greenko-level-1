package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ItemsSet {

	public static void main(String[] args) {

		Set<Item> items = new TreeSet<>();

		// Create some items
		Item item1 = new Item(1, "phone", 70000);
		Item item2 = new Item(2, "laptop", 160000);
		Item item3 = new Item(3, "watch", 10000);
		Item item4 = new Item(4, "bag", 2000);
		Item item5 = new Item(5, "tablet", 50000);
		Item item6 = new Item(5, "tablet", 50000);
		Item item7 = new Item(5, "tablet", 50000);

		// Add to the list
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		
		for(var item:items) {
			System.out.println(item);
		}
		

	}

}
