import map_set.GroupShiftedStrings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordBreakII obj = new WordBreakII();
        List<String> aaa = new ArrayList<>();
        for (String a: new String[]{"aaaa","aa","a"}) aaa.add(a);
        for (String str: obj.wordBreak("aaaaaaa", aaa))
            System.out.println(str);
    }
}

