package com.thankjava.toolkit.core.bytecode;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : acexy@thankjava.com
 * @desc : 扫描指定package下的所有工程class
 * @date : 2020/4/23
 **/
public class CustomizeClassScanner {

    /**
     * 从包package中获取所有的Class
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> scan(String packageName, String charset) {

        Set<Class<?>> classes = new HashSet<>();
        String packagePath = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), charset);
                    findAndAddClassesInPackageByFile(packageName, filePath, true, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        for (File file : files) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
