package interviewPrepare.microsoft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSortQuestion {
    // Problem:
    //You are given n tasks labeled from 0 to n - 1, and a list of dependency pairs dependencies, where dependencies[i] = [a, b]
    //means task a depends on task b, i.e., you must complete task b before task a.
    //
    //Return any valid order to complete all tasks. If it is impossible due to a cycle, return an empty list.

    //Input: n = 4, dependencies = [[1,0],[2,0],[3,1],[3,2]]
    //Output: [0,1,2,3] or [0,2,1,3]
    //
    //Input: n = 2, dependencies = [[1,0],[0,1]]
    //Output: []

    public static List<Integer> findTaskOrder(int n, int[][] dependencies){
        List<List<Integer>> g = new ArrayList<>();
        int[] indegree = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < n; i++){
            g.add(new ArrayList<>());
        }
        for(int[] e: dependencies){
            // [a,b]: b->a
            g.get(e[1]).add(e[0]);
            indegree[e[0]]++;
        }
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int cur = queue.poll();
            ans.add(cur);
            for(int next: g.get(cur)){
                indegree[next]--;
                if(indegree[next] == 0){
                    queue.add(next);
                }
            }
        }

        return ans.size() == n? ans:new ArrayList<>();
    }

    //Follow Up:
    // You are given the same task list as in Problem 1, but now each task i takes cost[i] time units to complete.
    //
    // Tasks can only be executed after all their dependencies are completed. However, tasks with no mutual dependencies can be run concurrently.
    //
    // Return the minimum time required to complete all tasks. If the tasks contain a cycle, return -1.

    //Input: n = 4, dependencies = [[1,0],[2,0],[3,1],[3,2]], cost = [1,2,3,4]
    //Output: 8
    //Explanation:
    //- Task 0 finishes at t = 1
    //- Task 1 finishes at t = 3, Task 2 at t = 4
    //- Task 3 starts after both (at t = 4) and takes 4 → finishes at t = 8
    //
    //Input: n = 2, dependencies = [[1,0],[0,1]], cost = [1,1]
    //Output: -1



    public static void main(String[] args){
//        int[][] tasks = {
//                {1,0},{2,0},{3,1},{3,2}
//        };
//        int n = 4;

        int[][] tasks = {
                {1,0},{0,1}
        };
        int n = 2;

        List<Integer> res = findTaskOrder(n, tasks);

        for(Integer a: res){
            System.out.print(a + " ");
        }
    }
}
