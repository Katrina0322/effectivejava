import alert.CacheData;
import alert.Rule;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * filename: Analysis
 * Description:
 * Author: ubuntu
 * Date: 12/28/17 2:11 PM
 */
public class Analysis {
    public static void main(String[] args) {
        CacheData cacheData = new CacheData();
        try {
            cacheData.init(Executors.newFixedThreadPool(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Rule> rules = cacheData.getRules();
        for(Rule rule:rules){

        }
    }
}
