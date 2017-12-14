package buffer;

import com.sun.org.apache.bcel.internal.generic.FADD;

import java.io.IOException;

/**
 * filename: InternalInputBuffer
 * Description:
 * Author: ubuntu
 * Date: 12/13/17 10:01 AM
 */
public class InternalInputBuffer implements InputBuffer {
    boolean isEnd = false;
    byte[] buf = new byte[4];
    protected int lastActiveFilter = -1;
    protected InputFilter[] activeFilters = new InputFilter[2];
    InputBuffer inputBuffer = new InputStreamInputBuffer();

    public void addFilter(InputFilter filter){
        if(lastActiveFilter == -1){
            filter.setBuffer(inputBuffer);
        }else {
            for(int i = 0; i <= lastActiveFilter; i++){
                if(activeFilters[i] == filter){
                    return;
                }
            }
            filter.setBuffer(activeFilters[lastActiveFilter]);
        }
        activeFilters[++lastActiveFilter] = filter;
    }

    @Override
    public int doRead(byte[] chunk) throws IOException {
        if(lastActiveFilter == -1){
            return inputBuffer.doRead(chunk);
        }else {
            return activeFilters[lastActiveFilter].doRead(chunk);
        }
    }

    public class InputStreamInputBuffer implements InputBuffer {
        @Override
        public int doRead(byte[] chunk) throws IOException {
            if (!isEnd) {
                buf[0] = 'a';
                buf[1] = 'b';
                buf[2] = 'a';
                buf[3] = 'd';
                System.arraycopy(buf, 0, chunk, 0, 4);
                isEnd = true;
                return chunk.length;
            } else{
                return -1;
            }
        }
    }
}
