package interviewPrepare;

public class copyListWithRandomPointerVariant_138 {
    // Clone binary tree with random pointer
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode random;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode(int val, TreeNode left, TreeNode right, TreeNode random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public static TreeNode CopyBinaryTreeWithRandomPointer(TreeNode head){
        f(head);
        TreeNode copyhead = head.left;
        SettingRandom(head);
        Reset(head);
        return copyhead;
    }

    public static void f(TreeNode cur){
        if(cur == null){
            return;
        }
        f(cur.left);
        f(cur.right);

        TreeNode copycur = new TreeNode(cur.val, cur.left, cur.right);
        cur.left = copycur;
    }

    public static void SettingRandom(TreeNode cur){
        if(cur == null){
            return;
        }

        TreeNode copycur = cur.left;
        copycur.random = cur.random == null? null: cur.random.left;
        SettingRandom(copycur.left);
        SettingRandom(copycur.right);
    }

    public static void Reset(TreeNode cur){
        if(cur == null){
            return;
        }

        TreeNode copycur = cur.left;
        cur.left = copycur.left;
        cur.right = copycur.right;
        copycur.left = cur.left == null? null:cur.left.left;
        copycur.right = cur.right == null? null:cur.right.left;

        Reset(cur.left);
        Reset(cur.right);
    }
    public static void testCopyBinaryTreeWithRandomPointer() {
        // 构建如下测试树：
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        //
        // 设置 random：
        // 1.random = 3
        // 2.random = 1
        // 3.random = 2
        // 4.random = 3

        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        node2.left = node4;

        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1, node2, node3);

        // 设置 random pointers
        node1.random = node3;
        node2.random = node1;
        node3.random = node2;
        node4.random = node3;

        TreeNode copied = CopyBinaryTreeWithRandomPointer(node1);

        // 验证拷贝结构与 random 指针
        System.out.println("Copied root val: " + copied.val); // 1
        System.out.println("Copied left.val: " + copied.left.val); // 2
        System.out.println("Copied right.val: " + copied.right.val); // 3
        System.out.println("Copied left.left.val: " + copied.left.left.val); // 4

        System.out.println("Copied.random.val = " + copied.random.val); // 3
        System.out.println("Copied.left.random.val = " + copied.left.random.val); // 1
        System.out.println("Copied.right.random.val = " + copied.right.random.val); // 2
        System.out.println("Copied.left.left.random.val = " + copied.left.left.random.val); // 3
    }

    public static void main(String[] args){
        testCopyBinaryTreeWithRandomPointer();
    }

}
