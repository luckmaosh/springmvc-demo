package com.niux.spring.algorithm;

/**
 * 不使用任何库函数，设计一个跳表。
 * <p>
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-skiplist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Skiplist2 {

    private int size;

    private Node head;

    public Skiplist2() {
        this.size = 0;
    }

    /**
     * 返回target是否存在于跳表中
     *
     * @param target
     * @return
     */
    public boolean search(int target) {
        if (head == null) {

            return false;
        }

        Node[] floor = head.floor;
        int length = floor.length;

        Node node = head;
        for (int j = length - 1; j >= 0; j--) {
            Node temp = floor[j];

            if (temp.num == target) {
                return true;
            }
            while (temp != null && temp.num < target) {
                if (temp.next != null && temp.next.num < target) {
                    temp = temp.next;
                }
            }

        }

        return false;
    }

    /**
     * 插入一个元素到跳表
     *
     * @param num
     */
    public void add(int num) {
        if (head == null) {
            head = new Node(num);
            head.next = null;
        }


    }

    /**
     * 在跳表中删除一个值，如果 num 不存在，直接返回false.
     * 如果存在多个 num ，删除其中任意一个即可。
     *
     * @param num
     * @return
     */
    public boolean erase(int num) {
        return false;
    }

    class Node {

        public Node(int num) {
            this.num = num;
        }

        private int num;
        /**
         * 层级
         */
        private Node[] floor;

        private Node next;
    }
}


/**
 * Your Skiplist2 object will be instantiated and called as such:
 * Skiplist2 obj = new Skiplist2();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */