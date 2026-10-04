package com.example.second_step.trapping_rain_water;

/**
 * <p>Given n non-negative integers representing an elevation map where the
 * width of each bar is 1, compute how much water it can trap after raining.</p>
 *
 * <p>Дан массив height длины n, где height[i] — высота столбца рельефа
 * в точке i. Ширина каждого столбца равна 1. После дождя в ямах между
 * столбцами скапливается вода. Посчитать, сколько всего единиц воды
 * удержится в рельефе.</p>
 *
 * <p>Пример:<br>
 * Вход:  height = [0,1,0,2,1,0,1,3,2,1,2,1]<br>
 * Выход: 6<br>
 * Пояснение: сумма воды по всем столбцам = 1+1+2+1+1 = 6.</p>
 *
 * <p>Пример:<br>
 * Вход:  height = [4,2,0,3,2,5]<br>
 * Выход: 9</p>
 *
 * <p>Паттерн:<br>
 * Два указателя навстречу с накоплением максимумов. Тот же паттерн, что
 * в Container With Most Water, но логика другая: там искали максимум
 * между двумя линиями, здесь суммируем воду над всеми столбцами.</p>
 *
 * <p>Именование переменных:<br>
 * leftBarIndex и rightBarIndex — индексы указателей с двух концов.
 * leftMax — максимальная высота, встреченная слева от leftBarIndex
 * (включая сам столбец). rightMax — то же справа. water — аккумулятор
 * общего объёма воды.</p>
 *
 * <p>Идея:<br>
 * Вода над каждым столбцом i ограничена меньшей из двух стен вокруг него:
 * максимумом слева и максимумом справа. Наивно для каждой позиции искать
 * оба максимума отдельно — это O(n²). Два указателя позволяют не хранить
 * оба массива максимумов: идём с двух концов навстречу, на каждом шаге
 * обрабатываем более низкую сторону, для которой противоположная стена
 * заведомо выше и на уровень воды не влияет.</p>
 *
 * <p>Формула в твоём видении:</p>
 * <ul>
 *   <li>контейнер = (накопленный максимум с этой стороны, текущий
 *       столбик).</li>
 *   <li>вода в контейнере = максимум − высота столбика.</li>
 * </ul>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация:
 *       <ul>
 *         <li>leftBarIndex = 0, rightBarIndex = n - 1.</li>
 *         <li>leftMax = 0, rightMax = 0.</li>
 *         <li>water = 0.</li>
 *       </ul>
 *   </li>
 *   <li>Пока leftBarIndex &lt; rightBarIndex:
 *       <ul>
 *         <li>Сравниваем height[leftBarIndex] и height[rightBarIndex].
 *             <ul>
 *               <li>Если height[leftBarIndex] &lt; height[rightBarIndex] —
 *                   работаем с левой стороной:
 *                   <ul>
 *                     <li>leftMax = max(leftMax, height[leftBarIndex]).</li>
 *                     <li>water += leftMax - height[leftBarIndex].</li>
 *                     <li>leftBarIndex++.</li>
 *                   </ul>
 *                 </li>
 *               <li>Иначе — работаем с правой стороной:
 *                   <ul>
 *                     <li>rightMax = max(rightMax, height[rightBarIndex]).</li>
 *                     <li>water += rightMax - height[rightBarIndex].</li>
 *                     <li>rightBarIndex--.</li>
 *                   </ul>
 *                 </li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем water.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Вода над столбцом = максимум_стороны − height[столбик]. Это не
 *       отдельный массив, а одна операция на каждом шаге. Всё копится
 *       в переменную water.</li>
 *   <li>Работаем с более низкой стороной, потому что противоположная
 *       заведомо выше и не ограничивает воду. Если height[leftBarIndex] &lt;
 *       height[rightBarIndex], то справа уже стоит стена выше левого
 *       столбца. Значит, для позиции leftBarIndex уровень воды
 *       определяется только leftMax.</li>
 *   <li>leftMax и rightMax обновляются ДО подсчёта воды. Если текущий
 *       столбец сам выше максимума — он становится новым максимумом,
 *       и вода над ним = 0. Если ниже — максимум не меняется, вода =
 *       разница.</li>
 *   <li>Сравнение height[leftBarIndex] &lt; height[rightBarIndex], а не
 *       &lt;=. Если равны, работает правая ветка. Это не влияет
 *       на результат, потому что при равных высотах максимумы одинаковые
 *       и вода посчитается корректно с любой стороны.</li>
 *   <li>Каждый столбец обрабатывается ровно один раз — либо как левый,
 *       либо как правый. leftBarIndex и rightBarIndex двигаются только
 *       навстречу, суммарно проходят n шагов. Отсюда O(n).</li>
 *   <li>leftMax не уменьшается и rightMax не уменьшается. Они
 *       накапливаются по ходу движения. Это и есть виртуальные стены,
 *       которые ограничивают воду.</li>
 *   <li>Не путать с Container With Most Water: там искали максимальную
 *       площадь между парой линий, здесь суммируем воду над всеми
 *       столбцами. Там формула min(h[l], h[r]) × (r-l), здесь
 *       min(maxLeft, maxRight) − h[i].</li>
 *   <li>Пустой массив или массив из одного элемента — цикл не выполнится,
 *       вернётся 0.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>height = [0,1,0,2,1,0,1,3,2,1,2,1] → 6.</li>
 *   <li>height = [4,2,0,3,2,5] → 9.</li>
 *   <li>height = [] → 0.</li>
 *   <li>height = [1] → 0.</li>
 *   <li>height = [3,2,1] → 0. Вода стекает вправо, ям нет.</li>
 *   <li>height = [1,2,3] → 0. Только подъём, ям нет.</li>
 *   <li>height = [3,0,3] → 3. Одна яма глубиной 3.</li>
 *   <li>height = [5,5,5,5] → 0. Плоская поверхность.</li>
 *   <li>height = [2,0,2] → 2.</li>
 *   <li>height = [2,0,0,2] → 4. Широкая яма.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход, leftBarIndex и rightBarIndex двигаются
 *       только навстречу, каждый суммарно не более n раз.<br>
 * Память: O(1) — только указатели, два максимума и аккумулятор воды.</p>
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int leftBarIndex = 0;
        int rightBarIndex = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (leftBarIndex < rightBarIndex) {
            if (height[leftBarIndex] < height[rightBarIndex]) {
                leftMax = Math.max(leftMax, height[leftBarIndex]);
                water += leftMax - height[leftBarIndex];
                leftBarIndex++;
            } else {
                rightMax = Math.max(rightMax, height[rightBarIndex]);
                water += rightMax - height[rightBarIndex];
                rightBarIndex--;
            }
        }
        return water;
    }
}
