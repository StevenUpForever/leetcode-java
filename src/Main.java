import depth_first_search.parentheses.RemoveInvalidParentheses;

public class Main {
    public static void main(String[] args) {
        RemoveInvalidParentheses obj = new RemoveInvalidParentheses();
        System.out.println(obj.removeInvalidParentheses("())))"));
    }

    private static char[] convert(String s) {
        return s.toCharArray();
    }
}

