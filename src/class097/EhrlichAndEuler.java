package class097;

// 计数质数
// 给定整数n，返回小于非负整数n的质数的数量
// 测试链接 : https://leetcode.cn/problems/count-primes/
public class EhrlichAndEuler {

    public static int ehrlich(int n){
        boolean[] visited = new boolean[n];
        for(int i = 2; i < n; i++){
            if(!visited[i]){
                for(int j = i; j*i<n; j++){
                    visited[i*j] = true;
                }
            }
        }
        int ans = 0;
        for(int i = 2; i <n; i++){
            ans += visited[i]? 0:1;
        }

        return ans;
    }

    public static int euler(int n) {
        int cnt = 0;
        int[] primes = new int[n/2+1];
        boolean[] visited = new boolean[n];
        for(int i = 2; i < n; i++){
            if(!visited[i]){
                primes[cnt++] = i;
            }

            for(int j = 0; j<cnt; j++){
                if(i*primes[j]>=n){
                    break;
                }

                visited[i*primes[j]] = true;
                if(primes[j]%i == 0){
                    break;
                }
            }
        }

        return cnt;
    }


}
