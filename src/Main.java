import stack.ExclusiveTimeOfFunctions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ExclusiveTimeOfFunctions obj = new ExclusiveTimeOfFunctions();
        List<String> list = new ArrayList<>();
        for (String str: new String[]{"0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"})
            list.add(str);
        for (int i: obj.exclusiveTime(1, list))
            System.out.println(i);
    }
}

