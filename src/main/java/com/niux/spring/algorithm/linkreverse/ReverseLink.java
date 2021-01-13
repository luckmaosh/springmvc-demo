package com.niux.spring.algorithm.linkreverse;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseLink {

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        int k = 3;

        ListNode reverse = new ReverseLink().reverseKGroup(node, k);
        print(reverse);
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

    }

    public ListNode reverseKGroup(ListNode node, int k) {
        if (node == null) {
            return null;
        }
        ListNode h = null, tail = null;

        Deque<Integer> deque = new ArrayDeque<>(k);
        while (node != null) {
            int val = node.val;
            if (deque.size() < k) {
                deque.push(val);
                node = node.next;

                if (deque.size() == k) {
                    //pop
                    while (!deque.isEmpty()) {
                        Integer pop = deque.pop();
                        if (tail == null) {
                            tail = new ListNode(pop);
                            h = tail;
                        } else {
                            tail.next = new ListNode(pop);
                            tail = tail.next;
                        }
                    }

                }
            }
        }

        ListNode end = null;
        ListNode emptyHead = new ListNode(0);
        while (!deque.isEmpty()) {
            Integer pop = deque.pop();

            ListNode insert = new ListNode(pop);
            insert.next = end;
            end = insert;
            emptyHead.next = end;
        }

        if (h != null) {
            tail.next = emptyHead.next;
            return h;
        } else {
            return emptyHead.next;
        }
    }
}
