import string.sliding_window.Q30SubstringWithConcatenationOfAllWords;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Q30SubstringWithConcatenationOfAllWords obj = new Q30SubstringWithConcatenationOfAllWords();
        List<Integer> list = obj.findSubstring("barfoothefoobarman", new String[]{"foo","bar"});
        for (int n: list) System.out.println(n);
    }
}



