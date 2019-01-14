import best_first_search.CheapestFlightsWithinKStops;

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

        CheapestFlightsWithinKStops obj = new CheapestFlightsWithinKStops();
//        int[][] flights = {{1,2,10}, {2,0,7}, {1,3,8},
//                {4,0,10},{3,4,2},{4,2,10},{0,3,3},{3,1,6},{2,4,5}};

        int[][] flights = {{0,1,5}, {1,2,5}, {0,3,2},
                {3,1,2},{1,4,1},{4,2,1}};
        System.out.println(obj.findCheapestPrice2(5,flights, 0, 2, 2));
//        AlienDictionary dictionary = new AlienDictionary();
//        System.out.println(dictionary.alienOrder(new String[]{"za","zb","ca","cb"}));

    }
}

