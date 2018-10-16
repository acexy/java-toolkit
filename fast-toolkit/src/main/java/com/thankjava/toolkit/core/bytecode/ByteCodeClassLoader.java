package com.thankjava.toolkit.core.bytecode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义的ClassLoader 类加载器，用于处理直接加载字节码byte数组
 **/
public final class ByteCodeClassLoader extends ClassLoader {

    // 缓存装入的字节码所转换的Class对象
    private final Map<String, Class<?>> byteCodesClass = new ConcurrentHashMap<>();

    private final static ByteCodeClassLoader instance = new ByteCodeClassLoader();
    private final static ClassLoader parentClassLoader = instance.getClass().getClassLoader();

    private ByteCodeClassLoader() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static ByteCodeClassLoader getLoader() {
        return instance;
    }

    /**
     * 使用双亲委派机制获取Class对象
     *
     * @param name Class类路径
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = byteCodesClass.get(name);
        if (clazz == null) {
            return super.loadClass(name, false);
        }
        return clazz;
    }

    /**
     * 仅从当前自定义加载器的加载器中获取类类型对象
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> findClassFromParent(String name) throws ClassNotFoundException {
        return parentClassLoader.loadClass(name);
    }

    /**
     * 让当前ClassLoader装载指定的Class字节码
     *
     * @param inputByteCodes
     */
    public void loadClassFromByteCodes(Map<String, byte[]> inputByteCodes) {

        if (inputByteCodes == null || inputByteCodes.isEmpty()) {
            throw new IllegalArgumentException("byteCodes can not be null");
        }

        for (Map.Entry<String, byte[]> byteCode : inputByteCodes.entrySet()) {
            byteCodesClass.put(byteCode.getKey(), defineClass(null, byteCode.getValue(), 0, byteCode.getValue().length));
        }
    }

}
