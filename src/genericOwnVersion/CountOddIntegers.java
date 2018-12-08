package genericOwnVersion;

import java.util.Arrays;
import java.util.Collection;

public class CountOddIntegers {
    public static void main(String[] args) {
        Collection<Integer> ci = Arrays.asList(1, 2, 3, 4, 5);
        CountOddIntegers countOddIntegers = new CountOddIntegers();
        int count = countOddIntegers.countOddInteger(ci, new Predicate());
        System.out.println("Number of odd integers = " + count);
    }

    public <T> int countOddInteger(Collection<T> collection, Generic<T> p) {
        int count = 0;
        for (T elem : collection) {
            if (p.test(elem)) {
                count++;
            }
        }
        return count;
    }
}

interface Generic<T> {
    boolean test(T obj);
}

class Predicate implements Generic<Integer> {
    @Override
    public boolean test(Integer obj) {
        return obj % 2 != 0;
    }
}
