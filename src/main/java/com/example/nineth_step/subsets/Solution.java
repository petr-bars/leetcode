package com.example.nineth_step.subsets;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsetsRecursion(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, new ArrayList<>(), nums, result);
        return result;
    }

    private void backtrack(int start, List<Integer> current, int[] nums, List<List<Integer>> result) {
        // Добавляем копию текущего подмножества
        result.add(new ArrayList<>(current));

        for (int index = start; index < nums.length; index++) {
            current.add(nums[index]);               // включаем nums[i]
            backtrack(index + 1, current, nums, result);
            current.remove(current.size() - 1); // откат
        }
    }

    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // пустое подмножество

        for (int num : nums) {
            int currentSize = result.size();
            for (int index = 0; index < currentSize; index++) {
                List<Integer> newSubset = new ArrayList<>(result.get(index));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        return result;
    }
}
