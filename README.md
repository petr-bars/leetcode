# 📘 Освоение алгоритмов на Java

Решение задач с LeetCode для глубокого понимания структур данных и алгоритмов.  
**106 задач · 15 паттернов · 1-й круг завершён.**

---

## 🧭 Мой подход

Я прохожу все задачи **последовательно, по этапам**.  
На каждом этапе разбираю новые темы и структуры данных.

После завершения всего списка я **начинаю новый круг** — решаю все задачи заново, постепенно уменьшая количество подсказок.  
Так я повторяю материал, пока паттерны не становятся естественными.

### Прогресс

| Круг | Статус | Период |
|------|--------|--------|
| 1-й круг (разбор) | ✅ Завершён | 4 августа — 18 сентября |
| 2-й круг (закрепление) | ⏳ В планах | — |

---

## 🎯 Почему именно эти задачи?

Этот список — не случайный набор. Он составлен на основе реальной статистики собеседований и проверен на практике.

### Основано на данных

- **20 паттернов покрывают 94% всех задач** на собеседованиях.
- **10 паттернов покрывают 80% задач**.
- Большинство успешных кандидатов решают **75–150 качественных задач**, а не 500 случайных.
- Некоторые авторы утверждают, что **50 задач могут покрыть 90% интервью**, но 106 дают полную картину.

### Полный список паттернов (15)

1. Хеш-таблицы
2. Два указателя
3. Бинарный поиск
4. Сортировка
5. Связные списки
6. Скользящее окно (Sliding Window)
7. Стеки и очереди
8. Деревья
9. Куча (PriorityQueue)
10. Жадные алгоритмы (Greedy)
11. Графы (BFS/DFS, Union-Find, Dijkstra)
12. Backtracking
13. Динамическое программирование
14. Префиксные суммы
15. Битовые операции

### Самые частые паттерны на собеседованиях

| Паттерн | Частота появления |
|---------|-------------------|
| Два указателя | 23% |
| Динамическое программирование | 18% |
| BFS/DFS (графы и деревья) | 16% |
| Скользящее окно | 14% |
| Бинарный поиск | 12% |
| Хеш-таблицы | основа большинства задач |

### Проверенный путь

1. Освоить один паттерн через 8–12 задач.
2. Научиться узнавать этот паттерн в разных условиях.
3. Комбинировать несколько паттернов в одной задаче.

---

## 📚 Полный список задач

### ✅ Этап 1. Массивы + Хеш-таблицы (12 задач)

1. Two Sum – Easy – HashMap
2. Contains Duplicate – Easy – HashSet
3. Valid Anagram – Easy – HashMap / int[26]
4. Group Anagrams – Medium – HashMap + сортировка
5. Longest Consecutive Sequence – Medium – HashSet
6. Top K Frequent Elements – Medium – HashMap + bucket
7. Product of Array Except Self – Medium – Prefix / suffix
8. Valid Sudoku – Medium – HashSet для строк/колонок/блоков
9. Best Time to Buy and Sell Stock – Easy – Отслеживание минимума
10. Maximum Subarray – Medium – Kadane's algorithm
11. Merge Intervals – Medium – Сортировка + слияние
12. Insert Interval – Medium – Вставка + слияние

---

### ✅ Этап 2. Два указателя + Sliding Window (12 задач)

13. Valid Palindrome – Easy – Два указателя
14. Two Sum II – Medium – Два указателя
15. 3Sum – Medium – Два указателя + дубликаты
16. Container With Most Water – Medium – Жадные два указателя
17. Trapping Rain Water – Hard – Два указателя / стек
18. Move Zeroes – Easy – Read/Write pointers
19. Remove Duplicates from Sorted Array – Easy – In-place два указателя
20. Longest Substring Without Repeating – Medium – Sliding window
21. Longest Repeating Character Replacement – Medium – Sliding window + freq
22. Minimum Size Subarray Sum – Medium – Variable window
23. Find All Anagrams in a String – Medium – Fixed window + HashMap
24. Max Consecutive Ones III – Medium – Variable window с K заменами

---

### ✅ Этап 3. Бинарный поиск (8 задач)

25. Binary Search – Easy – Классика
26. Search a 2D Matrix – Medium – Бинарный поиск в матрице
27. Search in Rotated Sorted Array – Medium – Поиск в сдвинутом массиве
28. Search in Rotated Sorted Array II – Medium – С дубликатами
29. Find Minimum in Rotated Sorted Array – Medium – Поиск минимума
30. Koko Eating Bananas – Medium – Поиск по ответу
31. Capacity To Ship Packages – Medium – Поиск по ответу
32. Median of Two Sorted Arrays – Hard – Бинарный поиск на двух массивах

---

### ✅ Этап 4. Связные списки (8 задач)

33. Reverse Linked List – Easy – Итеративный + рекурсивный реверс
34. Merge Two Sorted Lists – Easy – Слияние
35. Linked List Cycle – Easy – Fast & slow pointers
36. Linked List Cycle II – Medium – Найти начало цикла
37. Remove Nth Node From End – Medium – Два указателя с отступом
38. Reorder List – Medium – Середина + реверс + слияние
39. Merge k Sorted Lists – Hard – PriorityQueue
40. Reverse Nodes in k‑Group – Hard – Реверс группами

---

### ✅ Этап 5. Стеки и очереди (8 задач)

41. Valid Parentheses – Easy – Stack
42. Min Stack – Medium – Stack с минимумом
43. Evaluate Reverse Polish Notation – Medium – Стек для вычислений
44. Generate Parentheses – Medium – Backtracking + стек
45. Daily Temperatures – Medium – Монотонный стек
46. Largest Rectangle in Histogram – Hard – Монотонный стек
47. Sliding Window Maximum – Hard – Deque (монотонная очередь)
48. Decode String – Medium – Стек для вложенных строк

---

### ✅ Этап 6. Деревья (12 задач)

49. Maximum Depth of Binary Tree – Easy – DFS / рекурсия
50. Same Tree – Easy – Сравнение деревьев
51. Invert Binary Tree – Easy – Рекурсия
52. Binary Tree Level Order Traversal – Medium – BFS (очередь)
53. Validate Binary Search Tree – Medium – Inorder или min/max
54. Kth Smallest in BST – Medium – Inorder traversal
55. Construct Binary Tree from Preorder/Inorder – Medium – Рекурсивное построение
56. Binary Tree Maximum Path Sum – Hard – DFS + максимум пути
57. Serialize and Deserialize Binary Tree – Hard – BFS/DFS + сериализация
58. Lowest Common Ancestor of Deepest Leaves – Medium – LCA (рекурсия с глубиной)
59. Subtree of Another Tree – Easy – Проверка поддерева
60. Diameter of Binary Tree – Easy – DFS + диаметр

---

### ✅ Этап 7. Куча (Heap) и Greedy (7 задач)

61. Kth Largest Element in an Array – Medium – Min-Heap / QuickSelect
62. Kth Largest Element in a Stream – Easy – Min-Heap фиксированного размера
63. Last Stone Weight – Easy – Max-Heap (PriorityQueue с reverse)
64. K Closest Points to Origin – Medium – Min-Heap / Max-Heap
65. Jump Game – Medium – Greedy
66. Jump Game II – Medium – Greedy + BFS
67. Task Scheduler – Medium – Greedy + Heap

---

### ✅ Этап 8. Графы (12 задач)

68. Number of Islands – Medium – DFS / BFS на матрице
69. Max Area of Island – Medium – DFS / BFS
70. Clone Graph – Medium – DFS / BFS + HashMap
71. Course Schedule – Medium – Топологическая сортировка
72. Course Schedule II – Medium – Топологическая сортировка
73. Pacific Atlantic Water Flow – Medium – DFS с двух сторон
74. Rotting Oranges – Medium – BFS (Multi-source)
75. Word Ladder – Hard – BFS на графе слов
76. Network Delay Time – Medium – Dijkstra
77. Min Cost to Connect All Points – Medium – Union-Find / MST
78. Number of Provinces – Medium – Union-Find / DFS
79. Redundant Connection – Medium – Union-Find

---

### ✅ Этап 9. Backtracking (7 задач)

80. Subsets – Medium – Backtracking — основа
81. Subsets II – Medium – С дубликатами
82. Permutations – Medium – Backtracking с visited
83. Permutations II – Medium – С дубликатами
84. Combination Sum – Medium – Backtracking с повторениями
85. Combination Sum II – Medium – Без повторений
86. Letter Combinations of a Phone Number – Medium – Backtracking

---

### ✅ Этап 10. Динамическое программирование (14 задач)

87. Climbing Stairs – Easy – 1D DP
88. House Robber – Medium – 1D DP
89. House Robber II – Medium – 1D DP + circular
90. Longest Palindromic Substring – Medium – 2D DP / расширение от центра
91. Longest Common Subsequence – Medium – 2D DP
92. Longest Increasing Subsequence – Medium – 1D DP + binary search
93. Partition Equal Subset Sum – Medium – 0/1 Knapsack
94. Coin Change – Medium – Unbounded Knapsack
95. Coin Change II – Medium – Unbounded (количество способов)
96. Word Break – Medium – 1D DP + HashSet
97. Decode Ways – Medium – 1D DP
98. Unique Paths – Medium – 2D DP → 1D DP
99. Minimum Path Sum – Medium – 2D DP (Grid DP)
100. Edit Distance – Hard – 2D DP

---

### ✅ Бонус: Префиксные суммы и битовые операции (6 задач)

101. Range Sum Query - Immutable – Easy – Prefix sum
102. Subarray Sum Equals K – Medium – Prefix sum + HashMap
103. Single Number – Easy – XOR
104. Single Number II – Medium – Подсчёт битов по модулю 3
105. Sum of Two Integers – Medium – Битовая арифметика
106. Number of 1 Bits – Easy – Битовая арифметика / алгоритм Кернигана

---

## ⚙️ Как я работаю

### Первый круг (завершён)
- **Разбор каждой задачи** с объяснением паттерна и логики.
- Прогон в **дебаггере** для понимания движения указателей, индексов, значений.
- Ведение заметок по каждому паттерну.

### Второй круг (в планах)
- **10–15 минут** самостоятельной попытки решить задачу без подсказок.
- Если не выходит — подсмотреть **идею** (не код), затем написать своё решение.
- Постепенно уменьшать количество подсказок до нуля.

---

## 📊 Итоги первого круга

- **Период:** 4 августа — 18 сентября (45 дней).
- **Задач разобрано:** 106.
- **Средний темп:** ~2.4 задачи в день.

⭐ **Этот метод помогает мне постепенно и глубоко освоить алгоритмы.**