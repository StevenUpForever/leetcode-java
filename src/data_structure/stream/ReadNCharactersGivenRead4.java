package data_structure.stream;

public class ReadNCharactersGivenRead4 {

    //TAG: Facebook
    //TAG: stream
    //Difficulty: Easy

    /**
     * 157. Read N Characters Given Read4
     * The API: int read4(char *buf) reads 4 characters at a time from a file.
     *
     * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
     *
     * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
     *
     * Example 1:
     *
     * Input: buf = "abc", n = 4
     * Output: "abc"
     * Explanation: The actual number of characters read is 3, which is "abc".
     * Example 2:
     *
     * Input: buf = "abcde", n = 5
     * Output: "abcde"
     * Note:
     * The read function will only be called once for each test case.
     */

    /*
     * Solution:
     * Key point here is:
     *  1. when n > 4, need continue read4 and fill buff and until n chars filled
     *  2. when e.g. 3 chars left need to fill, cannot fill all 4 chars from new read4, but first 3 chars from new read4
     *
     *  so keep read4 and fill to buf until read4 n chars filled or read4 not enough 4 chars and all filled
     *
     *  Time: O(min(sizeOf(file), n))
     *  Space: O(1)
     */

    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        //Index represent current index of buf and final length filled
        int index = 0;
        while (index < n) {
            int count = read4(temp);
            //count is the correct length but not 4, due to read4 may not have enough 4 chars
            for (int i = 0; i < count && index < n; i++)
                buf[index++] = temp[i];
            //if read4 has not enough 4 chars, need to break the loop, will has no more chars
            if (count < 4) break;
        }
        return index;
    }

    int read4(char[] buf) {
        return 0;
    }

}
