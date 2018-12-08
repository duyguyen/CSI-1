package genericOwnVersion;

import java.util.Arrays;
import java.util.Collection;

public class IsPrime {

    public static void main(String[] args) {
        IsPrime isPrime = new IsPrime();
        Collection<Integer> collection = Arrays.asList(1,3,7,9);
        int count = isPrime.countPrimeNumber(collection, new PrimeNumver());

        System.out.println("Number of prime = " + count);
    }

    // ===============================BEGIN GENERIC METHOD TO COUNT PRIME INTEGER============================================ //

    public <T> int countPrimeNumber(Collection<T> collection, GenericPrime<T> g){
        int count = 0;
        for (T elem: collection){
            if (g.test(elem)){
                count++;
            }
        }

        return count;
    }

    // ===============================END GENERIC METHOD TO COUNT PRIME INTEGER============================================ //

//    public final class Algorithm12 {
//        public <T> T max(T x, T y) {
//            return x > y ? x : y;
//        }
//    }
}

interface GenericPrime<T>{
    boolean test(T obj);
}

class PrimeNumver implements GenericPrime<Integer>{
    @Override
    public boolean test(Integer n) {
        if(n == 1){
            return false;
        }else{
            for(int i = 2; i <= Math.sqrt((double)n); i++){
                if(n % i == 0){
                    return false;
                }
            }return true;
        }
    }
}
