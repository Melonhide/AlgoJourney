package interviewPrepare;

import java.util.*;

public class accountsMergeVariant_721 {

    // Given a list of accounts represented as ID → emails,
    // merge the IDs if they share any common emails (i.e., they belong to the same person).
    // Return the merged groups of IDs. The order of accounts and IDs does not matter.
    //
    // Example:
    // Input:
    // accounts = [["C1": ["a","b"]], ["C2": ["c"]], ["C3": ["b","d"]],
    //             ["C4": ["d"]], ["C5": ["e"]], ["C6": ["c"]], ["C7": ["a"]]]
    //
    // Output:
    // [["C1","C3","C4","C7"], ["C2","C6"], ["C5"]]
    //
    // Explanation:
    // - C1, C3, C4, and C7 are merged because they share emails "a" or "b" or "d".
    // - C2 and C6 are merged because they share email "c".
    // - C5 is alone as no emails overlap.

    public static List<List<String>> accountsMerge(Map<String, List<String>> accounts){
        Map<String, Node> map = new HashMap<>();
        for(String key: accounts.keySet()){
            String email = accounts.get(key).get(0);
            map.putIfAbsent(email, build(email));
            Node f = map.get(email);
            for(int i = 1; i < accounts.get(key).size(); i++){
                String curemail = accounts.get(key).get(i);
                map.putIfAbsent(curemail, build(curemail));
                union(f, map.get(curemail));
            }
        }

        Map<Node, List<String>> res = new HashMap<>();
        for(String key: accounts.keySet()){
            String email = accounts.get(key).get(0);
            Node f = find(map.get(email));
            res.putIfAbsent(f, new ArrayList<>());
            res.get(f).add(key);
        }

        List<List<String>> ans = new ArrayList<>();
        for(Node key: res.keySet()){
            ans.add(res.get(key));
        }

        return ans;
    }

    static class Node{
        String email;
        Node father;
        public Node(String e){
            email = e;
        }


    }
    public static Node build(String e){
        Node a = new Node(e);
        a.father = a;
        return a;
    }
    private static void union(Node a, Node b){
        Node fa = find(a);
        Node fb = find(b);
        if(fa == fb){
            return;
        }
        fa.father = fb;
    }

    private static boolean isSameSet(Node a, Node b){
        return find(a) == find(b);
    }

    private static Node find(Node a){
        if(a.father == a){
            return a;
        }
        a.father = find(a.father);
        return a.father;
    }


    public static void main(String[] args){
        // accounts = [["C1": ["a","b"]], ["C2": ["c"]], ["C3": ["b","d"]],
        //             ["C4": ["d"]], ["C5": ["e"]], ["C6": ["c"]], ["C7": ["a"]]]
        Map<String, List<String>> accounts = new HashMap<>();
        accounts.put("C1", Arrays.asList("a","b"));
        accounts.put("C2", Arrays.asList("c"));
        accounts.put("C3", Arrays.asList("b","d"));
        accounts.put("C4", Arrays.asList("d"));
        accounts.put("C5", Arrays.asList("e"));
        accounts.put("C6", Arrays.asList("c"));
        accounts.put("C7", Arrays.asList("a"));

        List<List<String>> res = accountsMerge(accounts);
        for (List<String> cur:res) {
            for (String id : cur) {
                System.out.print(id + " ");
            }
            System.out.println();
        }
    }
}
