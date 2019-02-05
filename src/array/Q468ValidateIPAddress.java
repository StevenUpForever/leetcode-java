package array;

public class Q468ValidateIPAddress {

    //Difficulty: medium
    //TAG: Uber
    //TAG: array

    /**
     * 468. Validate IP Address
     * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
     *
     * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
     *
     * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
     *
     * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
     *
     * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
     *
     * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
     *
     * Note: You may assume there is no extra space or special characters in the input string.
     *
     * Example 1:
     * Input: "172.16.254.1"
     *
     * Output: "IPv4"
     *
     * Explanation: This is a valid IPv4 address, return "IPv4".
     * Example 2:
     * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
     *
     * Output: "IPv6"
     *
     * Explanation: This is a valid IPv6 address, return "IPv6".
     * Example 3:
     * Input: "256.256.256.256"
     *
     * Output: "Neither"
     *
     * Explanation: This is neither a IPv4 address nor a IPv6 address.
     */

    /*
    Solution:
    Verify IPv4 and IPv6 separately
    common check:
    without prefix ./: and suffix ./:
    IPv4:
    array separate by . need be length 4
    every part is integer 0 - 255, without any other chars (e.g. a b + -)
    IPv6:
    array separate by : need be length 8
    every part only contains 0-9 a-f A-F

    Time: O(n)
    Space: O(n)
     */

    public String validIPAddress(String IP) {
        if (validIPv4(IP)) return "IPv4";
        else if (validIPv6(IP)) return "IPv6";
        else return "Neither";
    }

    private boolean validIPv4(String IP) {
        if (IP == null || IP.startsWith(".") || IP.endsWith(".")) return false;
        String[] strs = IP.split("\\.");
        if (strs.length != 4) return false;
        for (String str: strs) {
            //If str start with 0 but value is not 0
            if (str.length() > 1 && str.charAt(0) == '0') return false;
            try {
                int value = Integer.valueOf(str);
                //When value is over limit, or value is 0, but str != '0' e.g. -0 +0
                if (value < 0 || value > 255 || (value == 0 && !str.equals("0"))) return false;
                //If catch the exception that can not parse string, do not throw but return false
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private boolean validIPv6(String IP) {
        if (IP == null || IP.startsWith(":") || IP.endsWith(":")) return false;
        String[] strs = IP.split(":");
        if (strs.length != 8) return false;
        for (String str: strs) {
            if (str.length() == 0 || str.length() > 4) return false;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                boolean isDigit = Character.isDigit(c);
                boolean isUpperCase = c - 'A' >= 0 && c - 'F' <= 0;
                boolean isLowerCase = c - 'a' >= 0 && c - 'f' <= 0;
                if (!(isDigit || isUpperCase || isLowerCase)) return false;
            }
        }
        return true;
    }

}
