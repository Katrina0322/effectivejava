package agent;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * filename: AopAgentTransformer
 * Description:
 * Author: ubuntu
 * Date: 10/31/17 3:04 PM
 */
public class AopAgentTransformer implements ClassFileTransformer {
    private static final Logger logger = LoggerFactory.getLogger(AopAgentTransformer.class);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        logger.debug("Transforming " + className);
        CtClass cl = null;
        ClassPool pool = ClassPool.getDefault();
        try {
            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
//            CodeConverter converter = new CodeConverter();
            if(!cl.isInterface()){
                CtMethod[] methods = cl.getDeclaredMethods();
                for(int i = 0; i < methods.length; i++){
                    if(!methods[i].isEmpty()){
                        aopInsertMethod(methods[i]);
                    }
                }
                transformed = cl.toBytecode();
             }
        } catch (IOException e) {
            logger.error("CtClass创建失败");
        } catch (CannotCompileException e) {
            logger.error("class字节码无法编译");
        } finally {
            if(cl != null){
                cl.detach();
            }
        }
        return transformed;
    }

    private void aopInsertMethod(CtMethod method) throws CannotCompileException {
        method.instrument(new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                m.replace("{long stime = System.currentTimeMillis();$_ = $proceed($$);System.out.println(\""
                        + m.getClassName() + "." + m.getMethodName()
                        + " cost:\" + (System.currentTimeMillis() - stime) + \" ms\");}");
                super.edit(m);
            }
        });
    }
}
