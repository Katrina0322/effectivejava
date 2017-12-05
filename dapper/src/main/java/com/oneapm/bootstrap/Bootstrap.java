package com.oneapm.bootstrap;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * filename: Bootstrap
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 2:39 PM
 */
public class Bootstrap {
    private static final LoadState STATE = new LoadState();

    public static void premain(String agentArgs, Instrumentation instrumentation){
        if (agentArgs == null) {
            agentArgs = "";
        }
        final boolean success = STATE.start();
        Map<String, String> agentArgsMap = argsToMap(agentArgs);

        final ClassPathResolver classPathResolver = new AgentDirBaseClassPathResolver();
        BootstrapJarFile bootstrapJarFile = classPathResolver.getBootstrapJarFile();
        appendToBootstrapClassLoader(instrumentation, bootstrapJarFile);

        ApmStarter starter = new ApmStarter(agentArgsMap, bootstrapJarFile, classPathResolver, instrumentation);


    }

    private static void appendToBootstrapClassLoader(Instrumentation instrumentation, BootstrapJarFile agentJarFile) {
        List<JarFile> jarFileList = agentJarFile.getJarFileList();
        for (JarFile jarFile : jarFileList) {
            instrumentation.appendToBootstrapClassLoaderSearch(jarFile);
        }
    }

    private static Map<String,String> argsToMap(String agentArgs) {
        return ArgsParser.parse(agentArgs);
    }
}
