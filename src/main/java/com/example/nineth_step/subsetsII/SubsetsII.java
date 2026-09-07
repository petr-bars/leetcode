package com.example.nineth_step.subsetsII;

import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class SubsetsII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        Solution solution = new Solution();
//        List<List<Integer>> result = solution.subsetsRecursion(nums);
        List<List<Integer>> result = solution.subsetsIterative(nums);
        System.out.println(result);
    }
}
