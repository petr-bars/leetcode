package com.example.nineth_step.permutationsII;


import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 */
public class PermutationsII {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        Solution solution = new Solution();
        List<List<Integer>> result = solution.permuteUniqueRecursive(nums);
        System.out.println(result);
    }
}
