package com.example.first_step.product_of_array_except_self;

import java.util.Arrays;

/**
 * <p>Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].</p>
 *
 * <p>The product of any prefix or suffix of nums is guaranteed to fit in a
 * 32-bit integer. You must write an algorithm that runs in O(n) time and
 * without using the division operation.</p>
 *
 * <p>Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity
 * analysis.)</p>
 *
 * <p>Дан целочисленный массив nums. Вернуть массив answer, где answer[i] —
 * произведение всех элементов nums, кроме nums[i]. Деление использовать
 * нельзя. Время — O(n). Дополнительная память — O(1) (не считая выходного
 * массива).</p>
 *
 * <p>Пример:<br>
 * Вход:  nums = [1, 2, 3, 4]<br>
 * Выход: [24, 12, 8, 6]<br>
 * answer[0] = 2 × 3 × 4 = 24<br>
 * answer[1] = 1 × 3 × 4 = 12<br>
 * answer[2] = 1 × 2 × 4 = 8<br>
 * answer[3] = 1 × 2 × 3 = 6</p>
 *
 * <p>Паттерн:<br>
 * Prefix/Suffix products (два прохода).</p>
 *
 * <p>Именование переменных:<br>
 * answers — выходной массив. На первом проходе хранит префиксные произведения,
 * на втором — итоговый ответ. length — длина массива nums. rightProduct —
 * произведение всех элементов справа от текущего индекса (суффикс).
 * Изначально 1, потому что справа от последнего элемента ничего нет.
 * index — текущий индекс в цикле.</p>
 *
 * <p>Идея:<br>
 * Для каждой позиции i ответ = произведение всего слева от i, умноженное
 * на произведение всего справа от i. Это даёт полный набор элементов
 * «кроме самого i». Можно было бы для каждой позиции отдельно перемножать
 * всё слева и всё справа, но это O(n²). Два прохода решают задачу за O(n):
 * первый проход считает префиксные произведения и складывает их в answers,
 * второй проход считает суффиксные произведения в одной переменной и
 * домножает answers на них. Деление не используется, поэтому нули в массиве
 * не ломают логику.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Подготовка:
 *       <ul>
 *         <li>length = nums.length.</li>
 *         <li>answers = new int[length].</li>
 *         <li>answers[0] = 1 — база для префиксных произведений. Слева
 *             от нулевого элемента ничего нет, произведение пустого
 *             множества равно 1.</li>
 *         <li>rightProduct = 1 — база для суффиксных произведений. Справа
 *             от последнего элемента ничего нет, произведение пустого
 *             множества равно 1.</li>
 *       </ul>
 *   </li>
 *   <li>Проход 1 (слева направо). Заполняем префиксные произведения:
 *       <ul>
 *         <li>index от 1 до length - 1.</li>
 *         <li>answers[index] = answers[index - 1] * nums[index - 1].</li>
 *         <li>Смысл: answers[index] содержит произведение всех элементов
 *             от 0 до index - 1, то есть всё слева от index.</li>
 *       </ul>
 *   </li>
 *   <li>Проход 2 (справа налево). Домножаем на суффиксные произведения:
 *       <ul>
 *         <li>index от length - 1 вниз до 0.</li>
 *         <li>answers[index] *= rightProduct. Теперь в answers[index]
 *             лежит префикс × суффикс = ответ для позиции index.</li>
 *         <li>rightProduct *= nums[index]. Суффикс расширяется влево,
 *             чтобы на следующем шаге (index - 1) он включал nums[index].</li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем answers.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Порядок в проходе 2 критичен: сначала answers[index] *= rightProduct,
 *       потом rightProduct *= nums[index]. Если поменять местами, то в
 *       суффикс попадёт сам nums[index], и answer[index] будет равен
 *       произведению всего массива, а не всего кроме index.</li>
 *   <li>answers[0] = 1 — это не «пустое произведение ради красоты», это
 *       реальная база для рекуррентной формулы answers[index] =
 *       answers[index - 1] * nums[index - 1]. Без этой базы первый шаг
 *       прохода 1 не с чего начать.</li>
 *   <li>rightProduct = 1 — то же самое для прохода 2. На первой итерации
 *       (index = length - 1) справа ничего нет, поэтому суффикс = 1.
 *       answers[length - 1] *= 1 не меняет префиксное значение, что
 *       корректно: для последнего элемента ответ = произведение всего
 *       слева.</li>
 *   <li>Проход 2 идёт с length - 1, а не с length - 2. Потому что
 *       последний элемент тоже нужно обработать — для него суффикс = 1,
 *       но умножение на 1 и последующее расширение rightProduct нужны,
 *       чтобы на следующем шаге суффикс уже включал последний элемент.</li>
 *   <li>Дополнительная память — O(1). Единственная переменная вне выходного
 *       массива — rightProduct. Всё остальное пишется прямо в answers.</li>
 *   <li>Деление не используется. Наивный путь «перемножить всё и поделить
 *       на nums[i]» ломается на нулях (деление на ноль) и на случаях, когда
 *       в массиве больше одного нуля. Префикс × суффикс работает всегда,
 *       независимо от нулей.</li>
 *   <li>В массиве с нулями ответ всё равно корректный. Например,
 *       nums = [0, 1, 2]. Префиксы: [1, 0, 0]. Суффиксы справа налево:
 *       для index=2 суффикс = 1 → answer[2] = 0, rightProduct = 2.
 *       Для index=1 суффикс = 2 → answer[1] = 0, rightProduct = 2.
 *       Для index=0 суффикс = 2 → answer[0] = 2, rightProduct = 0.
 *       Ответ [2, 0, 0]. Проверка: answer[0] = 1 × 2 = 2 ✓,
 *       answer[1] = 0 × 2 = 0 ✓, answer[2] = 0 × 1 = 0 ✓.</li>
 *   <li>Оба прохода пишут в один и тот же массив answers. Первый проход
 *       заполняет его префиксами, второй — умножает на суффиксы. Никаких
 *       дополнительных массивов prefix[] и suffix[] не нужно — это и даёт
 *       O(1) дополнительной памяти.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>nums = [1,2,3,4] → [24, 12, 8, 6]. Классический случай.</li>
 *   <li>nums = [-1,1,0,-3,3] → [0, 0, 9, 0, 0]. Один ноль в середине.</li>
 *   <li>nums = [0,0] → [0, 0]. Два нуля.</li>
 *   <li>nums = [1,2] → [2, 1]. Минимальный размер.</li>
 *   <li>nums = [1] → [1]. Один элемент.</li>
 *   <li>nums = [5,5,5] → [25, 25, 25].</li>
 *   <li>nums = [-1,-2,-3] → [6, 3, 2]. Отрицательные.</li>
 *   <li>nums = [0,1] → [1, 0].</li>
 *   <li>nums = [1,0] → [0, 1].</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — два прохода по массиву.<br>
 * Память: O(1) дополнительной — только переменная rightProduct и счётчик
 *         index. Выходной массив answers не считается по условию.</p>
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 4};
//        int[] nums = new int[]{-1, 1, 0, -3, 3};
        int[] nums = new int[]{5, 5, 5};

        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return new int[0];
        }

        int[] answers = new int[length];
        answers[0] = 1;

        for (int index = 1; index < length; index++) {
            answers[index] = answers[index - 1] * nums[index - 1];
        }

        int rightProduct = 1;
        for (int index = length - 1; index >= 0; index--) {
            answers[index] *= rightProduct;
            rightProduct *= nums[index];
        }
        return answers;
    }
}
