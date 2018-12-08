package genericDocOracle;

import java.util.Arrays;
import java.util.Collection;

interface UnaryPredicate<T> {
    public boolean test(T obj);
}

class Algorithm{
    public static <T> int countIf(Collection<T> c, UnaryPredicate<T> p){
        int count = 0;
        for (T elem:c){
            if (p.test(elem)){
                count++;
            }
        }
        return count;
    }
}

public class OddPredicate implements UnaryPredicate<Integer> {// define genericDocOracle

    @Override
    public boolean test(Integer obj) {
        return obj % 2 != 0;
    }
}

class Test {
    public static void main(String[] args) {
        Collection<Integer> ci = Arrays.asList(1, 2, 3, 4);
        int count = Algorithm.countIf(ci, new OddPredicate());
        System.out.println("Number of odd integers = " + count);
    }
}


