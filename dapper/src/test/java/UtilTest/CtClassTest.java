package UtilTest;

import com.google.common.base.Preconditions;
import com.oneapm.test.House;
import com.oneapm.test.Person;
import javassist.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//        System.out.println(ctClass.getClassFile().getName());
//        for(CtConstructor constructor:ctClass.getDeclaredConstructors()){
//            System.out.println(constructor.getMethodInfo().getDescriptor());
//        }
        testCtClass(ctClass);


    }

    public static void testCtMethod(CtClass ctClass){
        CtMethod[] ctMethods = ctClass.getDeclaredMethods();
        for(CtMethod ctMethod:ctMethods){
            System.out.println(ctMethod.getSignature());
        }
    }

    public static void testCtClass(CtClass ctClass){
        try {
            CtField newField = CtField.make("private " + String.class.getName() + " house;", ctClass);
            ctClass.addField(newField);
            CtMethod getName = CtNewMethod.getter("getHouse", newField);

            CtMethod setName = CtNewMethod.setter("setHouse", newField);
            getName.setModifiers(Modifier.PUBLIC);
            setName.setModifiers(Modifier.PUBLIC);
            ctClass.addMethod(getName);
            ctClass.addMethod(setName);
            ctClass.setAttribute("house", "ccc".getBytes());

        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        try {
            Object object = ctClass.toClass().newInstance();

            System.out.println();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }


//        House house = new House("aa", 100, 130.22);
////        String[] bb = new String[]{"aaa"};
//        List<House> houses = new ArrayList<>();
//        houses.add(house);
//        Person person = new Person(25, "tuhao", new String[]{"aaa"});

    }
}
