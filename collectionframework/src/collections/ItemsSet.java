package collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class CompareByName implements Comparator<Item>{

	public int compare(Item i1, Item i2) {
	
		if(i1.getName().equals(i2.getName())) {
			return i1.getId()-i2.getId();
		}
		return i1.getName().compareTo(i2.getName());
	}
	
}

class CompareByPrice implements Comparator<Item>{

	public int compare(Item i1, Item i2) {
		return Double.compare(i1.getPrice(), i2.getPrice());
	}
	
}

public class ItemsSet {


	public static void main(String[] args) {
		
	
		Comparator<Item> compName = new CompareByName();
		Comparator<Item> compPrice = new CompareByPrice();

		Set<Item> items = new TreeSet<>(compName);

		// Create some items
		Item item1 = new Item(1, "phone", 70000);
		Item item2 = new Item(2, "laptop", 160000);
		Item item3 = new Item(3, "watch", 10000);
		Item item4 = new Item(4, "bag", 2000);
		Item item5 = new Item(5, "tablet", 50000);
		Item item6 = new Item(6, "tablet", 50000);

		// Add to the list
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);

		
		for(var item:items) {
			System.out.println(item);
		}
		

	}

}
