import math.random.RandomPickWithWeight;

public class Main {
    public static void main(String[] args) {
//        NumberOfIslands obj = new NumberOfIslands();
//        System.out.print(obj.numIslands2(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
//                {'1','1','0','0','0'},{'0','0','0','0','0'}}));

//        ExpressiveWords obj = new ExpressiveWords();
//        for (ExpressiveWords.Extension extension: obj.allExtensions("heeellooo")) {
//            System.out.print(extension.start + "->" + extension.end);
//            System.out.println();
//        }

        RandomPickWithWeight.Solution obj = new RandomPickWithWeight.Solution(new int[]{1});
        System.out.println(obj.pickIndex());
    }


}

