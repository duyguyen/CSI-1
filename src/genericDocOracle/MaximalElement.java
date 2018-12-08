package genericDocOracle;

import java.util.List;

public class MaximalElement {

    public <T extends Object & Comparable<? super T>> T max(List<? extends T> list, int begin, int end){
        T maxElement = list.get(begin);
        for (++begin; begin<end; ++begin){
            if (maxElement.compareTo(list.get(begin))< 0){
                maxElement = list.get(begin);
            }
        }
        return maxElement;
    }
}
