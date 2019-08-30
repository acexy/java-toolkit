package com.thankjava.toolkit.core.bytecode;

import com.thankjava.toolkit.bean.common.Charset;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.Map;

/**
 * 用于将java源码生成字节码编译器
 */
public final class ByteCodeCompiler {

    private ByteCodeCompiler() {
    }

    /**
     * 将java源代码生成字节码
     *
     * @param sourceCode
     * @return
     */
    public static Map<String, byte[]> compile(String sourceCode, Charset... charset) {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 3 个参数默认 则使用当前平台的相关jvm环境的编译器
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(
                null,
                null,
                java.nio.charset.Charset.forName((charset == null || charset.length == 0) ? Charset.utf8.charset : charset[0].charset)
        );

        ByteCodeJavaFileManager customJavaFileManager = new ByteCodeJavaFileManager(sourceCode, standardJavaFileManager);

        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null,
                customJavaFileManager,
                null,
                null,
                null,
                Arrays.asList(customJavaFileManager.new JavaSourceFileObject())
        );

        if (compilationTask.call()) {
            return customJavaFileManager.getByteCodes();
        } else {
            return null;
        }
    }
}
