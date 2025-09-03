package interviewPrepare.uber.vo;

import java.util.*;

public class FolderSystem {
    //Folder system:
    //Add folder[0, {7,3,8},"folderR1"], [3, {}, "folder3"], [8, {9}, "folder8"]
    // [9, {}, "folder9"] [7, {}, "folder7"] [0, {}, "folderR2"][5,{},"folder5"]
    //Print(n) if n is not a path from root, return empty string, is a path link print(9) return folderR1->folder8->folder9

    static class folderSystem{
        class folder{
            int id;

            int fatherId;
            List<Integer> subFolders;
            String folderName;

            public folder(int id){
                this.id = id;
                fatherId = -1;
                folderName = "";
            }
            public folder(int id, int fatherId, List<Integer> subFolders, String folderName){
                this.id = id;
                this.subFolders = subFolders;
                this.folderName = folderName;
                this.fatherId = fatherId;
            }
        }

        Map<Integer, folder> map;
        public folderSystem(){
            map = new HashMap<>();
        }

        public void add(int id, List<Integer> subFolders, String folderName){
            if(map.containsKey(id) && !map.get(id).folderName.equals("")){
                return;
            }

            map.putIfAbsent(id, new folder(id));
            folder cur = map.get(id);
            cur.folderName = folderName;
            cur.subFolders = subFolders;
            for(int sub:subFolders){
                map.putIfAbsent(sub, new folder(sub));
                map.get(sub).fatherId = cur.id;
            }
        }

        public String print(int id){
            if(!map.containsKey(id)){
                return "";
            }

            Stack<String> stack = new Stack<>();
            folder cur = map.get(id);
            stack.add(cur.folderName);
            while(cur.fatherId != -1){
                cur = map.get(cur.fatherId);
                stack.add(cur.folderName);
            }

            StringBuilder res = new StringBuilder();
            while(!stack.isEmpty()){
                res.append(stack.pop());
                if(!stack.isEmpty()){
                    res.append("->");
                }
            }
            return res.toString();
        }
    }

    public static void main(String[] args){
        folderSystem test = new folderSystem();
        //Add folder[0, {7,3,8},"folderR1"], [3, {}, "folder3"], [8, {9}, "folder8"]
        // [9, {}, "folder9"] [7, {}, "folder7"] [0, {}, "folderR2"][5,{},"folder5"]
        test.add(0, Arrays.asList(7,3,8), "folderR1");
        test.add(3, new ArrayList<>(), "folder3");
        test.add(8, Arrays.asList(9), "folder8");
        test.add(9, new ArrayList<>(), "folder9");
        test.add(7, new ArrayList<>(), "folder7");
        test.add(0, new ArrayList<>(), "folderR2");
        test.add(5, new ArrayList<>(), "folder5");


        System.out.println(test.print(0));

    }



}
