package com.example.final_bonus.number_of_1_bits;

/**
 * Given a positive integer n, write a function that
 * returns the number of set bits in its binary representation (also known as the Hamming weight).
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        int n = 11;
        Solution solution = new Solution();
        System.out.println(solution.hammingWeight(n));
        System.out.println(solution.hammingWeightKernighan(n));
    }
}
