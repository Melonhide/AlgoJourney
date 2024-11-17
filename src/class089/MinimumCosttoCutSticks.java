package class089;


import java.io.*;

// 连接棒材的最低费用(洛谷测试)
// 你有一些长度为正整数的棍子
// 这些长度以数组sticks的形式给出
// sticks[i]是第i个木棍的长度
// 你可以通过支付x+y的成本将任意两个长度为x和y的棍子连接成一个棍子
// 你必须连接所有的棍子，直到剩下一个棍子
// 返回以这种方式将所有给定的棍子连接成一个棍子的最小成本
// 测试链接 : https://www.luogu.com.cn/problem/P1090
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class MinimumCosttoCutSticks {
    public static int n;
    public static int maxn = 100001;
    public static int[] nums = new int[maxn];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int[] heap = new int[maxn];
    public static int size;
    public static int compute(){
        size = 0;
        for(int i = 0; i < n; i++){
            add(nums[i]);
        }

        int ans = 0;
        while(size > 1){
            int cur = pop()+pop();
            ans += cur;
            add(cur);
        }
        return ans;
    }


    public static void add(int n){
        heap[size++] = n;
        int i = size-1;
        while(i>=0 && heap[(i-1)/2]>heap[i]){
            swap((i-1)/2, i);
            i = (i-1)/2;
        }
    }


    public static int pop(){
        swap(0, --size);
        int i = 0;
        int left = 1;
        while(left<size){
            int small = left;
            if(left+1<size && heap[left+1]<heap[left]){
                small = left+1;
            }

            if(heap[i]<=heap[small]){
                break;
            }
            swap(i, small);
            i = small;
            left = i*2+1;
        }
        return heap[size];
    }


    public static void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }



}
