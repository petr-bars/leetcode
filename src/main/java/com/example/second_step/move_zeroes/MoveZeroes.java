package com.example.second_step.move_zeroes;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero
 * elements.
 * Note that you must do this in-place without making a copy of the array.
 * Дан массив nums. Нужно переместить все нули в конец, сохранив порядок остальных элементов.
 * Всё делается на месте, без создания нового массива.
 * Пример:
 * Вход:  nums = [0,1,0,3,12]
 * Выход: [1,3,12,0,0]
 * Паттерн
 * Read/Write pointers — два указателя: один читает, другой пишет.
 * Идея
 * Идёшь по массиву одним указателем read.
 * Второй указатель write показывает, куда положить следующий ненулевой элемент.
 * Если nums[read] != 0 — это ненулевой. Кладём его на позицию write, сдвигаем write вперёд.
 * Если nums[read] == 0 — пропускаем, ничего не делаем.
 * После прохода все ненулевые стоят в начале (в исходном порядке), а хвост массива от write до конца заполняем нулями.
 * Формула
 * read:  0 → n-1
 * write: счётчик ненулевых
 * если nums[read] ≠ 0:
 * nums[write] = nums[read]
 * write++
 * после цикла: nums[write..n-1] = 0
 * Время: O(n) — один проход по массиву для переноса ненулевых + один проход для заливки нулей.
 * Оба линейные, суммарно O(n).
 * Память: O(1) — работаем на месте, никаких дополнительных массивов. Только два указателя.
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write] = nums[read];
                write++;
            }
        }

        while (write < nums.length) {
            nums[write++] = 0;
        }
    }
}
