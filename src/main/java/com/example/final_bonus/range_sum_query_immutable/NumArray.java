package com.example.final_bonus.range_sum_query_immutable;

/**
 * Решение задачи "Range Sum Query - Immutable" (LeetCode 303).
 * <p>
 * Задача: дан целочисленный массив {@code nums}. Требуется многократно отвечать
 * на запросы вида {@code sumRange(left, right)} — сумма элементов массива
 * с индекса {@code left} по {@code right} включительно.
 * <p>
 * <b>Подход:</b> предварительное вычисление префиксных сумм (Prefix Sum).
 * <p>
 * <b>Идея:</b> создаётся вспомогательный массив {@code prefix} длиной {@code n + 1},
 * где {@code prefix[i]} — сумма первых {@code i} элементов исходного массива:
 * <pre>
 * prefix[0] = 0
 * prefix[1] = nums[0]
 * prefix[2] = nums[0] + nums[1]
 * ...
 * prefix[i] = nums[0] + nums[1] + ... + nums[i-1]
 * </pre>
 * Тогда сумма элементов от {@code left} до {@code right} включительно вычисляется
 * как разность:
 * <pre>
 * sumRange(left, right) = prefix[right + 1] - prefix[left]
 * </pre>
 * <p>
 * <b>Почему это работает:</b>
 * <ul>
 *   <li>{@code prefix[right + 1]} содержит сумму всех элементов от 0 до {@code right}.</li>
 *   <li>{@code prefix[left]} содержит сумму всех элементов от 0 до {@code left - 1}.</li>
 *   <li>Вычитая второе из первого, мы исключаем элементы до {@code left}
 *       и получаем ровно сумму от {@code left} до {@code right}.</li>
 * </ul>
 * <p>
 * <b>Сложность:</b>
 * <ul>
 *   <li>Построение в конструкторе: O(n) времени, O(n) памяти.</li>
 *   <li>Каждый запрос {@code sumRange}: O(1) времени.</li>
 * </ul>
 * <p>
 * <b>Преимущества:</b> многократные запросы обрабатываются мгновенно,
 * что критично при большом количестве вызовов.
 *
 * @see #NumArray(int[])
 * @see #sumRange(int, int)
 */
public class NumArray {
    /**
     * Массив префиксных сумм.
     * <p>
     * {@code prefix[i]} — сумма первых {@code i} элементов исходного массива.
     * Длина массива — {@code nums.length + 1}.
     * <p>
     * Поле объявлено {@code private final}, чтобы гарантировать:
     * <ul>
     *   <li>инкапсуляцию (никто извне не может изменить массив);</li>
     *   <li>неизменяемость ссылки после инициализации в конструкторе.</li>
     * </ul>
     */
    private final int[] prefix;

    /**
     * Конструктор, инициализирующий объект и строящий массив префиксных сумм.
     * <p>
     * <b>Алгоритм построения:</b>
     * <ol>
     *   <li>Создаётся массив {@code prefix} длиной {@code nums.length + 1}.</li>
     *   <li>{@code prefix[0] = 0} (по умолчанию в Java).</li>
     *   <li>Для каждого индекса {@code i} от 1 до {@code nums.length}:
     *       <pre>
     *       prefix[i] = prefix[i - 1] + nums[i - 1]
     *       </pre>
     *       То есть к сумме всех предыдущих элементов добавляется текущий элемент
     *       исходного массива (с индексом {@code i - 1}).</li>
     * </ol>
     * <p>
     * <b>Почему {@code i - 1}?</b> Потому что {@code prefix} имеет длину на 1 больше,
     * и {@code prefix[1]} соответствует сумме первого элемента {@code nums[0]}.
     * <p>
     * <b>Пример:</b>
     * <pre>
     * nums = [-2, 0, 3, -5, 2, -1]
     * prefix = [0, -2, -2, 1, -4, -2, -3]
     * </pre>
     *
     * @param nums исходный массив целых чисел (не может быть {@code null})
     */
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int index = 1; index <= nums.length; index++) {
            prefix[index] = prefix[index - 1] + nums[index - 1];
        }
    }


    /**
     * Возвращает сумму элементов массива {@code nums} с индекса {@code left}
     * по индекс {@code right} включительно.
     * <p>
     * <b>Формула:</b>
     * <pre>
     * sumRange(left, right) = prefix[right + 1] - prefix[left]
     * </pre>
     * <p>
     * <b>Пояснение:</b>
     * <ul>
     *   <li>{@code sumBeforeLeft = prefix[left]} — сумма всех элементов до {@code left}
     *       (не включая сам элемент с индексом {@code left}).</li>
     *   <li>{@code sumThroughRight = prefix[right + 1]} — сумма всех элементов
     *       от начала массива до {@code right} включительно.</li>
     *   <li>Их разность даёт сумму от {@code left} до {@code right} включительно.</li>
     * </ul>
     * <p>
     * <b>Пример:</b>
     * <pre>
     * nums = [-2, 0, 3, -5, 2, -1]
     * prefix = [0, -2, -2, 1, -4, -2, -3]
     * sumRange(0, 2) = prefix[3] - prefix[0] = 1 - 0 = 1
     * sumRange(2, 5) = prefix[6] - prefix[2] = -3 - (-2) = -1
     * </pre>
     * <p>
     * <b>Сложность:</b> O(1) по времени, O(1) по памяти.
     *
     * @param left  левая граница диапазона (включительно), 0 ≤ left ≤ right
     * @param right правая граница диапазона (включительно), left ≤ right < nums.length
     * @return сумма элементов от {@code left} до {@code right} включительно
     * @throws ArrayIndexOutOfBoundsException если {@code left} или {@code right}
     *                                        выходят за пределы допустимых значений
     */
    public int sumRange(int left, int right) {
        int sumBeforeLeft = prefix[left];
        int sumThroughRight = prefix[right + 1];
        return sumThroughRight - sumBeforeLeft;
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
}
