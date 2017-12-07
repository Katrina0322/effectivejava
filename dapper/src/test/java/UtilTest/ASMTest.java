package UtilTest;

import com.oneapm.test.Man;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.io.IOException;

/**
 * filename: ASMTest
 * Description:
 * Author: ubuntu
 * Date: 12/7/17 2:29 PM
 */
public class ASMTest {
    public static void main(String[] args) {

    }

    public static void testClassNode() throws IOException {

        ClassReader classReader = new ClassReader(Man.class.getName());
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

    }
}
