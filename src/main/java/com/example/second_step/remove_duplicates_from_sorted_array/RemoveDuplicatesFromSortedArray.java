package com.example.second_step.remove_duplicates_from_sorted_array;

/**
 * <p>Given an integer array nums sorted in non-decreasing order, remove the
 * duplicates in-place such that each unique element appears only once. The
 * relative order of the elements should be kept the same.</p>
 *
 * <p>Consider the number of unique elements in nums to be k. After removing
 * duplicates, return the number of unique elements k. The first k elements
 * of nums should contain the unique numbers in sorted order. The remaining
 * elements beyond index k - 1 can be ignored.</p>
 *
 * <p>Дан отсортированный массив nums. Удалить дубликаты на месте так, чтобы
 * каждый уникальный элемент встречался один раз. Вернуть k — количество
 * уникальных. Первые k ячеек должны содержать уникальные в исходном порядке.
 * Остальное — не важно.</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [1,1,2]<br>
 * Выход: k = 2, nums = [1,2,_]</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [0,0,1,1,1,2,2,3,3,4]<br>
 * Выход: k = 5, nums = [0,1,2,3,4,_,_,_,_,_]</p>
 *
 * <p>Паттерн:<br>
 * Read/Write pointers — тот же, что в Move Zeroes.</p>
 *
 * <p>Именование переменных:<br>
 * write — указатель, который показывает, где лежит последний уникальный
 * элемент. Медленный. read — указатель, который читает массив. Быстрый.
 * Начинается с 1, потому что первый элемент уже уникальный по определению.</p>
 *
 * <p>Идея:<br>
 * Массив отсортирован, значит все дубликаты стоят подряд. Достаточно
 * сравнивать текущий элемент с последним уникальным. Если они разные —
 * нашли новый уникальный, кладём его после предыдущего. Если одинаковые —
 * пропускаем. write держит позицию последнего уникального, read ищет
 * следующий отличный.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>write = 0 — индекс последнего уникального.</li>
 *   <li>для read от 1 до n-1: если nums[read] != nums[write] →
 *       write++, nums[write] = nums[read].</li>
 *   <li>вернуть write + 1 — количество уникальных.</li>
 * </ul>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация: write = 0. Первый элемент считаем уникальным,
 *       ни с чем не сравниваем.</li>
 *   <li>Проход, read от 1 до n-1:
 *       <ul>
 *         <li>Если nums[read] != nums[write] — нашли новый уникальный.
 *             Сдвигаем write вперёд и записываем туда nums[read].</li>
 *         <li>Если nums[read] == nums[write] — дубликат, пропускаем.</li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем write + 1. write — индекс последнего уникального,
 *       значит количество уникальных = write + 1.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Массив должен быть отсортирован. Иначе дубликаты могут стоять
 *       не подряд, и сравнение с последним уникальным не сработает.
 *       Например, в [1,2,1] второй 1 не равен nums[write]=2, и алгоритм
 *       ошибочно посчитает его новым уникальным.</li>
 *   <li>write — индекс последнего уникального, а не количество. Количество
 *       уникальных = write + 1, поэтому возвращаем write + 1.</li>
 *   <li>read начинается с 1, а не с 0. Первый элемент уже уникальный
 *       по определению — не с чем его сравнивать.</li>
 *   <li>Сравнение именно с nums[write], а не с nums[read-1]. Оба варианта
 *       работают для отсортированного массива, но сравнение с nums[write]
 *       напрямую отражает идею «сравниваем с последним уникальным».</li>
 *   <li>Хвост массива после write остаётся как был — там старые значения.
 *       LeetCode проверяет только первые k ячеек, остальное игнорируется.
 *       Если бы мы хотели затереть хвост, понадобился бы отдельный проход,
 *       как в Move Zeroes.</li>
 *   <li>Пустой массив — цикл не выполнится, вернётся 0 + 1 = 1. Но по
 *       условиям LeetCode n &gt;= 1, так что этого случая не будет.
 *       Если хочешь защититься — добавь проверку в начале.</li>
 *   <li>Массив из одного элемента — цикл не выполнится, вернётся 1.</li>
 *   <li>Все элементы одинаковые — ни одно сравнение не даст «не равно»,
 *       write останется 0, вернётся 1.</li>
 *   <li>Все элементы уникальные — каждое сравнение даст «не равно»,
 *       write пройдёт до n-1, вернётся n.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>nums = [1,1,2] → 2, nums = [1,2,2].</li>
 *   <li>nums = [0,0,1,1,1,2,2,3,3,4] → 5, nums = [0,1,2,3,4,...].</li>
 *   <li>nums = [1,1,2,3] → 3, nums = [1,2,3,3].</li>
 *   <li>nums = [1] → 1.</li>
 *   <li>nums = [1,1,1] → 1.</li>
 *   <li>nums = [1,2,3] → 3.</li>
 *   <li>nums = [-3,-1,-1,0,0,2] → 4, nums = [-3,-1,0,2,...].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход по массиву.<br>
 * Память: O(1) — только указатели write и read, работа на месте.</p>
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int[] nums = new int[]{1, 1, 2, 3};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int write = 0;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[write]) {
                write++;
                nums[write] = nums[read];
            }
        }
        return write + 1;
    }
}
