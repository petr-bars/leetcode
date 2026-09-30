package com.example.third_step.capacity_to_ship_packages;

/**
 * <p>A conveyor belt has packages that must be shipped from one port to another
 * within days days. The ith package on the conveyor belt has a weight of
 * weights[i]. Each day, we load the ship with packages on the conveyor belt
 * (in the order given by weights). We may not load more weight than the maximum
 * weight capacity of the ship.</p>
 *
 * <p>Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within days days.</p>
 *
 * <p>На конвейере пакеты, которые нужно отправить из одного порта в другой
 * за days дней. Вес i-го пакета — weights[i]. Каждый день корабль загружается
 * пакетами с конвейера (в порядке массива). Нельзя загрузить больше, чем
 * максимальная грузоподъёмность корабля. Найти минимальную грузоподъёмность,
 * при которой все пакеты уедут за days дней или меньше.</p>
 *
 * <p>Пример:<br>
 * Вход:  weights = [1,2,3,4,5,6,7,8,9,10], days = 5<br>
 * Выход: 15</p>
 *
 * <p>Пример:<br>
 * Вход:  weights = [3,2,2,4,1,4], days = 3<br>
 * Выход: 6</p>
 *
 * <p>Пример:<br>
 * Вход:  weights = [1,2,3,1,1], days = 4<br>
 * Выход: 3</p>
 *
 * <p>Паттерн:<br>
 * Бинарный поиск по ответу.</p>
 *
 * <p>Идея:<br>
 * Прямой формулы для грузоподъёмности нет. Но есть монотонность: если корабль
 * справляется с грузоподъёмностью capacity, то справится и с любой большей.
 * Значит, множество подходящих значений — отрезок [c_min, +∞). Ищем его левую
 * границу бинарным поиском по диапазону возможных грузоподъёмностей.</p>
 *
 * <p>Именование переменных:<br>
 * minCapacity и maxCapacity — это не индексы, а границы диапазона
 * грузоподъёмностей. minCapacity — минимально возможная, maxCapacity —
 * максимально возможная. currentCapacity — проверяемая грузоподъёмность
 * на текущей итерации (середина диапазона). totalDays — сколько дней уйдёт
 * при currentCapacity. currentLoad — вес, накопленный за текущий день.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Определяем границы диапазона грузоподъёмностей:
 *       <ul>
 *         <li>minCapacity = максимум в массиве weights. Почему не 1 и не 0:
 *             если грузоподъёмность меньше самого тяжёлого пакета, его
 *             невозможно загрузить в принципе. Значит, минимум — вес самого
 *             тяжёлого пакета.</li>
 *         <li>maxCapacity = сумма всех weights. Почему: за один день можно
 *             увезти всё, если грузоподъёмность равна общей сумме. Больше
 *             смысла нет — задача решится за 1 день.</li>
 *       </ul>
 *   </li>
 *   <li>Пока minCapacity &lt; maxCapacity:
 *       <ul>
 *         <li>currentCapacity = minCapacity + (maxCapacity - minCapacity) / 2.
 *             Это проверяемая грузоподъёмность. Не «выбираем», а вычисляем
 *             как середину диапазона.</li>
 *         <li>Считаем, сколько дней нужно при currentCapacity:
 *             <ul>
 *               <li>totalDays начинаем с 1 — первый день уже открыт.</li>
 *               <li>currentLoad = 0 — в первый день пока ничего не загружено.</li>
 *               <li>Идём по массиву weights по порядку.</li>
 *               <li>Проверяем: currentLoad + weight &lt;= currentCapacity?
 *                   <ul>
 *                     <li>Да → пакет влезает в текущий день. Просто добавляем:
 *                         currentLoad += weight.</li>
 *                     <li>Нет → пакет не влезает. Закрываем текущий день:
 *                         totalDays++. Начинаем новый день с этого пакета:
 *                         currentLoad = weight.</li>
 *                   </ul>
 *               </li>
 *               <li>После цикла ничего делать не нужно — последний день уже
 *                   посчитан, он был открыт с самого начала.</li>
 *             </ul>
 *         </li>
 *         <li>Если totalDays &lt;= days → грузоподъёмность подходит, но
 *             может быть меньше. Сдвигаем maxCapacity = currentCapacity.</li>
 *         <li>Иначе → слишком мало, нужно больше. Сдвигаем
 *             minCapacity = currentCapacity + 1.</li>
 *       </ul>
 *   </li>
 *   <li>Когда minCapacity == maxCapacity — это минимальная подходящая
 *       грузоподъёмность. Возвращаем minCapacity.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Порядок пакетов фиксирован. Нельзя переставлять или сортировать.
 *       Поэтому подсчёт дней — это жадный проход по массиву, а не
 *       упаковка в контейнеры в произвольном порядке.</li>
 *   <li>minCapacity = максимум, а не 1. Если поставить 1, то для любого
 *       пакета тяжелее 1 корабль не загрузится вообще, и подсчёт дней даст
 *       бесконечность. Минимум — самый тяжёлый пакет.</li>
 *   <li>maxCapacity = сумма, а не максимум. С суммой всё уедет за один день.
 *       Если поставить максимум — может не хватить, поиск не найдёт ответ.</li>
 *   <li>maxCapacity = currentCapacity, а не currentCapacity - 1.
 *       currentCapacity может быть минимальным ответом, исключать его нельзя.</li>
 *   <li>minCapacity = currentCapacity + 1, потому что currentCapacity точно
 *       не подходит — его можно выкинуть.</li>
 *   <li>totalDays начинаем с 1, а не с 0. Почему: первый день открывается
 *       сразу, до цикла. Внутри цикла totalDays увеличивается только при
 *       закрытии дня — когда очередной пакет не влезает. В конце последний
 *       день уже посчитан, дополнительный totalDays++ не нужен. Если начать
 *       с 0, придётся делать totalDays++ после цикла — легко забыть, и
 *       получишь ответ на 1 меньше.</li>
 *   <li>Условие проверки — currentLoad + weight &lt;= currentCapacity, а не
 *       currentLoad &lt;= currentCapacity. Первое говорит «новый пакет влезет
 *       вместе с текущей загрузкой». Второе — «текущая загрузка валидна»
 *       (что и так всегда верно, поэтому else никогда не сработает).</li>
 *   <li>Сравнение с days: &lt;=, а не &lt;. Нужно успеть ровно за days или
 *       раньше.</li>
 *   <li>Внутренний цикл по пакетам — обязательный. Для каждой проверяемой
 *       грузоподъёмности нужно заново посчитать дни.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>days = 1 → ответ = сумма всех весов.</li>
 *   <li>days = weights.length → ответ = максимум весов.</li>
 *   <li>один пакет → ответ = вес этого пакета.</li>
 *   <li>все пакеты одинаковые.</li>
 *   <li>самый тяжёлый пакет в середине — проверка, что greedy-проход
 *       правильно начинает новый день.</li>
 *   <li>пакет точно равен currentCapacity — должен уместиться в тот же день.</li>
 *   <li>сумма пакетов за день ровно равна currentCapacity.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n log S), где n — число пакетов, S — сумма всех весов.
 *       log S итераций бинарного поиска, на каждой — проход по всем пакетам
 *       для подсчёта дней.<br>
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

            if (totalDays <= days) {
                maxCapacity = currentCapacity;
            } else {
                minCapacity = currentCapacity + 1;
            }
        }

        return minCapacity;
    }
}
