package com.oneapm.classpool;

import javassist.*;

/**
 * @filename: Test
 * @Description:
 * @Author: ubuntu
 * @Date: 12/14/16 6:55 PM
 */
public class Test {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass hello = pool.get("com.oneapm.classpool.Hello");
        CtMethod m = hello.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = hello.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
