import depth_first_search.all_subsets.PartitionToKEqualSumSubsets;

public class Main {
    public static void main(String[] args) {
        PartitionToKEqualSumSubsets obj = new PartitionToKEqualSumSubsets();
        System.out.println(obj.canPartitionKSubsets(new int[]{15,1,1,1,1,3,11,1,10}, 3));
    }
}

