package chap5;

import java.math.BigInteger;

/**
 * Created by spark on 11/12/16.
 */
public class ExpensiveFunction implements Computable<String,BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
