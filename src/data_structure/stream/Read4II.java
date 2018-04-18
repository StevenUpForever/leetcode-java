package data_structure.stream;

public class Read4II {

    //TAG: Google
    //TAG: data structure
    //Difficulty: Hard

    /**
     * 158. Read N Characters Given Read4 II - Call multiple times
     * The API: int read4(char *buf) reads 4 characters at a time from a file.
     *
     * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
     *
     * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
     *
     * Note:
     * The read function may be called multiple times.
     */

    /**
     * Solution:
     * The key point is, current read may not fill the n chars, or fill over current n chars
     * for fill over n chars, two situation:
     *      1. n < 4, so need more char arrays to fill
     *      2. n e.g. == 6, when fill first 4, next time it left 2 need another arr
     *
     * Normally readN will read N chars, but due to above reasons, as stream goes by, finally don't know how
     * many chars left, means last read is the important thing
     */

    private int index = 0;
    private int count = 0;
    //Used to save current status of last read char4
    private char[] tempChars = new char[4];
    public int read(char[] buf, int n) {
        int read = 0;
        //Keep reading until n filled or no more chars
        while (read < n) {
            //read from fresh start if index == 0
            if (index == 0) count = read4(tempChars);
            //no char read
            if (count == 0) break;
            //keep read 4 length chars to final n length chars, from start
            while (read < n && index < count) {
                buf[read++] = tempChars[index++];
            }
            //if index filled current buf, reset read
            if (index >= count) index = 0;
        }
        return read;
    }

    int read4(char[] buf) { return 0; }

}
