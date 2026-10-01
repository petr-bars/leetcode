package com.example.fourth_step.reorder_list;

/**
 * <p>You are given the head of a singly linked-list. The list can be represented
 * as: L0 → L1 → … → Ln-1 → Ln.</p>
 *
 * <p>Reorder the list to be on the following form:<br>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …</p>
 *
 * <p>You may not modify the values in the list's nodes. Only nodes themselves
 * may be changed.</p>
 *
 * <p>Дан односвязный список. Переставить узлы так: первый, последний, второй,
 * предпоследний, третий, третий с конца и так далее. Значения узлов менять
 * нельзя — можно менять только ссылки между узлами.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2,3,4]<br>
 * Выход: [1,4,2,3]</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2,3,4,5]<br>
 * Выход: [1,5,2,4,3]</p>
 *
 * <p>Паттерн:<br>
 * Комбинация трёх приёмов: fast &amp; slow pointers, реверс списка, слияние
 * двух списков.</p>
 *
 * <p>Именование переменных:<br>
 * slow и fast — указатели для поиска середины. slow двигается на 1 шаг,
 * fast — на 2. Когда fast дойдёт до конца, slow будет на середине.
 * prev, current, nextNode — переменные для реверса второй половины.
 * first — указатель на начало первой половины. second — указатель на начало
 * перевёрнутой второй половины.</p>
 *
 * <p>Идея:<br>
 * Задача разбивается на три независимые подзадачи, каждая из которых решается
 * известным приёмом. Сначала находим середину списка. Потом разворачиваем
 * вторую половину — теперь она идёт в обратном порядке, и её элементы
 * выстроены так, как нам нужно для вставки. Затем сливаем две половины
 * поочерёдно: берём узел из первой, потом из второй, потом снова из первой,
 * и так до конца.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Ранний выход:
 *       <ul>
 *         <li>Если head == null или head.next == null — список пустой
 *             или из одного узла. Переставлять нечего, выходим сразу.</li>
 *       </ul>
 *   </li>
 *   <li>Шаг 1. Найти середину списка через fast &amp; slow:
 *       <ul>
 *         <li>slow и fast начинают с head.</li>
 *         <li>Пока fast и fast.next не равны null:
 *             <ul>
 *               <li>slow двигается на 1 шаг.</li>
 *               <li>fast двигается на 2 шага.</li>
 *             </ul>
 *         </li>
 *         <li>Когда цикл закончился, slow стоит на середине. Для чётной длины
 *             slow окажется на первом из двух средних элементов. Для нечётной —
 *             ровно на среднем.</li>
 *       </ul>
 *   </li>
 *   <li>Шаг 2. Разделить список и развернуть вторую половину:
 *       <ul>
 *         <li>prev = null, current = slow.next. Запомнили начало второй
 *             половины, prev пока пустой — он станет хвостом развёрнутого
 *             списка.</li>
 *         <li>slow.next = null. Разорвали связь между половинами. Теперь
 *             первая половина — отдельный список от head до slow, вторая —
 *             от current до конца.</li>
 *         <li>Пока current не null:
 *             <ul>
 *               <li>nextNode = current.next — запомнить следующий узел,
 *                   пока не потеряли.</li>
 *               <li>current.next = prev — развернуть ссылку назад.</li>
 *               <li>prev = current — сдвинуть prev вперёд.</li>
 *               <li>current = nextNode — сдвинуть current вперёд.</li>
 *             </ul>
 *         </li>
 *         <li>После цикла prev — начало развёрнутой второй половины.</li>
 *       </ul>
 *   </li>
 *   <li>Шаг 3. Слить две половины поочерёдно:
 *       <ul>
 *         <li>first = head — начало первой половины.</li>
 *         <li>second = prev — начало развёрнутой второй половины.</li>
 *         <li>Пока second не null:
 *             <ul>
 *               <li>nextFirst = first.next — сохранить следующий узел первой
 *                   половины.</li>
 *               <li>nextSecond = second.next — сохранить следующий узел второй
 *                   половины.</li>
 *               <li>first.next = second — вставить узел из второй половины
 *                   после текущего узла первой.</li>
 *               <li>second.next = nextFirst — связать вставленный узел
 *                   со следующим узлом первой половины.</li>
 *               <li>first = nextFirst — сдвинуть first на следующий узел
 *                   первой половины.</li>
 *               <li>second = nextSecond — сдвинуть second на следующий узел
 *                   второй половины.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>slow останавливается на середине. Для чётной длины (например,
 *       1→2→3→4) slow окажется на 2, а вторая половина начнётся с 3.
 *       Значит, первая половина длиннее или равна второй. Это удобно:
 *       в шаге слияния вторая половина закончится раньше или одновременно,
 *       и цикл по second корректно завершится.</li>
 *   <li>Обязательно разорвать связь slow.next = null перед реверсом.
 *       Иначе первая половина будет тянуться во вторую, и при реверсе
 *       получится цикл.</li>
 *   <li>При реверсе обязательно сохранять nextNode до перезаписи
 *       current.next. Иначе потеряешь остаток списка.</li>
 *   <li>При слиянии сохранять nextFirst и nextSecond до перезаписи ссылок.
 *       Иначе потеряешь указатели на следующие узлы.</li>
 *   <li>Порядок слияния: сначала first.next = second, потом
 *       second.next = nextFirst. Если сделать наоборот, потеряешь связь
 *       со второй половиной.</li>
 *   <li>Цикл слияния идёт по second, а не по first. Потому что первая
 *       половина длиннее или равна, а вторая заканчивается первой. Если
 *       идти по first, можно попытаться вставить null после последнего
 *       узла — упадёт.</li>
 *   <li>Значения узлов не меняются. Меняются только ссылки next. Это
 *       требование задачи.</li>
 *   <li>Список из одного узла (head.next == null) — ранний выход, ничего
 *       делать не нужно.</li>
 *   <li>Список из двух узлов (1→2) — slow на 1, вторая половина [2],
 *       реверс не меняет её, слияние даёт 1→2. Ответ совпадает с исходным,
 *       но код всё равно проходит корректно.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>список из одного узла → без изменений, ранний выход.</li>
 *   <li>список из двух узлов → без изменений.</li>
 *   <li>список из трёх узлов (1→2→3) → 1→3→2.</li>
 *   <li>список из четырёх узлов (1→2→3→4) → 1→4→2→3.</li>
 *   <li>список из пяти узлов (1→2→3→4→5) → 1→5→2→4→3.</li>
 *   <li>список из шести узлов (1→2→3→4→5→6) → 1→6→2→5→3→4.</li>
 *   <li>чётная длина с разными значениями узлов.</li>
 *   <li>нечётная длина с разными значениями узлов.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — три прохода по списку: поиск середины, реверс второй
 * половины, слияние.<br>
 * Память: O(1) — только указатели, никаких дополнительных структур.</p>
 */
public class ReorderList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        reorderList(head);
        System.out.println(listToString(head));
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        //Ищем середину
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //Разворачиваем вторую половину
        ListNode prev = null;
        ListNode current = slow.next;
        //Разрываем связь
        slow.next = null;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        // голова развёрнутой второй части
        ListNode second = prev;

        //Чередуем элементы друг за другом из двух половин
        ListNode first = head;
        while (second != null) {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }

    }

    public static ListNode createList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(", ");
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
