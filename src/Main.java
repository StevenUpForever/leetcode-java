import javafx.stage.StageStyle;

import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
//        ProblemFrom1To10 mySolve = new ProblemFrom1To10();
//        ProblemFrom11To20 mySolveTwo = new ProblemFrom11To20();
//        ProblemFrom21To30 mySolveThree = new ProblemFrom21To30();
        ProblemFrom31To40 mySolveFour = new ProblemFrom31To40();
        int testArray[] = {1};

        System.out.print(mySolveFour.countAndSay(4));
        StringBuilder builder = new StringBuilder();
        builder.append(1 + "1".charAt(0));
        System.out.print(builder);
//        String testStr = "abcde".substring(0, 2);
//        System.out.print(testStr);




    }


}

