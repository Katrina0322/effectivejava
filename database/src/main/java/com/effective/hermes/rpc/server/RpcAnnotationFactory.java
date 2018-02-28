package com.effective.hermes.rpc.server;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @filename: RpcAnnotationFactory
 * @Description:
 * @Author: ubuntu
 * @Date: 12/15/16 3:05 PM
 */
public class RpcAnnotationFactory {
    public static Map<Annotation,Object> getBeansWithAnnotation(Class<? extends Annotation> annotation,String packageName){
        Map<Annotation,Object> handlerMap = new ConcurrentHashMap<>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(annotation, packageName, filePath,
                            handlerMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return handlerMap;
    }

    private static void findAndAddClassesInPackageByFile(Class<? extends Annotation> annotation, String packageName, String packagePath, Map<Annotation, Object> handlerMap){
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".class");
            }
        });

        for (File file : dirfiles){
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(annotation,packageName + "."
                                + file.getName(), file.getAbsolutePath(),
                        handlerMap);
            }else{
                String className = file.getName().substring(0, file.getName().length() - 6);
                Object serviceBean = null;
                try {
                    Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
                    Annotation anno = clazz.getAnnotation(annotation);
                    if(anno != null){
                        serviceBean = clazz.newInstance();
                        handlerMap.put(anno,serviceBean);
                    }

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
