package bytecode;

import com.thankjava.toolkit.bytecode.ByteCodeClassLoader;

import java.util.Map;

/**
 * @Author: acexy@thankjava.com
 * 2018/6/22
 * @Description:
 **/
public class ByteCodeClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        Map<String, byte[]> byteCodes = ByteCodeCompilerTest.compile();

        // 获取自定义字节码加载器
        ByteCodeClassLoader byteCodeClassLoader = ByteCodeClassLoader.getLoader();

        // 从自定义加载器中利用双亲委派机制获取bytecode.TestInvoke类类型
        Class classA = byteCodeClassLoader.findClass("bytecode.SimpleClass");

        System.out.println("classA的加载器是: " + classA.getClassLoader());

        // 从当前自定义加载器的加载器中获取bytecode.TestInvoke类类型 此处由于ByteCodeClassLoader是Application加载的
        // 所以其实得到的是Application加载测试代码里面的TestInvoke类
        Class classB = byteCodeClassLoader.findClassFromParent("bytecode.SimpleClass");
        System.out.println("classB的加载器是: " + classB.getClassLoader());

        System.out.println(classA == classB); // true

        // 将bytecode.TestInvoke的字节码加载到ByteCodeClassLoader加载器中
        byteCodeClassLoader.loadClassFromByteCodes(byteCodes);


        // 由于byteCodeClassLoader加载了bytecode.SimpleClass，所以得到的是由ByteCodeCompiler所产生的字节码编译的类类型
        Class classC = byteCodeClassLoader.findClass("bytecode.SimpleClass");

        System.out.println("classC的加载器是: " + classC.getClassLoader());


        // classC 与 classB 是相同字节码，不同加载器的类类型 在java中，他们不相同
        System.out.println(classC == classB); // false

    }
}
