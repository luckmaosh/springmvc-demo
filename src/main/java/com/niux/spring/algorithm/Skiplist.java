package com.niux.spring.algorithm;

import java.util.Random;

class Skiplist {
    class SkipListNode {
        int val;
        int cnt;  // 当前val出现的次数
        SkipListNode[] levels;  // start from 0

        SkipListNode() {
            levels = new SkipListNode[MAX_LEVEL];
        }
    }

    private double p = 0.5;
    private int MAX_LEVEL = 16;
    private SkipListNode head;  // 头结点
    private int level;  // 
    private Random random;

    public Skiplist() {
        // 保存此level有利于查询（以及其他操作）
        level = 0;  // 当前 skiplist的高度（所有数字 level数最大的）
        head = new SkipListNode();
        random = new Random();
    }

    // 返回target是否存在于跳表中
    public boolean search(int target) {
        SkipListNode curNode = head;
        for (int i = level - 1; i >= 0; i--) {
            while (curNode.levels[i] != null && curNode.levels[i].val < target) {
                curNode = curNode.levels[i];
            }
        }
        curNode = curNode.levels[0];
        return (curNode != null && curNode.val == target);
    }

    // 插入一个元素到跳表。
    public void add(int num) {
        SkipListNode curNode = head;
        // 记录每层能访问的最右节点
        SkipListNode[] levelTails = new SkipListNode[MAX_LEVEL];
        for (int i = level - 1; i >= 0; i--) {
            while (curNode.levels[i] != null && curNode.levels[i].val < num) {
                curNode = curNode.levels[i];
            }
            levelTails[i] = curNode;
        }
        curNode = curNode.levels[0];
        if (curNode != null && curNode.val == num) {
            // 已存在，cnt 加1
            curNode.cnt++;
        } else {
            // 插入
            int newLevel = randomLevel();
            if (newLevel > level) {
                for (int i = level; i < newLevel; i++) {
                    levelTails[i] = head;
                }
                level = newLevel;
            }
            SkipListNode newNode = new SkipListNode();
            newNode.val = num;
            newNode.cnt = 1;
            for (int i = 0; i < level; i++) {
                newNode.levels[i] = levelTails[i].levels[i];
                levelTails[i].levels[i] = newNode;

            }
        }
    }

    private int randomLevel() {
        int level = 1;  // 注意思考此处为什么是 1 ？
        while (random.nextDouble() < p && level < MAX_LEVEL) {
            level++;
        }
        return level > MAX_LEVEL ? MAX_LEVEL : level;
    }

    // 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
    public boolean erase(int num) {
        SkipListNode curNode = head;
        // 记录每层能访问的最右节点
        SkipListNode[] levelTails = new SkipListNode[MAX_LEVEL];

        for (int i = level - 1; i >= 0; i--) {
            while (curNode.levels[i] != null && curNode.levels[i].val < num) {
                curNode = curNode.levels[i];
            }
            levelTails[i] = curNode;
        }
        curNode = curNode.levels[0];
        if (curNode != null && curNode.val == num) {
            if (curNode.cnt > 1) {
                curNode.cnt--;
                return true;
            }
            // 存在，删除
            for (int i = 0; i < level; i++) {
                if (levelTails[i].levels[i] != curNode) {
                    break;
                }
                levelTails[i].levels[i] = curNode.levels[i];
            }
            while (level > 0 && head.levels[level - 1] == null) {
                level--;
            }
            return true;
        }
        return false;
    }
}
//
//作者：caipengbo
//        链接：https://leetcode-cn.com/problems/design-skiplist/solution/java-lun-wen-fu-xian-by-caipengbo/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。