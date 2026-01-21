package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample {

    static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,3,7,4,5,6,8);

//        var odds = new ArrayList<Integer>();
//
//        for (int n:nums){
//            if (n%2!=0){
//                odds.add(n);
//            }
//        }
//
//        var squares = new ArrayList<Integer>();
//        for (int n:odds){
//            squares.add(n*n);
//        }
//
//        System.out.println(odds);
//        System.out.println(squares);

        // get the squares of the odd numbers

        // get sum
//        int sum = 0;




        int sum = nums.stream()
                            .filter(n -> n%2!=0)
                            .mapToInt(n->n*n)
                            .sum();


        System.out.println(sum);


    }

}
