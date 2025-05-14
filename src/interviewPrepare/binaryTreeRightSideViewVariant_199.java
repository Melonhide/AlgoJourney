package interviewPrepare;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class binaryTreeRightSideViewVariant_199 {
    // Given the root of a binary tree, return the left side view and right side view.
    // First from the left side (bottom to top), followed by those from the right side(top to bottom);
    // Input: root = [1,2,3,null,5,null,4]
    // Output: [5,2,1,3,4]
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public static List<Integer> leftAndRightSideView(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        ans.add(root.val);

        if(root.right != null){
            queue.add(root.right);
        }

        if(root.left != null){
            queue.add(root.left);
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            ans.add(queue.peekFirst().val);
            ans.add(0, queue.peekLast().val);

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.pollFirst();
                if(cur.right != null){
                    queue.addLast(cur.right);
                }
                if(cur.left != null){
                    queue.addLast(cur.left);
                }
            }
        }

        return ans;
    }

    public static List<Integer> res;
    public static List<Integer> leftAndRightSideViewDFS(TreeNode root){
        res = new ArrayList<>();
        res.add(root.val);
        f1(root, 0);
        f2(root, res.size()-1);
        return res;
    }

    public static void f1(TreeNode cur, int level){
        if(cur == null){
            return;
        }
        if(level == res.size()){
            res.add(cur.val);
        }

        f1(cur.right, level+1);
        f1(cur.left, level+1);
    }

    public static void f2(TreeNode cur, int level){
        if(cur == null){
            return;
        }
        if(level == res.size()){
            res.add(0, cur.val);
        }

        f2(cur.left, level+1);
        f2(cur.right, level+1);
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(5);
//        root.left.right = new TreeNode(6);
//        root.left.right.right = new TreeNode(8);
//        root.right.right = new TreeNode(9);
//        root.right.right = new TreeNode(7);
        List<Integer> ans = leftAndRightSideViewDFS(root);

        for(Integer a: ans){
            System.out.print(a + " ");
        }
    }

}
