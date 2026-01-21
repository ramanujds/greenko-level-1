package exceptionhandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class ExceptionTest {

	public static void main(String[] args) {

		try {
			FileReader reader = new FileReader("hello.txt");
		} catch (FileNotFoundException e) {

		}

		// List with few names
		// Get a name at a specified index

		List<String> list = Arrays.asList("Harsh", "Karan", "Amit",null);
		int index = 3;
		try {
			list.get(index).toUpperCase();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Array out of bound exception");
		}
		catch(NullPointerException e) {
			System.out.println("Value is null");
		}
		catch(Exception e) {
			
		}

//		System.out.println("Hello..");
//		System.out.println("Let's try some math operations..");
//		int a = 10; 
//		int b = 0;
//		try {
//			int output = a/b;
//			System.out.println("Division Done");
//			System.out.println("Output : "+output);
//		}
//		catch(ArithmeticException e) {
//			System.out.println("Sorry can't divide by zero");
//		}
//		System.out.println("Thank you..");
//		System.out.println("Have a nice day..");

	}

}
