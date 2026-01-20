package collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CollectionsDemo {
	
	public static void main(String[] args) {
		
		Collection<String> items = new ArrayList<>();
		
		items.add("laptop");
		items.add("phone");
		// index 2
		items.add("watch");
		
		items.add("bottle");
		items.remove("Watch");
		
		for(var item:items) {
			System.out.println(item.toUpperCase());
		}
		
		
		
	}
	

}
