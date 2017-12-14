package buffer;

import java.io.IOException;

/**
 * filename: InputBuffer
 * Description:
 * Author: ubuntu
 * Date: 12/13/17 10:00 AM
 */
public interface InputBuffer {
    int doRead(byte[] chunk) throws IOException;
}
