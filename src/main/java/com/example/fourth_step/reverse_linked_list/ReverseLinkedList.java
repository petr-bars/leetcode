package com.example.fourth_step.reverse_linked_list;

import java.util.Objects;

/**
 * <p>Given the head of a singly linked list, reverse the list, and return
 * the reversed list.</p>
 *
 * <p>Дан головной узел односвязного списка. Развернуть список и вернуть
 * новую голову.</p>
 *
 * <p>Пример:<br>
 * Вход:  head = [1,2,3,4,5]<br>
 * Выход: [5,4,3,2,1]</p>
 *
 * <p>Паттерн:<br>
 * Три указателя + итеративный проход.</p>
 *
 * <p>Идея:<br>
 * Идём по списку и на каждом узле разворачиваем ссылку назад. Чтобы
 * не потерять хвост, сначала запоминаем следующий узел, потом разворачиваем,
 * потом сдвигаем оба указателя вперёд. prev в конце становится новой головой.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>prev = null, current = head.</li>
 *   <li>Пока current не null:
 *       <ul>
 *         <li>запомнить nextNode — следующий узел после current;</li>
 *         <li>развернуть ссылку current на prev;</li>
 *         <li>сдвинуть prev на current;</li>
 *         <li>сдвинуть current на nextNode.</li>
 *       </ul>
 *   </li>
 *   <li>Вернуть prev — новая голова.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        int[] head = new int[]{1, 2, 3, 4, 5};
        System.out.println(reverseList(createList(head)).val);
        System.out.println(Objects.requireNonNull(reverseListRecursive(createList(head))).val);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next; // запоминаем следующий
            current.next = prev;          // разворачиваем ссылку
            prev = current;               // двигаем prev
            current = nextTemp;               // двигаем current
        }
        return prev; // новая голова
    }


    public static ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next); //Возвращаем начальный узел новую голову
        head.next.next = head; //Меняем указатель следующего узла теперь он будет указывать обратно на текущий узел.
        head.next = null;// Разрываем прямую связь на старый узел, чтобы избежать зацикливания
        return newHead;
    }


    // Вспомогательный метод для создания списка из массива
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
}
