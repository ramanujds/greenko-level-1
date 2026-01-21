package exceptionhandling;
public class ProcessItem {
	
	static void processItem(String item)throws InvalidItemException {
		if(item == null || item.isBlank()) {
			throw new InvalidItemException("Item is blank or null");
		}
		System.out.println(item+ " processed..");
	}
	
	public static void main(String[] args) {
		String item = "";
		try {
			processItem(item);
		} catch (InvalidItemException e) {
			e.printStackTrace();
		}
	}

}
