package com.example.final_bonus.subarray_sum_equals_k;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.subarraySum(nums, k));
    }
}
