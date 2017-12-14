package buffer;

import java.io.IOException;

/**
 * filename: ClearFilter
 * Description:
 * Author: ubuntu
 * Date: 12/13/17 10:23 AM
 */
public class ClearFilter implements InputFilter {
    protected InputBuffer inputBuffer;
    @Override
    public void setBuffer(InputBuffer buffer) {
        this.inputBuffer = buffer;
    }

    public InputBuffer getInputBuffer() {
        return inputBuffer;
    }

    @Override
    public int doRead(byte[] chunk) throws IOException {
        int i = inputBuffer.doRead(chunk);
        if(i == -1){
            return -1;
        }
        for(int j = 0; j < chunk.length; j++){
            if(chunk[j] == 'a'){
                chunk[j] = 'f';
            }
        }
        return i;
    }
}
