package com.example.nineth_step.subsets;

import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution solution = new Solution();
//        List<List<Integer>> result = solution.subsetsRecursion(nums);
        List<List<Integer>> result = solution.subsetsIterative(nums);
        System.out.println(result);
    }
}
