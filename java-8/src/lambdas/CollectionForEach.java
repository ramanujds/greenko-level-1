package lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CollectionForEach {

    static int play(Supplier<Integer> value){
        return value.get();
    }

    static void main() {
        List<Integer> nums = Arrays.asList(4,2,8,6,10,4);

        // Get the first odd number from here

        int odd = nums.stream().filter(n->n%2!=0)
                               .findFirst()
                                .orElseThrow(()->new RuntimeException("No odd elements"));
                        ;

        System.out.println(odd);

    }
}
