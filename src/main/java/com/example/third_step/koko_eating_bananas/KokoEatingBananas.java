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
 * <p>Идея:<br>
 * Прямой формулы для скорости нет. Но есть монотонность: если Коко успевает
 * при скорости k, то успеет и при любой скорости больше k. Значит, множество
 * подходящих скоростей — это отрезок [k_min, +∞). Ищем его левую границу
 * бинарным поиском по диапазону возможных скоростей.</p>
 *
 * <p>Именование переменных:<br>
 * В этой задаче minSpeed и maxSpeed — это не индексы, а границы диапазона
 * скоростей. minSpeed — минимальная возможная скорость, maxSpeed — максимальная.
 * currentSpeed — проверяемая скорость на текущей итерации (середина диапазона).
 * currentHours — суммарное время, которое Коко потратит при currentSpeed.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Определяем границы диапазона скоростей:
 *       <ul>
 *         <li>minSpeed = 1. Почему не 0: k — это скорость поедания, минимум
 *             1 банан в час. При k = 0 Коко не ест вообще, деление на ноль
 *             в формуле часов, ответ никогда не найдётся.</li>
 *         <li>maxSpeed = максимум в массиве piles. Быстрее самой большой кучи
 *             смысла нет: каждая куча съедается за один час. Находится одним
 *             проходом по массиву.</li>
 *       </ul>
 *   </li>
 *   <li>Пока minSpeed &lt; maxSpeed:
 *       <ul>
 *         <li>currentSpeed = minSpeed + (maxSpeed - minSpeed) / 2. Это
 *             проверяемая скорость. Не «выбираем», а вычисляем как середину
 *             диапазона.</li>
 *         <li>Считаем суммарное время: проходим по всем кучам, для каждой
 *             считаем ceil(pile / currentSpeed), складываем в currentHours.</li>
 *         <li>Если currentHours &lt;= h → скорость подходит, но может быть
 *             меньше. Сдвигаем maxSpeed = currentSpeed.</li>
 *         <li>Иначе → слишком медленно. Сдвигаем minSpeed = currentSpeed + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Когда minSpeed == maxSpeed — это минимальная подходящая скорость.
 *       Возвращаем minSpeed.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>ceil без double: (pile + currentSpeed - 1) / currentSpeed при
 *       целочисленном делении. Обычное pile / currentSpeed округляет вниз
 *       и даёт неверные часы.</li>
 *   <li>maxSpeed = currentSpeed, а не currentSpeed - 1. currentSpeed может
 *       быть минимальным ответом, исключать его нельзя.</li>
 *   <li>minSpeed = currentSpeed + 1, потому что currentSpeed точно не
 *       подходит — его можно выкинуть.</li>
 *   <li>Верхняя граница — максимум в массиве, а не сумма всех бананов.
 *       За один час Коко ест только из одной кучи.</li>
 *   <li>Сравнение с h: &lt;=, а не &lt;. Нужно успеть ровно за h или раньше.</li>
 *   <li>Внутренний цикл по кучам — обязательный. Для каждой проверяемой
 *       скорости нужно заново посчитать суммарные часы.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>h больше суммы бананов → ответ 1.</li>
 *   <li>h равно количеству куч → ответ = максимум куч.</li>
 *   <li>одна куча, h = 1 → ответ = эта куча.</li>
 *   <li>все кучи одинаковые.</li>
 *   <li>h ровно совпадает с количеством часов при каком-то k.</li>
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
