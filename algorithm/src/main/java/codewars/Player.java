package codewars;

import java.util.*;

/**
 * Created by ubuntu on 2/22/16.
 */
public class Player {
    private int cakes;

    public Player(int cakes) {
        this.cakes = cakes;
    }


    public static void main(String[] args) {

    }

    public static String orderWeight(String strng) {
        // your code
        String[] orig = strng.split(" ");
        StringBuffer sb = new StringBuffer();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (String s : orig) {
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum += Integer.parseInt(String.valueOf(s.charAt(i)));
            }

            map.put(Integer.parseInt(s), sum);
        }
        if (map != null && !map.isEmpty()) {
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());


            Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
                public int compare(Map.Entry<Integer, Integer> entry1,
                                   Map.Entry<Integer, Integer> entry2) {
                    return entry1.getValue() != entry2.getValue() ? entry2.getValue() - entry1.getValue() : String.valueOf(entry1.getValue()).compareTo(String.valueOf(entry2.getValue()));
                }
            });
            Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
            while (iter.hasNext()) {
                sb.append(String.valueOf(iter.next().getKey())).append(" ");
            }
        }
        return sb.toString().trim();


    }

    // Decide who move first - player or opponent (true if player)
    public boolean firstMove(int cakes) {
//       if(cakes == 1 || (cakes - 2) % 4 == 0) return false;
//
//        return true;
        return cakes > 2 && cakes % 4 != 2;
    }

    // Decide your next move
    public int move(int cakes, int last) {
        // I'm not greedy
//        int m = 0;
//        if(cakes < 2 && last == 1){
//            throw new IllegalArgumentException();
//
//        }else if(cakes == 3 ){
//            return last==2?1:2;
//        }else if(cakes == 4){
//            return last==2?3:2;
//        }else if((cakes - 4) % 4 == 0 || last == 2){
//            return 3;
//        }else if(cakes >= 3 && (cakes - 3)%4 == 0){
//            return 1;
//        }else if(cakes >= 4 && (cakes - 4)%4 == 0) {
//            return 2;
//        }else if(cakes >= 5 && (cakes - 5)%4 == 0) {
//            return 3;
//        }
//        return m;
        return cakes % 4 < 3 ? 3 : 1;
    }


}
