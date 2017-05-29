import Problem81To90.Merge_Sorted_Array;
import Problem81To90.Search_in_Rotated_Sorted_Array_II;

public class Main {
    public static void main(String[] args) {
        Merge_Sorted_Array obj = new Merge_Sorted_Array();
        int[] nums1 = new int[]{1, 3, 5, 0, 0, 0}, nums2 = new int[]{2, 4, 6};
        obj.mergeS2(nums1, 3, nums2, 3);
        for (int num: nums1) System.out.println(num);
    }
}

