package com.example.nineth_step.permutations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permuteRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(0);
        boolean[] used = new boolean[nums.length];
        backtracking(current, nums, result, used);
        return result;
    }

    private void backtracking(List<Integer> current, int[] nums, List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (!used[index]) {
                current.add(nums[index]);
                used[index] = true;
                backtracking(current, nums, result, used);
                current.remove(current.size() - 1);
                used[index] = false;
            }
        }
    }

    public List<List<Integer>> permuteIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(List.of(nums[0])));

        for (int index = 1; index < nums.length; index++) {
            int num = nums[index];
            List<List<Integer>> newResult = new ArrayList<>();
            for (List<Integer> perm : result) {
                for (int pos = 0; pos <= perm.size(); pos++) {
                    List<Integer> newPerm = new ArrayList<>(perm);
                    newPerm.add(pos, num);
                    newResult.add(newPerm);
                }
            }
            result = newResult;
        }
        return result;
    }
}
