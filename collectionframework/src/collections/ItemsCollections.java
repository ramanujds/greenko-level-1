package collections;

import java.util.ArrayList;
import java.util.List;

public class ItemsCollections {
	
	public static void main(String[] args) {
		
		// List - Items
		
		List<Item> items = new ArrayList<>();
		
		// Create some items
		Item item1 = new Item(1,"phone",70000);
		Item item2 = new Item(2,"laptop",160000);
		Item item3 = new Item(3,"watch",10000);
		Item item4 = new Item(4,"bag",2000);
		Item item5 = new Item(5,"tablet",50000);
		
		// Add to the list
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		
		// Find all the items with price >= 50000 put in a list
		
		List<Item> costlyItems = new ArrayList<>();
		
		for(var item : items) {
			if(item.getPrice()>=50000) {
				costlyItems.add(item);
			}
		}
		
		System.out.println(costlyItems);
	
		
		// Find the most costly item 
		
		Item costly = costlyItems.getFirst();
		
		for(var item : items) {
			if(item.getPrice()>costly.getPrice()) {
				costly = item;
			}
		}
		
		System.out.println(costly);
		
		
		
	}

}
