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
 * Комбинация трёх приёмов: fast &amp; slow, реверс списка, слияние двух
 * списков.</p>
 *
 * <p>Идея:<br>
 * Задача разбивается на три подзадачи. Сначала находим середину списка
 * через fast и slow. Потом разворачиваем вторую половину — теперь она идёт
 * в обратном порядке. Затем сливаем две половины поочерёдно: узел из первой,
 * узел из второй, снова из первой, и так до конца.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>fast &amp; slow до конца: slow на середине.</li>
 *   <li>Развернуть вторую половину от slow.next по классическому реверсу
 *       (prev, current, nextNode).</li>
 *   <li>Слить поочерёдно: first.next = second, second.next = nextFirst,
 *       сдвинуть оба.</li>
 *   <li>Цикл слияния идёт по second — вторая половина короче или равна.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — три прохода.<br>
 * Память: O(1).</p>
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

        ListNode slow = head;
        ListNode fast = head;
        //Ищем середину
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode current = slow.next;
        slow.next = null;

        // голова развёрнутой второй части
        ListNode second = null;

        //Разворачиваем вторую половину
        while (current != null) {
            ListNode temp = current.next;
            current.next = second;
            second = current;
            current = temp;
        }

        ListNode fistNext;
        ListNode secondNext;
        ListNode first = head;
        //Чередуем элементы друг за другом из двух половин
        while (second != null) {
            fistNext = first.next;
            secondNext = second.next;
            first.next = second;
            second.next = fistNext;
            first = fistNext;
            second = secondNext;
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
