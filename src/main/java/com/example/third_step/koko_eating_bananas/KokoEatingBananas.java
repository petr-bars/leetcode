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
 * <p>Паттерн:<br>
 * Бинарный поиск по ответу.</p>
 *
 * <p>Идея:<br>
 * Прямой формулы для скорости нет, но есть монотонность: если Коко успевает
 * при скорости k, то успеет и при любой большей. Значит, подходящие скорости
 * образуют отрезок [k_min, +∞). Ищем левую границу бинарным поиском.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>minSpeed = 1, maxSpeed = максимум в piles.</li>
 *   <li>Пока minSpeed &lt; maxSpeed:
 *       <ul>
 *         <li>currentSpeed = minSpeed + (maxSpeed − minSpeed) / 2.</li>
 *         <li>currentHours = сумма по всем кучам от
 *             (pile + currentSpeed − 1) / currentSpeed.
 *             Это ceil(pile / currentSpeed) при целочисленном делении.</li>
 *         <li>если currentHours ≤ h → maxSpeed = currentSpeed;</li>
 *         <li>иначе → minSpeed = currentSpeed + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Вернуть minSpeed.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n log m), где m — самая большая куча.<br>
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
