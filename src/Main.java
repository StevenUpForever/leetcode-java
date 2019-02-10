import bit_operation.IPToCIDR;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IPToCIDR obj = new IPToCIDR();
        System.out.println(obj.ipToCIDR("255.0.0.7", 10));
    }
}



