package bit_operation;

public class Q393UTF8Validation {

    //TAG: Google
    //TAG: Bit operation
    //Difficulty: Medium

    /**
     * 393. UTF-8 Validation
     * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

     For 1-byte character, the first bit is a 0, followed by its unicode code.
     For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
     This is how the UTF-8 encoding would work:

     Char. number range  |        UTF-8 octet sequence
     (hexadecimal)    |              (binary)
     --------------------+---------------------------------------------
     0000 0000-0000 007F | 0xxxxxxx
     0000 0080-0000 07FF | 110xxxxx 10xxxxxx
     0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
     0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

     Note:
     The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

     Example 1:

     data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

     Return true.
     It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
     Example 2:

     data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

     Return false.
     The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
     The next byte is a continuation byte which starts with 10 and that's correct.
     But the second continuation byte does not start with 10, so it is invalid.
     */

    /**
     * Solution:
     * Simplify the question:
     * it means if 1 bit number, start with 0
     * if n bit number, 1st number start with n 1s, next n - 1 numbers should have 10 as prefix, other numbers
     * should be new 1 or n bit number
     *
     * loop numbers in data, check how many 1s as prefix, it should be n bit number
     * check next n - 1 over data.length or any number within n - 1 numbers start with 10 return false
     * move index to number after n bit number start new check
     * if passed all checks, return true
     */

    public boolean validUtf8(int[] data) {
        if(data == null || data.length == 0) return false;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > 255) return false; //over 8 bit, 100000000
            int numberOfBytes;
            if((data[i] & 128) == 0) { //1 byte, 128(10000000)
                numberOfBytes = 1;
                //Try with 1 more 1 bit than 11000000, 11100000 etc. to check number first most continuously 1s
            } else if((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
                numberOfBytes = 2;
            } else if((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
                numberOfBytes = 3;
            } else if((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
                numberOfBytes = 4;
            } else {
                //because max 4 numbers
                return false;
            }
            for(int j = 1; j < numberOfBytes; j++) {
                /*
                If data do not have enough numbers or
                If next some n bit number is not start with 10 return false // 192(11000000), 128(10000000)
                 */
                if(i + j >= data.length || (data[i + j] & 192) != 128) return false;
            }
            //Jump to next 1 bit number
            i = i + numberOfBytes - 1;
        }
        return true;
    }

}
