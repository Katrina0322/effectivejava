package consistenthashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * filename: HashFunction
 * Description:
 * Author: ubuntu
 * Date: 10/11/17 3:50 PM
 */
public class HashFunction {
    private MessageDigest md5 = null;

    public long hash(String key){
        if(md5 == null){
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("no md5 algorythm found");
            }
        }
        md5.reset();
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        long res = ((bKey[3] & 0xff) << 24) | ((bKey[2] & 0xff) << 16) | ((bKey[1] & 0xff) << 8) | (bKey[0] & 0xff);
        return res & 0xffffffffL;
    }
}
