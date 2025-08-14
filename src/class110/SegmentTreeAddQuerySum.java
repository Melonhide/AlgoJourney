package class110;

// 线段树支持范围增加、范围查询
// 维护累加和
// 测试链接 : https://www.luogu.com.cn/problem/P3372
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

public class SegmentTreeAddQuerySum {
    public static int maxn = 100001;
    public static long[] arr = new long[maxn];
    public static long[] sum = new long[maxn>>2];
    public static long[] lazy = new long[maxn>>2];

    public static void build(int l, int r, int i){
        if(l == r){
            sum[i] = arr[l];
            return;
        }


    }


}
