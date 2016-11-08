package leetcode.simple;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class Solution6 {
    public String convert(String s, int numRows) {
        int len = s.length();
        if(numRows < 2 || len == 1){
            return s;
        }

        int cycle = 2*(numRows - 1);
        String res = "";
        for(int i = 0;i < numRows;i++){
            for(int j = i;j < len;j+=cycle){
                res += s.charAt(j);

                if(i > 0 && i < numRows - 1){
                    int zIndex = j + cycle - 2*i;
                    if(zIndex < len){
                        res += s.charAt(zIndex);
                    }
                }
            }
        }
        return res;
    }
}
