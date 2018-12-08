package genericDocOracle;

public class ExchangePosition {

    public static void main(String[] args) {

        Integer[] ints = {10, 20, 30, 40};
        ExchangePosition exchangePosition = new ExchangePosition();
        exchangePosition.exchangePosition(ints); // exchange int[]

        int count = 1;
        for (int num : ints) {
            System.out.println(count + ". " + num);
            count++;
        }
    }


    public <T> void exchangePosition(T[] array) {
        for (int a = 0; a < array.length - 1; a++){
            T temp = array[a];
            array[a] = array[a + 1];
            array[a + 1] = temp;
        }
    }
}
