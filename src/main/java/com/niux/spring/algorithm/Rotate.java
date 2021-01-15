package com.niux.spring.algorithm;

/**
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 */
public class Rotate {

    public static void main(String[] args) {
        int[][] r = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        new Rotate().rotate(r);

        System.out.println(r);
    }

    public void rotate(int[][] matrix) {

    }

}
