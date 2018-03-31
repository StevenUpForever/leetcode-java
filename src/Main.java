import depth_first_search.parentheses.RemoveInvalidParentheses;

public class Main {
    public static void main(String[] args) {
        GuessNumberHigherOrLower obj = new GuessNumberHigherOrLower();
        System.out.println(obj.guessNumber(10));
    }

    private static char[] convert(String s) {
        return s.toCharArray();
    }
}

