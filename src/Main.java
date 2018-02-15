import string.LicenseKeyFormatting;

public class Main {
    public static void main(String[] args) {

        LongestAbsoluteFilePath obj = new LongestAbsoluteFilePath();
        System.out.println(obj.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println("dir/subdir2/subsubdir2/".length());
    }
}

