import depth_first_search.EvaluateDivision;

public class Main {
    public static void main(String[] args) {

//        BestTimeToBuyAndSellStockII stockII = new BestTimeToBuyAndSellStockII();
//        System.out.println(stockII.maxProfit(new int[]{7,1,5,3,6,4}));
//        String[][] strs = new String[1][1];
//        System.out.println(strs[0][0]);

//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(random.nextInt(10));
//        }

        EvaluateDivision evaluateDivision = new EvaluateDivision();
        double[] res = evaluateDivision.calcEquation(new String[][]{{"x1","x2"},{"x2","x3"}, {"x3","x4"}, {"x4","x5"}},
                new double[]{3.0,4.0,5.0,6.0},
                new String[][]{{"x1","x5"},{"x5","x2"}, {"x2","x4"}, {"x2","x2"}, {"x2","x9"}, {"x9","x9"}});
        for (double num: res) System.out.println(num);
//        System.out.println(Math.floor(1.2345 * 100) / 100   );

//        System.out.print(String.valueOf(1/2.0));

    }




}



