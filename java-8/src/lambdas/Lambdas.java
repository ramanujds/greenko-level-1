package lambdas;

import java.util.List;

interface Printable{
    String print(String str);

}


public class Lambdas {
    public static void main(String[] args) {
        Printable printable = str -> str.toUpperCase();
        printable.print("Hello");

        List<Integer> nums = List.of();
    }
}
