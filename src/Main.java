import breadth_first_search.IsGraphBipartite;

public class Main {
    public static void main(String[] args) {

        IsGraphBipartite obj = new IsGraphBipartite();
        System.out.println(obj.isBipartite(new int[][]{{1,2,3},{0,3,4},{0,3}, {0,1,2}, {1}}));
        System.out.println(obj.isBipartite(new int[][]{{3},{2, 4},{1}, {0,4}, {1,3}}));
//        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
//        addToSets(new int[]{1, 2, 3}, set1, set2);
//        for (int num: set1) System.out.println(set1);
    }



}

