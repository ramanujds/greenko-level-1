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

        List<Integer> nums = Arrays.asList(6,3,7,4,5,6,8);

        // Get the square of the first odd and print

        int sq = nums.stream()
                                .filter(n->n%2!=0)
                                .map(n->n*n)
                                .findFirst()
                                .get();


    }

}
