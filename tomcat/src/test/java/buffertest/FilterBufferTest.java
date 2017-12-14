package buffertest;

import buffer.ClearFilter;
import buffer.InputFilter;
import buffer.InternalInputBuffer;
import buffer.UpperFilter;

import java.io.IOException;

/**
 * filename: FilterBufferTest
 * Description:
 * Author: ubuntu
 * Date: 12/13/17 10:30 AM
 */
public class FilterBufferTest {
    public static void main(String[] args) {
        InternalInputBuffer internalInputBuffer = new InternalInputBuffer();
        InputFilter cleanFilter = new ClearFilter();
        InputFilter upperFilter = new UpperFilter();
        internalInputBuffer.addFilter(cleanFilter);
        internalInputBuffer.addFilter(upperFilter);
        byte[] chunk = new byte[4];
        try {
            int i = 0;
            while (i != -1) {
                i = internalInputBuffer.doRead(chunk);
//                if (i == -1) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(chunk));
    }
}
