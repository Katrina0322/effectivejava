package org.microframe.rpc.chap20;


/**
 * used to
 * Created by tianjin on 8/2/16.
 */
public class Testable {
    public void execute() {
        System.out.println("Executing");
    }

    @Test
    void testExecute() {
        execute();
    }
}
