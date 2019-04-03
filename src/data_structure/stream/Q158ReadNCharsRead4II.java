package data_structure.stream;

public class Q158ReadNCharsRead4II {

    //Difficulty: hard
    //TAG: Google
    //TAG: Facebook
    //TAG: Data structure

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

    /*
     * Solution:
     * be aware that n may smaller than 4 or larger than 4, need diff operations
     * The key point to read char[] multiple times, is keep the visited index where current index at, after previous
     * read, so that when run next read, could have preserved index where to start
     */

    private int index = 0;
    private int count = 0;
    private char[] curBuff = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        //Keep read until fully fill n chars
        while (ptr < n) {
            //If index == 0 means all chars already read from temp char[] to result char[] and need read next4 to index
            if (index == 0) count = read4(curBuff);
            //If buf is all read, then count could be 0, do not keep going otherwise will infinite loop
            if (count == 0) break;
            //Use condition that min(chars actual read, n), read from temp buff to res buff
            while (ptr < n && index < count) {
                buf[ptr++] = curBuff[index++];
            }
            // *** this means if n < 4, temp buff is not fully read, need wait next read to read to res buff ***
            if (index >= count) index = 0;
        }
        return ptr;
    }

    int read4(char[] buf) {
        return 0;
    }

}
