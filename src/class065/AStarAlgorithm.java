package class065;

public class AStarAlgorithm {
    public static int[] move = new int[] {-1,0,1,0,-1};

    // Dijkstra算法
    // grid[i][j] == 0 代表障碍
    // grid[i][j] == 1 代表道路
    // 只能走上、下、左、右，不包括斜线方向
    // 返回从(startX, startY)到(targetX, targetY)的最短距离
    public static int minDistance1(int[][] grid, int startX, int startY, int targetX, int targetY){
        if(grid[startX][startY] == 0 || grid[targetX][targetY] == 0){
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}
