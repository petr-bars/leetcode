package com.example.nineth_step.combination_sum_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtracking(target, 0, candidates, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int leftSum, int start, int[] candidates, List<Integer> currentCombination, List<List<Integer>> result) {
        if (leftSum == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int index = start; index < candidates.length && candidates[index] <= leftSum; index++) {
            if (index > start && candidates[index] == candidates[index - 1]) {
                continue;
            }

            currentCombination.add(candidates[index]);
            backtracking(leftSum - candidates[index], index + 1, candidates, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
