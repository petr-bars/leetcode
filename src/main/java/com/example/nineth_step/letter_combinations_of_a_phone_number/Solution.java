package com.example.nineth_step.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        backtrack(0, mapping, new StringBuilder(), result, digits);
        return result;
    }

    private void backtrack(int index, String[] mapping, StringBuilder currentString, List<String> result, String digits) {
        if (index == digits.length()) {
            result.add(currentString.toString());
            return;
        }

        int indexOfLetter = digits.charAt(index) - '0';
        char[] letters = mapping[indexOfLetter].toCharArray();
        for (char aChar : letters) {
            backtrack(index + 1, mapping, currentString.append(aChar), result, digits);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }
}
