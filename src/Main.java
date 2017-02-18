import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main newMain = new Main();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(10);
        arr.add(2);
        arr.add(1);
        ArrayList<Integer> B = newMain.performOps(arr);
        for (int i = 0; i < B.size(); i++) {
            System.out.print(B.get(i) + " ");
        }
    }

    ArrayList<Integer> performOps(ArrayList<Integer> A) {
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int i = 0; i < 2 * A.size(); i++) B.add(0);
        for (int i = 0; i < A.size(); i++) {
            B.set(i, A.get(i));
            B.set(i + A.size(), A.get((A.size() - i) % A.size()));
        }
        return B;
    }


}

