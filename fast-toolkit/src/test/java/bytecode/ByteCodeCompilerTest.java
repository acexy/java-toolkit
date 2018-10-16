package bytecode;

import com.thankjava.toolkit.core.bytecode.ByteCodeCompiler;

import java.util.Map;

/**
 * @Author:acexy@thankjava.com 2018/6/22
 * @Description:测试字节码生成
 **/
public class ByteCodeCompilerTest {

    static String sourceCode = "package bytecode;\n" +
            "public class SimpleClass {\n" +
            "\n" +
            "    public void main() {\n" +
            "        System.out.println(\"java code\");\n" +
            "        new InnerClass().print();\n" +
            "    }\n" +
            "\n" +
            "    class InnerClass {\n" +
            "\n" +
            "        public void print() {\n" +
            "            System.out.println(\"inner class code\");\n" +
            "        }\n" +
            "    }\n" +
            "}\n";


    public static void main(String[] args) {
        Map<String, byte[]> byteCodes = compile();
        // 编译完毕后的Class类名
        System.out.println(byteCodes.keySet().toString());
    }

    public static Map<String, byte[]> compile() {
        return ByteCodeCompiler.compile(sourceCode);
    }
}
