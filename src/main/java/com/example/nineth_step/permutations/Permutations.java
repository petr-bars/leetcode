package com.example.nineth_step.permutations;

import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 */
public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution solution = new Solution();
//        List<List<Integer>> result = solution.permuteRecursive(nums);
        List<List<Integer>> result = solution.permuteIterative(nums);
        System.out.println(result);
    }
}
