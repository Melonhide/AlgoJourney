package interviewPrepare.microsoft;

import java.util.*;

public class DnaResembling {
    public static class sequence{
        public String start_tag;
        public String end_tag;
        public String payload;

        public sequence(String a, String b, String c){
            start_tag = a;
            end_tag = b;
            payload = c;
        }

    }

    public static String resemble(List<sequence> arr){
        Map<String, sequence> map = new HashMap<>();
        Map<String, String> graph = new HashMap<>();
        for(sequence cur: arr){
            map.put(cur.end_tag, cur);
            graph.put(cur.start_tag, cur.end_tag);

        }
        String start = "";
        for(sequence cur: arr){
            if(!map.containsKey(cur.start_tag)){
                start = cur.start_tag;
                break;
            }
        }

        StringBuilder res = new StringBuilder();
        while(graph.containsKey(start)){
            start = graph.get(start);
            res.append(map.get(start).payload);
        }

        return res.toString();
    }

    public static void main(String[] args){
        sequence a = new sequence("AAA", "AGC", "AAAA");
        sequence b = new sequence("AGC", "ATT", "CCCC");
        sequence c = new sequence("ATT", "CTT", "GGGG");
        sequence d = new sequence("CTT", "ACC", "TTTT");
        List<sequence> input = Arrays.asList(a, b, c, d);
        System.out.println(resemble(input));
    }
}
