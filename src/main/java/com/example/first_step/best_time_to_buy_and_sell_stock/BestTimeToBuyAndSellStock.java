package com.example.first_step.best_time_to_buy_and_sell_stock;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a
 * different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 * <p>
 * Дан массив prices, где prices[i] — цена акции в день i.
 * Нужно найти максимальную прибыль от одной сделки: купить в один день,
 * продать в другой (продажа строго после покупки).
 * Если прибыль невозможна — вернуть 0.
 * Пример:
 * Вход: prices = [7, 1, 5, 3, 6, 4]
 * Выход: 5
 * Покупка в день 1 (цена 1), продажа в день 4 (цена 6). Прибыль = 6 − 1 = 5.
 * Паттерн
 * Один проход + отслеживание минимума.
 * Ключевая идея
 * Идём по массиву слева направо, поддерживая:
 * minPrice — минимальная цена, встреченная до текущего дня.
 * maxProfit — максимальная прибыль, найденная к текущему моменту.
 * На каждом дне:
 * Обновляем maxProfit = max(maxProfit, prices[i] - minPrice).
 * Обновляем minPrice = min(minPrice, prices[i]).
 * Формула
 * profit = prices[i] - minPrice
 * maxProfit = max(maxProfit, profit)
 * minPrice = min(minPrice, prices[i])
 * Сложность
 * Время: O(n)
 * Память: O(1)
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
//        int[] prices = new int[]{7, 6, 4, 3, 1};

        System.out.println(maxProfit(prices));
    }


    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
}
