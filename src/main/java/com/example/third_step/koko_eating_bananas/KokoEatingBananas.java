package com.example.third_step.koko_eating_bananas;

/**
 * <p>Koko loves to eat bananas. There are n piles of bananas, the ith pile has
 * piles[i] bananas. The guards have gone and will come back in h hours.</p>
 *
 * <p>Koko can decide her bananas-per-hour eating speed of k. Each hour, she
 * chooses some pile of bananas and eats k bananas from that pile. If the pile
 * has less than k bananas, she eats all of them instead and will not eat any
 * more bananas during this hour.</p>
 *
 * <p>Return the minimum integer k such that she can eat all the bananas within
 * h hours.</p>
 *
 * <p>Коко любит бананы. Есть n куч, в i-й куче piles[i] бананов. Охрана вернётся
 * через h часов. Коко выбирает скорость k бананов в час. Каждый час она ест
 * из одной кучи. Если в куче меньше k — съедает всё и в этот час больше не ест.
 * Найти минимальное целое k, при котором она съест всё за h часов или меньше.</p>
 *
 * <p>Пример:<br>
 * Вход:  piles = [3,6,7,11], h = 8<br>
 * Выход: 4</p>
 *
 * <p>Пример:<br>
 * Вход:  piles = [30,11,23,4,20], h = 5<br>
 * Выход: 30</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по ответу.</p>
 *
 * <p>Именование переменных:<br>
 * minSpeed и maxSpeed — границы диапазона скоростей (не индексы).
 * currentSpeed — проверяемая скорость, середина диапазона. currentHours —
 * суммарное время при currentSpeed. pile — текущая куча в цикле подсчёта.
 * h — лимит часов. piles — входной массив куч.</p>
 *
 * <p>Идея:<br>
 * Прямой формулы для скорости нет. Но есть монотонность: если Коко успевает
 * при скорости k, то успеет и при любой скорости больше k. Значит, множество
 * подходящих скоростей — отрезок [k_min, +∞). Ищем его левую границу бинарным
 * поиском по диапазону возможных скоростей.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Определяем границы диапазона скоростей:
 *       <ul>
 *         <li>minSpeed = 1. Скорость не может быть 0 — при нуле Коко не ест,
 *             и деление на ноль в формуле часов.</li>
 *         <li>maxSpeed = максимум в массиве piles. Быстрее самой большой кучи
 *             смысла нет: каждая куча съедается за один час. Находится одним
 *             проходом.</li>
 *       </ul>
 *   </li>
 *   <li>Пока minSpeed &lt; maxSpeed:
 *       <ul>
 *         <li>currentSpeed = minSpeed + (maxSpeed - minSpeed) / 2.</li>
 *         <li>Считаем суммарное время currentHours:
 *             <ul>
 *               <li>Проходим по всем кучам.</li>
 *               <li>Для каждой кучи часы = (pile + currentSpeed - 1) / currentSpeed.
 *                   Это ceil(pile / currentSpeed) при целочисленном делении.
 *                   Почему так: если pile не делится на currentSpeed нацело,
 *                   нужно округлить вверх — Коко тратит на остаток ещё один
 *                   полный час. Формула (pile + currentSpeed - 1) / currentSpeed
 *                   делает это без вещественных чисел. Пример: pile = 7,
 *                   currentSpeed = 4 → (7 + 3) / 4 = 10 / 4 = 2. Проверка:
 *                   ceil(7 / 4) = 2.</li>
 *               <li>Складываем часы по всем кучам.</li>
 *             </ul>
 *         </li>
 *         <li>Если currentHours &lt;= h — скорость подходит, но может быть
 *             меньше. Сдвигаем maxSpeed = currentSpeed.</li>
 *         <li>Иначе — слишком медленно. Сдвигаем minSpeed = currentSpeed + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Когда minSpeed == maxSpeed — это минимальная подходящая скорость.
 *       Возвращаем minSpeed.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>maxSpeed = currentSpeed, а не currentSpeed - 1. currentSpeed может
 *       быть минимальным ответом, исключать его нельзя.</li>
 *   <li>minSpeed = currentSpeed + 1, потому что currentSpeed точно не
 *       подходит — его можно выкинуть.</li>
 *   <li>Сравнение с h: &lt;=, а не &lt;. Нужно успеть ровно за h или раньше.</li>
 *   <li>Внутренний цикл по кучам — обязательный. Для каждой проверяемой
 *       скорости нужно заново посчитать суммарные часы.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>piles = [3,6,7,11], h = 8 → 4.</li>
 *   <li>piles = [30,11,23,4,20], h = 5 → 30.</li>
 *   <li>piles = [30,11,23,4,20], h = 6 → 23.</li>
 *   <li>piles = [1], h = 1 → 1. Одна куча.</li>
 *   <li>piles = [10], h = 1 → 10. Одна куча, один час.</li>
 *   <li>piles = [10], h = 10 → 1. Можно есть по 1 банану в час.</li>
 *   <li>piles = [5,5,5], h = 3 → 5. Каждую кучу за час.</li>
 *   <li>piles = [1000000000], h = 2 → 500000000. Проверка
 *       производительности на больших числах.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n log m), где n — число куч, m — самая большая куча.<br>
 * Память: O(1).</p>
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = new int[]{3, 6, 7, 11};
        int h = 8;

        System.out.println(minEatingSpeed(piles, h));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = 0;
        for (int pile : piles) {
            maxSpeed = Math.max(maxSpeed, pile);
        }

        while (minSpeed < maxSpeed) {
            int currentSpeed = minSpeed + (maxSpeed - minSpeed) / 2;

            int currentHours = 0;
            for (int pile : piles) {
                currentHours += (pile + currentSpeed - 1) / currentSpeed;
            }

            if (currentHours <= h) {
                maxSpeed = currentSpeed;
            } else {
                minSpeed = currentSpeed + 1;
            }
        }
        return minSpeed;
    }
}
