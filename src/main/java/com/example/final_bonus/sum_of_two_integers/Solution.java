package com.example.final_bonus.sum_of_two_integers;

public class Solution {
    public int getSum(int a, int b) {
        // a = 0001  (1)
        // b = 0010  (2)

        while (b != 0) {
            // ШАГ 1: carry = (a & b) << 1
            // a & b:
            //   0001
            // & 0010
            // ------
            //   0000   ← нет позиций, где оба бита = 1
            //
            // (a & b) << 1:
            //   0000 << 1 = 0000
            //
            // carry = 0000  (0)
            int carry = (a & b) << 1;

            // ШАГ 2: a ^= b  (то же, что a = a ^ b)
            //   0001   (a)
            // ^ 0010   (b)
            // ------
            //   0011   ← сумма без переноса
            //
            // a = 0011  (3)
            a ^= b;

            // ШАГ 3: b = carry
            // b = 0000  (0)
            b = carry;
        }

        // return a = 0011  (3)
        return a;
    }
}
