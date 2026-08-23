package com.example.sixth_step.same_tree;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode p = buildTree(new Integer[]{1, 2, 3});
        TreeNode q = buildTree(new Integer[]{1, 2, 3});

        System.out.println("Деревья идентичны: " + isSameTree(p, q));
        System.out.println("Деревья идентичны: " + isSameTreeQueue(p, q));
        System.out.println("Деревья идентичны: " + isSameTreeStackWithRecordPair(p, q));
        System.out.println("Деревья идентичны: " + isSameTreeStack(p, q));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static boolean isSameTreeQueue(TreeNode p, TreeNode q) {
        Queue<NodePair> queue = new ArrayDeque<>();
        queue.offer(new NodePair(p, q));

        while (!queue.isEmpty()) {
            NodePair pair = queue.poll();
            TreeNode nodeP = pair.p();
            TreeNode nodeQ = pair.q();

            if (nodeP == null && nodeQ == null) {
                continue;
            }
            if (nodeP == null || nodeQ == null) {
                return false;
            }
            if (nodeP.val != nodeQ.val) {
                return false;
            }

            queue.offer(new NodePair(nodeP.left, nodeQ.left));
            queue.offer(new NodePair(nodeP.right, nodeQ.right));
        }

        return true;
    }

    public static boolean isSameTreeStack(TreeNode p, TreeNode q) {
        Deque<TreeNode> stackP = new LinkedList<>();
        Deque<TreeNode> stackQ = new LinkedList<>();
        stackP.push(p);
        stackQ.push(q);

        while (!stackP.isEmpty() || !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();

            if (nodeP == null && nodeQ == null) {
                continue;
            }
            if (nodeP == null || nodeQ == null) {
                return false;
            }
            if (nodeP.val != nodeQ.val) {
                return false;
            }

            stackP.push(nodeP.left);
            stackQ.push(nodeQ.left);

            stackP.push(nodeP.right);
            stackQ.push(nodeQ.right);

        }

        return true;
    }

    public static boolean isSameTreeStackWithRecordPair(TreeNode p, TreeNode q) {
        Deque<NodePair> stack = new ArrayDeque<>();
        stack.push(new NodePair(p, q));

        while (!stack.isEmpty()) {
            NodePair nodePair = stack.pop();
            TreeNode nodeP = nodePair.p();
            TreeNode nodeQ = nodePair.q();

            if (nodeP == null && nodeQ == null) {
                continue;
            }
            if (nodeP == null || nodeQ == null) {
                return false;
            }
            if (nodeP.val != nodeQ.val) {
                return false;
            }

            stack.push(new NodePair(nodeP.left, nodeQ.left));
            stack.push(new NodePair(nodeP.right, nodeQ.right));
        }

        return true;
    }
}
