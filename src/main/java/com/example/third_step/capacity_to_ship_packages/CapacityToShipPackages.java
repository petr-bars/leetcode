package com.example.third_step.capacity_to_ship_packages;

/**
 * <p>A conveyor belt has packages that must be shipped from one port to
 * another within days days. The ith package has weight weights[i]. Each day
 * we load the ship with packages in the order given by weights. We may not
 * load more weight than the maximum capacity of the ship.</p>
 *
 * <p>Return the least weight capacity that will ship all packages within
 * days days.</p>
 *
 * <p>На конвейере пакеты. За days дней нужно перевезти все. Каждый день
 * грузим пакеты по порядку, не превышая грузоподъёмность. Найти минимальную
 * грузоподъёмность, при которой всё уедет за days дней или меньше.</p>
 *
 * <p>Пример:<br>
 * Вход:  weights = [1,2,3,4,5,6,7,8,9,10], days = 5<br>
 * Выход: 15</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по ответу.</p>
 *
 * <p>Идея:<br>
 * Прямой формулы нет, но есть монотонность: если корабль справляется
 * с грузоподъёмностью C, то справится и с любой большей. Подходящие
 * значения — отрезок [C_min, +∞). Ищем левую границу бинарным поиском.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>minCapacity = максимум в weights — меньше нельзя, самый тяжёлый
 *       пакет должен влезать.</li>
 *   <li>maxCapacity = сумма всех weights — хватит, чтобы увезти всё за день.</li>
 *   <li>Пока minCapacity &lt; maxCapacity:
 *       <ul>
 *         <li>currentCapacity = minCapacity + (maxCapacity − minCapacity) / 2.</li>
 *         <li>Считаем totalDays: идём по weights, копим currentLoad. Если
 *             currentLoad + weight ≤ currentCapacity → добавляем в текущий
 *             день. Иначе → totalDays++, currentLoad = weight.</li>
 *         <li>totalDays ≤ days → maxCapacity = currentCapacity;
 *             иначе → minCapacity = currentCapacity + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Вернуть minCapacity.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n log S), где S — сумма весов.<br>
 * Память: O(1).</p>
 */
public class CapacityToShipPackages {
    public static void main(String[] args) {
        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println(shipWithinDays(weights, days));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int minCapacity = 0;
        int maxCapacity = 0;
        for (int weight : weights) {
            minCapacity = Math.max(minCapacity, weight);
            maxCapacity += weight;
        }

        while (minCapacity < maxCapacity) {
            int currentCapacity = minCapacity + (maxCapacity - minCapacity) / 2;
            int totalDays = 1;
            int currentLoad = 0;
            for (int weight : weights) {
                if (currentLoad + weight <= currentCapacity) {
                    currentLoad += weight;
                } else {
                    totalDays++;
                    currentLoad = weight;
                }
            }

            if (totalDays <= days) {// Почему не currentCapacity -1, потому что он может быть искомым.
                maxCapacity = currentCapacity;
            } else {
                minCapacity = currentCapacity + 1;
            }
        }

        return minCapacity;
    }
}
