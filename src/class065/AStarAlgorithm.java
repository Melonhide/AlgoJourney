package class065;

import java.util.PriorityQueue;

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
        distance[startX][startY] = 1;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[2]-b[2]);
        heap.add(new int[] {startX, startY, 1});

        while(!heap.isEmpty()){
            int[] cur = heap.poll();
            int x = cur[0];
            int y = cur[1];
            if(visited[x][y]){
                continue;
            }

            if(x == targetX && y == targetY){
                return distance[x][y];
            }

            for(int i = 0, nx, ny; i<4; i++){
                nx = x+move[i];
                ny = y+move[i+1];
                if(nx>=0&&nx<m && ny>=0 && ny<n && grid[nx][ny] == 1
                        && !visited[nx][ny] && distance[x][y]+1<distance[nx][ny]){
                    distance[nx][ny] = distance[x][y]+1;
                    heap.add(new int[] {nx,ny,distance[nx][ny]});
                }
            }
        }
        return -1;

    }
}
