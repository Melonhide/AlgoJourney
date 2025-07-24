package class045;


import java.util.Arrays;

// 牛牛和他的朋友们约定了一套接头密匙系统，用于确认彼此身份
// 密匙由一组数字序列表示，两个密匙被认为是一致的，如果满足以下条件：
// 密匙 b 的长度不超过密匙 a 的长度。
// 对于任意 0 <= i < length(b)，有b[i+1] - b[i] == a[i+1] - a[i]
// 现在给定了m个密匙 b 的数组，以及n个密匙 a 的数组
// 请你返回一个长度为 m 的结果数组 ans，表示每个密匙b都有多少一致的密匙
// 数组 a 和数组 b 中的元素个数均不超过 10^5
// 1 <= m, n <= 1000
// 测试链接 : https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932
public class CountConsistentKeys {
    static int maxn = 1000001;
    static int[][] tree = new int[maxn][12];
    static int[] pass = new int[maxn];
    static int cnt;
    public static void build(int[][] nums){
        StringBuilder cur = new StringBuilder();
        cnt = 1;
        for(int[] num:nums){
            cur.setLength(0);
            for(int i = 0, j=1; j < num.length; i++, j++){
                cur.append(String.valueOf(num[j]-num[i]) + "#");
            }
            insert(cur.toString());
        }
    }

    public static int getPath(char c){
        if(c == '-'){
            return 10;
        }else if(c == '#'){
            return 11;
        }

        return c-'0';
    }
    public static void insert(String word){
        int cur = 1;
        pass[cur]++;
        for(char c: word.toCharArray()){
            int path = getPath(c);
            if(tree[cur][path] == 0){
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
    }

    public static int search(String word){
        int cur = 1;
        for(char c: word.toCharArray()){
            int path = getPath(c);
            if(tree[cur][path] == 0){
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static void clear(){
        for(int i = 1; i <= cnt; i++){
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

    public static int[] countConsistentKeys (int[][] b, int[][] a) {
        build(a);
        int[] res = new int[b.length];
        StringBuilder cur = new StringBuilder();
        cnt = 1;
        for(int k = 0; k<b.length; k++){
            int[] num = b[k];
            cur.setLength(0);
            for(int i = 0, j=1; j < num.length; i++, j++){
                cur.append(String.valueOf(num[j]-num[i]) + "#");
            }
            res[k] = search(cur.toString());
        }

        clear();
        return res;
    }

    public static void main(String[] args){
        int[][] a = new int[][] {{3, 4, 5, 6, 7, 8}, {2, 4, 6, 8}, {1, 3, 5, 7, 9}};
        int[][] b = new int[][] {{1, 2, 3, 4, 5}, {2, 4, 6, 8}, {1, 4, 7, 10}};
        int[] res = countConsistentKeys(b,a);
        for(int num:res){
            System.out.print(num + " ");
        }
    }

}
