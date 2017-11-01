package agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * filename: AopAgentTest
 * Description:
 * Author: ubuntu
 * Date: 10/26/17 10:16 AM
 */
public class AopAgentTest {
    private static final Logger logger = LoggerFactory.getLogger(AopAgentTest.class);
    public static void premain(String agentArgs, Instrumentation inst){
        if(agentArgs == null){
            agentArgs = "";
        }
        ClassFileTransformer transformer = new AopAgentTransformer();
        inst.addTransformer(transformer);
    }
}
