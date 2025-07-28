package interviewPrepare.uber.oaProblem4;


import java.util.HashMap;
import java.util.Map;

// 给两个string list，找两个string间最长的 common prefix。LC14变种。暴力解只能过一半的case。
public class FindCommonPrefixFromTwoStringList {

    public static class trie{
        static class trieNode{
            Map<Character, trieNode> next;

            public trieNode(){
                next = new HashMap<>();
            }
        }
        trieNode root;
        public trie(){
            root = new trieNode();
        }

        public void insert(String word){
            trieNode cur = root;
            for(char c: word.toCharArray()){
                if(!cur.next.containsKey(c)){
                    cur.next.put(c, new trieNode());
                }
                cur = cur.next.get(c);
            }
        }

        public String commonPrefix(String word){
            trieNode cur = root;
            StringBuilder res = new StringBuilder();
            for(char c: word.toCharArray()){
                if(!cur.next.containsKey(c)){
                    break;
                }
                res.append(c);
                cur = cur.next.get(c);
            }
            return res.toString();
        }
    }

    public static String findCommonPrefix(String[] str1, String[] str2){
        trie tree = new trie();
        for(String s: str1){
            tree.insert(s);
        }
        String res = "";
        for(String s:str2){
            String cur = tree.commonPrefix(s);
            if(cur.length()>res.length()){
                res = cur;
            }
        }

        return res;
    }


    public static void main(String[] args){
        String[] strs1 = {"interview", "interval", "internal", "internet"};
        String[] strs2 = {"into", "intensive", "intelligent"};

        System.out.println(findCommonPrefix(strs1, strs2));
    }
}
