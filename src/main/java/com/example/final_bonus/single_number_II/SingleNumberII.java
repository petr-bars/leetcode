package com.example.final_bonus.single_number_II;

/**
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class SingleNumberII {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 2};
        Solution solution = new Solution();
        System.out.println(solution.singleNumber(nums));
    }
}
