package com.oneapm.bootstrap;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.Map;

/**
 * filename: ApmStarter
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 3:14 PM
 */
public class ApmStarter {
    private final Map<String, String> agentArgs;
    private final BootstrapJarFile bootstrapJarFile;
    private final ClassPathResolver classPathResolver;
    private final Instrumentation instrumentation;

    public ApmStarter(Map<String, String> agentArgs, BootstrapJarFile bootstrapJarFile, ClassPathResolver classPathResolver, Instrumentation instrumentation) {
        this.agentArgs = agentArgs;
        this.bootstrapJarFile = bootstrapJarFile;
        this.classPathResolver = classPathResolver;
        this.instrumentation = instrumentation;
    }


    public boolean start(){
        URL[] pluginJars = classPathResolver.resolvePlugins();
        return false;
    }
}
