package com.niux.spring.algorithm.ancestor;

/**
 * 最近公共祖先
 */
class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    /**
     * 最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //没有公共祖先
        if (left == null && right == null) {
            return null; // 1.
        }
        //pq 不在左边
        if (left == null) {
            return right; // 3.
        }
        //pq不在右边
        if (right == null) {
            return left; // 4.
        }
        //同时分布在左右
        return root; // 2. if(left != null and right != null)
    }
}

//作者：jyd
//链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。