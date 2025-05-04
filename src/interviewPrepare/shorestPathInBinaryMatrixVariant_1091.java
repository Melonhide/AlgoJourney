package interviewPrepare;

import java.util.*;
public class shorestPathInBinaryMatrixVariant_1091 {
    public static int[] dir = new int[]{-1,0,1,0,-1}; // (x-1, y); (x, y+1); (x+1,y); (x,y-1);

    // Find the shortest path in binary matrix.
    public static List<List<Integer>> bfs_with_path(int[][] nums){
        int n = nums.length;
        int m = nums[0].length;
        Queue<List<List<Integer>>> queue = new LinkedList<>(); //<<x,y><path>>
        if(nums[n-1][m-1] == 1 || nums[0][0] == 1){
            return new ArrayList<>();
        }

        if(n==1&&m==1){
            return Arrays.asList(Arrays.asList(0,0));
        }
        nums[0][0] = 1;
        List<List<Integer>> cur = new ArrayList<>();
        List<Integer> curPath = Arrays.asList(0,0);
        cur.add(Arrays.asList(0,0));
        cur.add(curPath);
        queue.add(cur);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0, x, y; k<size; k++){
                cur = queue.poll();
                x = cur.get(0).get(0);
                y = cur.get(0).get(1);
                curPath = cur.get(1);
                for(int i = 0, nx, ny; i < 4; i++){
                    nx = x+dir[i];
                    ny = y+dir[i+1];
                    if(nx<n&&nx>=0&&ny<m&&ny>=0 && nums[nx][ny] == 0){
                        nums[nx][ny] = 1;
                        List<List<Integer>> next = new ArrayList<>();
                        next.add(Arrays.asList(nx,ny));
                        List<Integer> nextPath = new ArrayList<>();
                        for(int loc:curPath){
                            nextPath.add(loc);
                        }
                        nextPath.add(nx);
                        nextPath.add(ny);
                        next.add(nextPath);
                        queue.add(next);
                        if(nx == n-1 && ny == m-1){
                            return f(nextPath);
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public static List<List<Integer>> f(List<Integer> path){
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < path.size(); i+=2){
            ans.add(Arrays.asList(path.get(i), path.get(i+1)));
        }

        return ans;
    }

    public static int[] direction = new int[]{-1,0,1,0,-1,-1,1,1,-1};
    //                              (i-1, j)(i, j+1)(i+1, j)(i, j-1)
    //                              (i-1, j-1)(i-1, j+1)(i+1, j+1)(i+1, j-1)
    public static int bfs1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[n-1][m-1] == 1 || grid[0][0] == 1){
            return -1;
        }
        if(n == 1 && m == 1){
            return 1;
        }

        int[][] queue = new int[10001][2];
        int l = 0;
        int r = 0;
        grid[0][0] = 1;
        queue[r++] = new int[]{0,0};
        int ans = 0;
        while(l<r){
            int size = r-l;
            ans++;
            for(int x = 0, i, j; x<size; x++){
                i = queue[l][0];
                j = queue[l++][1];
                for(int k = 0, ni, nj; k < 8; k++){
                    ni = i+direction[k];
                    nj = j+direction[k+1];
                    if(ni<n && ni>=0 && nj<m && nj>=0 && grid[ni][nj] == 0){
                        grid[ni][nj] = 1;
                        queue[r++] = new int[]{ni,nj};
                    }

                    if(ni == n-1 && nj == m-1){
                        return ans+1;
                    }
                }
            }
        }
        return -1;
    }

    public static int bfs2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[n-1][m-1] == 1 || grid[0][0] == 1){
            return -1;
        }
        if(n == 1 && m == 1){
            return 1;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        grid[0][0] = 1;
        int ans = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            ans++;
            for(int x = 0, i, j; x<size; x++){
                int[] cur = queue.poll();
                i = cur[0];
                j = cur[1];
                for(int k = 0, ni, nj; k < 8; k++){
                    ni = i+direction[k];
                    nj = j+direction[k+1];
                    if(ni<n && ni>=0 && nj<m && nj>=0 && grid[ni][nj] == 0){
                        grid[ni][nj] = 1;
                        queue.add(new int[] {ni, nj});
                        if(ni == n-1 && nj == m-1){
                            return ans+1;
                        }
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        // 测试用例 1: 普通矩阵（无障碍）
        int[][] grid1 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Test Case 1 (无障碍矩阵):");
        printPaths(bfs_with_path(grid1));

        // 测试用例 2: 有障碍物（1表示障碍）
        int[][] grid2 = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println("\nTest Case 2 (有障碍物):");
        printPaths(bfs_with_path(grid2));

        // 测试用例 3: 无可行路径
        int[][] grid3 = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        System.out.println("\nTest Case 3 (无可行路径):");
        printPaths(bfs_with_path(grid3));

        // 测试用例 4: 单点矩阵（1x1）
        int[][] grid4 = {{0}};
        System.out.println("\nTest Case 4 (1x1矩阵):");
        printPaths(bfs_with_path(grid4));

        int[][] grid5 = {
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 1, 1, 0}
        };

        System.out.println("复杂测试案例（带障碍物）：");
        List<List<Integer>> paths = bfs_with_path(grid5);
        printPaths(paths);
    }

    /**
     * 打印所有路径
     */
    private static void printPaths(List<List<Integer>> paths) {
        if (paths == null || paths.isEmpty()) {
            System.out.println("无可行路径！");
            return;
        }
        System.out.println("找到最短路径:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}
