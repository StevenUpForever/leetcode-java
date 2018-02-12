import depth_first_search.FloodFill;

public class Main {
    public static void main(String[] args) {
        FloodFill obj = new FloodFill();
        System.out.println(obj.floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}}, 1, 1, 2));
    }
}

