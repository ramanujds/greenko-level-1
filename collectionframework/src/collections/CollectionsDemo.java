package collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CollectionsDemo {
	
	public static void main(String[] args) {
		
		List<String> items = new ArrayList<>();
		
		items.add("laptop");
		items.add("phone");
		items.add("watch");
		items.add("bottle");
		items.add(null);
		
		Collections.sort(items);
		
//		items.sort((s1,s2)->s1.compareToIgnoreCase(s2));
		
//		var sortedList = items.stream().sorted().toList();
		
		for(var item:items) {
			System.out.println(item.toUpperCase());
		}
		
		
		
	}
	

}
