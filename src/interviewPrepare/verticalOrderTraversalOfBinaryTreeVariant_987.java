package interviewPrepare;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.lang.Integer;


public class verticalOrderTraversalOfBinaryTreeVariant_987 {


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
    // Variant 1: Print the order

    static HashMap<Integer, PriorityQueue<int[]>> map = new HashMap<>();
    static int min;
    static int max;
    public static void printVerticalOrder(TreeNode root){
        map = new HashMap<>();
        min = 0;
        max = 0;
        f(root, 0, 0);
        for(int i = min; i<= max ; i++){
            System.out.print("[");
            while(map.get(i).size() != 1){
                System.out.print(map.get(i).poll()[1] + ",");
            }
            System.out.print(map.get(i).poll()[1]);
            System.out.print("]");
            System.out.println();
        }


    }

    public static void f(TreeNode root, int x, int y){
        if(root == null){
            return;
        }
        min = Math.min(min, x);
        max = Math.max(max, x);

        map.putIfAbsent(x, new PriorityQueue<>((a,b)->(a[0]!=b[0]? a[0]-b[0]:a[1]-b[1])));
        map.get(x).add(new int[]{y, root.val});
        f(root.left, x-1,y+1);
        f(root.right, x+1, y+1);
    }

    // Variant 2: Print the 1D order



    public static void main(String[] args){
        Integer[] nums = new Integer[]{3,9,20,null,null,15,7};
        TreeNode root = parse(nums);
        printVerticalOrder(root);
    }

    public static TreeNode parse(Integer[] nums){
        TreeNode[] res = new TreeNode[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != null){
                res[i] = new TreeNode(nums[i]);
            }
        }
        for(int i = 0; i < nums.length; i++){
            TreeNode cur = res[i];
            if(cur==null){
                continue;
            }
            TreeNode left = i*2+1< nums.length? res[i*2+1]:null;
            TreeNode right = i*2+2< nums.length? res[i*2+2]:null;
            cur.left = left;
            cur.right = right;
        }


        return res[0];
    }
}
