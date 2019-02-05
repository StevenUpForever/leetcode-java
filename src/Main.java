import depth_first_search.EvaluateDivision;
import depth_first_search.ReconstructItinerary;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        ReconstructItinerary obj = new ReconstructItinerary();
        List<String> strs = obj.findItinerary(new String[][]{{"JFK", "A"}, {"JFK", "B"}, {"A", "Z"}, {"B", "Z"}});
        for (String str: strs) System.out.println(str);

    }




}



