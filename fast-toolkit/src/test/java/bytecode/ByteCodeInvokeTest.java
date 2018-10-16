package bytecode;

import com.thankjava.toolkit.core.bytecode.ByteCodeClassLoader;
import com.thankjava.toolkit.core.reflect.ReflectHelper;

import java.lang.reflect.Method;

/**
 * @Author: acexy@thankjava.com
 * 2018/6/22
 * @Description:
 **/
public class ByteCodeInvokeTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ByteCodeClassLoader byteCodeClassLoader = ByteCodeClassLoader.getLoader();
        byteCodeClassLoader.loadClassFromByteCodes(ByteCodeCompilerTest.compile());
        Class simpleClass = byteCodeClassLoader.findClass("bytecode.SimpleClass");
        Object instance = simpleClass.newInstance();
        Method method = ReflectHelper.getMethod(instance.getClass(), "main");
        ReflectHelper.invokeMethod(instance, method);
    }
}
