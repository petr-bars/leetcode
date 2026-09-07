package com.example.nineth_step.permutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> permuteUniqueRecursive(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(0);
        boolean[] used = new boolean[nums.length];
        backtrack(current, nums, result, used);
        return result;
    }

    private void backtrack(List<Integer> current, int[] nums, List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (used[index]) {
                continue;
            }

            if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) {
                continue;
            }

            current.add(nums[index]);
            used[index] = true;
            backtrack(current, nums, result, used);
            current.remove(current.size() - 1);
            used[index] = false;
        }
    }
}