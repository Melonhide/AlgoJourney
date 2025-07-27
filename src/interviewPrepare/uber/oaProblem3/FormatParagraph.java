package interviewPrepare.uber.oaProblem3;

import java.util.ArrayList;
import java.util.List;

public class FormatParagraph {
    //You are given a 2D list of strings paragraphs, where each inner list represents a paragraph consisting of one or more words.
    //
    //You are also given an integer width, representing the maximum line width, excluding border characters (*).
    //
    //Your task is to format and return the paragraph according to the following rules:
    //
    //The first and last lines must consist entirely of '*', and their length should be width + 2.
    //
    //Each paragraph’s content must be kept separate, i.e., words from different paragraph[i] must not appear on the same line.
    //
    //Within a paragraph:
    //
    //Words can be placed on the same line if they fit, separated by a single space.
    //
    //You can use multiple lines if needed to fit all words.
    //
    //For each line of content:
    //
    //The remaining space (after placing the words and spaces) should be split evenly between the left and right.
    //
    //If the remaining space is odd, the right side gets the extra space.
    //
    //The line should be surrounded by '*' on both ends.
    //
    //Return the list of strings representing the fully formatted paragraph.

    //Input:
    //paragraphs = [["hello", "world"], ["foo", "bar"]]
    //width = 8
    //
    //Output:
    //[
    //  "**********",
    //  "* hello  *",
    //  "* world  *",
    //  "*foo bar *",
    //  "**********"
    //]

    public static List<String> formatParagraph(String[][] paragraph, int width){
        List<String> res = new ArrayList<>();
        StringBuilder start = new StringBuilder();
        StringBuilder words = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        cur.append("*");
        for(int i = 0; i < width+2; i++){
            start.append("*");
        }
        res.add(start.toString());
        for(String[] p: paragraph){
           cur.setLength(1);
           words.append(p[0]);

           for(int i = 1, totalNeed, l, r; i <= p.length; i++){
               if(i<p.length && words.length()+1+p[i].length() <= width){
                   words.append(" ");
                   words.append(p[i]);
               }else{
                   totalNeed = width - words.length();
                   l = totalNeed/2;
                   r = totalNeed-l;
                   for(int j = 0; j<l; j++){
                       cur.append(" ");
                   }
                   cur.append(words.toString());
                   for(int j = 0; j<r; j++){
                       cur.append(" ");
                   }
                   cur.append("*");
                   res.add(cur.toString());
                   words.setLength(0);
                   cur.setLength(1);
                   if(i<p.length){
                        words.append(p[i]);
                   }
               }
           }

        }


        res.add(start.toString());
        return res;
    }


    public static void main(String[] args){
        String[][] paragraph = new String[][]{{"hello", "world"}, {"foo", "bar"}};

        List<String> res = formatParagraph(paragraph, 8);

        for(String c: res){
            System.out.println(c);
        }
    }
}
