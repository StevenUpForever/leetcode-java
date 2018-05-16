import two_pointers.ExpressiveWords;

public class Main {
    public static void main(String[] args) {
//        NumberOfIslands obj = new NumberOfIslands();
//        System.out.print(obj.numIslands2(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
//                {'1','1','0','0','0'},{'0','0','0','0','0'}}));

        ExpressiveWords obj = new ExpressiveWords();
        for (ExpressiveWords.Extension extension: obj.allExtensions("heeellooo")) {
            System.out.print(extension.start + "->" + extension.end);
            System.out.println();
        }

    }
}

