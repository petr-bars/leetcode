package com.example.tenth_step.partition_equal_subset_sum;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of
 * the elements in both subsets is equal or false otherwise.
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        Solution solution = new Solution();
        System.out.println(solution.canPartition(nums));
    }
}
