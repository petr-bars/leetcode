package com.example.nineth_step.letter_combinations_of_a_phone_number;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 */
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String digits = "23";
        System.out.println(solution.letterCombinations(digits));
    }
}
