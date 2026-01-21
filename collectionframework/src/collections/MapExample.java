package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
	
	public static void main(String[] args) {
		
		Map<Integer, Item> items = new TreeMap<>();
		
		Item item1 = new Item(101,"phone",70000);
		Item item2 = new Item(302,"laptop",160000);
		Item item3 = new Item(103,"watch",10000);
		Item item4 = new Item(104,"bag",2000);
		Item item5 = new Item(205,"tablet",50000);
		Item item6 = new Item(501,"mic",5000);
	
		
		items.put(item1.getId(),item1);
		items.put(item2.getId(),item2);
		items.put(item3.getId(),item3);
		items.put(item4.getId(),item4);
		items.put(item5.getId(),item5);
		items.put(item6.getId(),item6);

		
//		for(int key:items.keySet()) {
//			System.out.println(items.get(key));
//		}
		
		// O(1)
		
		for(var item:items.entrySet()) {
			System.out.println(item.getValue());
		}
		
		
	}

}
