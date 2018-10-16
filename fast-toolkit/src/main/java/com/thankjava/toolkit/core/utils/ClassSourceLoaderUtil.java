package com.thankjava.toolkit.core.utils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;


/**
 * Class内部资源加载器
 * <p>Function: ClassSourceLoaderUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年4月18日 上午10:33:00
 */
public class ClassSourceLoaderUtil {

    private static ClassLoaderWrapper classLoaderWrapper = new ClassSourceLoaderUtil.ClassLoaderWrapper();

    /**
     * 加载内部指定资源
     * <p>Function: getResourceAsReader</p>
     * <p>Description: </p>
     *
     * @param resource
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月9日 下午5:06:34
     * @version 1.0
     */
    public static Reader getResourceAsReader(String resource) {
        InputStream in = classLoaderWrapper.getResourceAsStream(resource);
        if (in == null) {
            return null;
        }
        return new InputStreamReader(in);
    }


    /**
     * 加载内部指定资源
     * <p>Function: getResourceAsReader</p>
     * <p>Description: </p>
     *
     * @param resource
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月9日 下午5:06:34
     * @version 1.0
     */
    public static InputStream getResourceAsInputStream(String resource) {
        InputStream in = classLoaderWrapper.getResourceAsStream(resource);
        if (in == null) {
            return null;
        }
        return in;
    }


    static class ClassLoaderWrapper {

        final ClassLoader defaultClassLoader = null;
        ClassLoader systemClassLoader;

        ClassLoaderWrapper() {
            try {
                systemClassLoader = ClassLoader.getSystemClassLoader();
            } catch (SecurityException ignored) {

            }
        }

        public URL getResourceAsURL(String resource) {
            return getResourceAsURL(resource, getClassLoaders(null));
        }

        public URL getResourceAsURL(String resource, ClassLoader classLoader) {
            return getResourceAsURL(resource, getClassLoaders(classLoader));
        }

        public InputStream getResourceAsStream(String resource) {
            return getResourceAsStream(resource, getClassLoaders(null));
        }

        public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
            return getResourceAsStream(resource, getClassLoaders(classLoader));
        }

        public Class<?> classForName(String name) throws ClassNotFoundException {
            return classForName(name, getClassLoaders(null));
        }

        public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
            return classForName(name, getClassLoaders(classLoader));
        }

        InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
            for (ClassLoader cl : classLoader) {
                if (null != cl) {

                    InputStream returnValue = cl.getResourceAsStream(resource);
                    if (null == returnValue) {
                        returnValue = cl.getResourceAsStream("/" + resource);
                    }
                    if (null != returnValue) {
                        return returnValue;
                    }
                }
            }
            return null;
        }

        URL getResourceAsURL(String resource, ClassLoader[] classLoader) {

            URL url;

            for (ClassLoader cl : classLoader) {

                if (null != cl) {

                    url = cl.getResource(resource);
                    if (null == url) {
                        url = cl.getResource(File.separator + resource);
                    }
                    if (null != url) {
                        return url;
                    }

                }

            }
            return null;
        }

        Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {

            for (ClassLoader cl : classLoader) {

                if (null != cl) {

                    try {

                        Class<?> c = Class.forName(name, true, cl);

                        if (null != c) {
                            return c;
                        }

                    } catch (ClassNotFoundException e) {
                    }

                }

            }
            throw new ClassNotFoundException("Cannot find class: " + name);
        }

        ClassLoader[] getClassLoaders(ClassLoader classLoader) {
            return new ClassLoader[]{
                    classLoader,
                    defaultClassLoader,
                    Thread.currentThread().getContextClassLoader(),
                    getClass().getClassLoader(),
                    systemClassLoader
            };
        }

    }
}
