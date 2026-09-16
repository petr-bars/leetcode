package com.example.tenth_step.decode_ways;

/**
 * You have intercepted a secret message encoded as a string of numbers.
 * The message is decoded via the following mapping:
 * <p>
 * "1" -> 'A'
 * <p>
 * "2" -> 'B'
 * <p>
 * ...
 * <p>
 * "25" -> 'Y'
 * <p>
 * "26" -> 'Z'
 * <p>
 * However, while decoding the message, you realize that there are many different ways you can decode the message
 * because some codes are contained in other codes ("2" and "5" vs "25").
 * <p>
 * For example, "11106" can be decoded into:
 * <p>
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * If the entire string cannot be decoded in any valid way, return 0.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */
public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "12";
        System.out.println(solution.numDecodings(s));
        System.out.println(solution.numDecodingsOptimized(s));
    }
}
