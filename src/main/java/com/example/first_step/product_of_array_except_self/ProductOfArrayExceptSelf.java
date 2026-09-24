package com.example.first_step.product_of_array_except_self;

import java.util.Arrays;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product
 * of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 * <p>
 * Дан целочисленный массив nums.
 * Нужно вернуть массив answer, где answer[i] равно произведению всех элементов nums, кроме nums[i].
 * Важно:
 * Нельзя использовать деление.
 * Решение должно работать за O(n).
 * Дополнительная память: O(1) (не считая выходного массива).
 * Пример:
 * Вход: nums = [1, 2, 3, 4]
 * Выход: [24, 12, 8, 6]
 * answer[0] = 2 × 3 × 4 = 24
 * answer[1] = 1 × 3 × 4 = 12
 * answer[2] = 1 × 2 × 4 = 8
 * answer[3] = 1 × 2 × 3 = 6
 * <p>
 * Паттерн: Prefix/Suffix products (два прохода).
 * Ключевая идея
 * ответ[i] = произведение слева × произведение справа.
 * Проход 1 (слева направо): в answers[i] кладёшь произведение всего слева. База answers[0] = 1.
 * Проход 2 (справа налево): держишь rightProduct — произведение всего справа. Изначально 1.
 * На каждом шаге:
 * answers[i] *= rightProduct — теперь там ответ для i;
 * rightProduct *= nums[i] — суффикс для следующего.
 * Порядок важен: сначала умножаешь answers[i], потом обновляешь rightProduct.
 * Иначе текущий элемент попадёт в свой же суффикс.
 * Почему без деления: деление ломается на нулях. Префикс × суффикс работает всегда.
 * Сложность:
 * O(n) время,
 * O(1) память (не считая выходного массива).
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
//        int[] nums = new int[]{-1, 1, 0, -3, 3};

        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return new int[0];
        }

        int[] answers = new int[length];
        answers[0] = 1;
        for (int index = 1; index < nums.length; index++) {
            answers[index] = answers[index - 1] * nums[index - 1];
        }

        int rightProduct = 1;
        for (int index = length - 1; index >= 0; index--) {
            answers[index] = answers[index] * rightProduct;
            rightProduct *= nums[index];
        }

        return answers;
    }
}
