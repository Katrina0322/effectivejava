package com.oneapm.bootstrap;

import java.net.URL;
import java.util.List;

/**
 * filename: AgentDirBaseClassPathResolver
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 3:08 PM
 */
public class AgentDirBaseClassPathResolver implements ClassPathResolver {
    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public BootstrapJarFile getBootstrapJarFile() {
        return null;
    }

    @Override
    public String getPinpointCommonsJar() {
        return null;
    }

    @Override
    public String getBootStrapCoreJar() {
        return null;
    }

    @Override
    public String getBootStrapCoreOptionalJar() {
        return null;
    }

    @Override
    public String getAgentJarName() {
        return null;
    }

    @Override
    public String getAgentJarFullPath() {
        return null;
    }

    @Override
    public String getAgentLibPath() {
        return null;
    }

    @Override
    public String getAgentLogFilePath() {
        return null;
    }

    @Override
    public String getAgentPluginPath() {
        return null;
    }

    @Override
    public List<URL> resolveLib() {
        return null;
    }

    @Override
    public URL[] resolvePlugins() {
        return new URL[0];
    }

    @Override
    public String getAgentDirPath() {
        return null;
    }

    @Override
    public String getAgentConfigPath() {
        return null;
    }
}
