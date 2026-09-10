package com.example.tenth_step.longest_increasing_subsequence;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        Solution solution = new Solution();
//        System.out.println(solution.lengthOfLIS(nums));
        System.out.println(solution.lengthOfLISPS(nums));
    }
}
