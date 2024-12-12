package class096;
// 计算两堆石子的SG值
// 桌上有两堆石子，石头数量分别为a、b
// 任取一堆石子，将其移走，然后分割同一组的另一堆石子
// 从中取出若干个石子放在被移走的位置，组成新的一堆
// 操作完成后，组内每堆的石子数必须保证大于0
// 显然，被分割的一堆的石子数至少要为2
// 两个人轮流进行分割操作，如果轮到某人进行操作时，两堆石子数均为1，判此人输掉比赛
// 计算sg[a][b]的值并找到简洁规律
// 本文件仅为题目5打表找规律的代码
public class EDGame1 {
    public static int maxn = 1001;

    public static int[][] dp = new int[maxn][maxn];
    public static void build(){
        for(int i = 0; i < maxn; i++){
            for(int j = 0; j < maxn; j++){
                dp[i][j] = -1;
            }
        }
    }

    public static ing sg(int a, int b){

    }
}
