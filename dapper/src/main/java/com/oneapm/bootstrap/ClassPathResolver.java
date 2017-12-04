package com.oneapm.bootstrap;

import java.net.URL;
import java.util.List;

/**
 * filename: ClassPathResolver
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 3:06 PM
 */
public interface ClassPathResolver {
    boolean verify();

    BootstrapJarFile getBootstrapJarFile();

    String getPinpointCommonsJar();

    String getBootStrapCoreJar();

    String getBootStrapCoreOptionalJar();

    String getAgentJarName();

    String getAgentJarFullPath();

    String getAgentLibPath();

    String getAgentLogFilePath();

    String getAgentPluginPath();

    URL[] resolvePlugins();

    List<URL> resolveLib();

    String getAgentDirPath();

    String getAgentConfigPath();
}
