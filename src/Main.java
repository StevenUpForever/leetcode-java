import binary_search.GuessNumberHigherOrLower;
import depth_first_search.NumberOfIslands;

public class Main {
    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();
        System.out.print(obj.numIslands2(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
                {'1','1','0','0','0'},{'0','0','0','0','0'}}));

    }
}

