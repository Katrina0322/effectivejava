package UtilTest;

import com.google.common.base.Preconditions;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

/**
 * filename: CtClassTest
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 5:24 PM
 */
public class CtClassTest {
    public static void main(String[] args) {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = classPool.get("com.oneapm.test.Person");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        Preconditions.checkNotNull(ctClass, "com.oneapm.test.Person不存在");
        System.out.println(ctClass.getClassFile().getName());
        for(CtConstructor constructor:ctClass.getDeclaredConstructors()){
            System.out.println(constructor.getMethodInfo().getDescriptor());
        }


    }
}
