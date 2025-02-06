package class098;

public class BigShow {
    public static void main(String[] args){
        System.out.println("f1() : ");
        System.out.println("矩阵乘法展示开始");
        f1();
        System.out.println("矩阵乘法展示结束");
        System.out.println();
    }

    public static int[][] multiply(int[][] a, int[][] b){
        int m = a.length;
        int n = b[0].length;
        int k = b.length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int x = 0; x<k; x++){
                    res[i][j] += a[i][x]*b[x][j];
                }
            }
        }

        return res;
    }

    public static int[][] power(int[][] m, int p){
        int[][] res;


        return res;
    }

    // 打印二维矩阵
    public static void print(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] < 10) {
                    System.out.print(m[i][j] + "   ");
                } else if (m[i][j] < 100) {
                    System.out.print(m[i][j] + "  ");
                } else {
                    System.out.print(m[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // 矩阵乘法的展示
    public static void f1() {
        int[][] a = { { 1, 3 }, { 4, 2 } };
        int[][] b = { { 2, 3 }, { 3, 2 } };
        //        2  3
        //        3  2
        //
        // 1  3  11  9
        // 4  2  14 16
        int[][] ans1 = multiply(a, b);
        print(ans1);
        System.out.println("======");
        int[][] c = { { 2, 4 }, { 3, 2 } };
        int[][] d = { { 2, 3, 2 }, { 3, 2, 3 } };
        //         2  3  2
        //         3  2  3
        //
        // 2  4   16 14 16
        // 3  2   12 13 12
        int[][] ans2 = multiply(c, d);
        print(ans2);
        System.out.println("======");
        int[][] e = { { 2, 4 }, { 1, 2 }, { 3, 1 } };
        int[][] f = { { 2, 3 }, { 4, 1 } };
        //          2  3
        //          4  1
        //
        // 2  4    20 10
        // 1  2    10  5
        // 3  1    10 10
        int[][] ans3 = multiply(e, f);
        print(ans3);
        System.out.println("======");
        int[][] g = { { 3, 1, 2 } };
        int[][] h = { { 1, 2, 1 }, { 3, 2, 1 }, { 4, 2, -2 } };
        //           1  2  1
        //           3  2  1
        //           4  2 -2
        //
        // 3  1  2  14 12  0
        int[][] ans4 = multiply(g, h);
        print(ans4);
    }
}
