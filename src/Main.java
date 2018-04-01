import binary_search.GuessNumberHigherOrLower;

public class Main {
    public static void main(String[] args) {
        long res = 1;
        while (res < Integer.MAX_VALUE) {
            res *= 3;
        }
        System.out.println(res/3);

    }
}

