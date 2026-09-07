package com.example.nineth_step.subsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsetsRecursion(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, new ArrayList<>(), nums, result);
        return result;
    }

    private void backtrack(int start, List<Integer> current, int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int index = start; index < nums.length; index++) {
            if (index > start && nums[index] == nums[index - 1]) {
                continue;
            }
            current.add(nums[index]);
            backtrack(index + 1, current, nums, result);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsIterative(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        int sizeBeforePreviousStep = 0;

        for (int index = 0; index < nums.length; index++) {
            int currentNumber = nums[index];
            int startAddingFrom = (index > 0 && currentNumber == nums[index - 1]) ? sizeBeforePreviousStep : 0;
            int currentSizeBeforeAdding = result.size();

            for (int subsetIndex = startAddingFrom; subsetIndex < currentSizeBeforeAdding; subsetIndex++) {
                List<Integer> newSubset = new ArrayList<>(result.get(subsetIndex));
                newSubset.add(currentNumber);
                result.add(newSubset);
            }

            sizeBeforePreviousStep = currentSizeBeforeAdding;
        }

        return result;
    }
}
