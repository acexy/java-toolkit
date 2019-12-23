package com.thankjava.toolkit.core.bytecode;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ByteCodeJavaFileManager extends ForwardingJavaFileManager {

    private Map<String, byte[]> byteCodes = new HashMap<>();

    private URI javaSourceURI;
    private String sourceCode;

    private static final String CLASS_URI_PREFIX = "mfm:///";

    private static final String SP = "/";

    // 包名匹配正则
    private static final String PACKAGE_REG = "package\\s+(\\w+(.\\w+)*)\\s*;";

    // 类名匹配正则
    private static final String CLASS_NAME_REG = "[class|enum]\\s+(\\w+)\\s*\\{";

    private static final String JAVA_SOURCE_SUFFIX = ".java";

    protected ByteCodeJavaFileManager(String sourceCode, JavaFileManager fileManager) {
        super(fileManager);
        this.sourceCode = sourceCode;
        String javaName = resolveJavaName(sourceCode);
        javaSourceURI = createJavaURI(javaName);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        return new JavaClassFileObject(
                className
        );
    }

    public Map<String, byte[]> getByteCodes() {
        return byteCodes;
    }

    URI createJavaURI(String javaName) {
        // 包名解析
        StringBuffer uri = new StringBuffer(CLASS_URI_PREFIX);
        uri.append(javaName.replace(".", SP));
        uri.append(JAVA_SOURCE_SUFFIX);
        return URI.create(uri.toString());
    }

    String resolveJavaName(String sourceCode) {
        StringBuffer uri = new StringBuffer();
        Pattern pattern = Pattern.compile(PACKAGE_REG);
        Matcher matcher = pattern.matcher(sourceCode);
        boolean includePackage = false;
        if (matcher.find()) {
            includePackage = true;
            uri.append(matcher.group(1));
        }
        // 主JAVA名文件解析
        pattern = Pattern.compile(CLASS_NAME_REG);
        matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            if (includePackage) {
                uri.append(".");
            }
            uri.append(matcher.group(1));
        }
        return uri.toString();
    }


    /**
     * JavaSource 文件对象
     */
    public class JavaSourceFileObject extends SimpleJavaFileObject {

        protected JavaSourceFileObject() {
            super(javaSourceURI, JavaFileObject.Kind.SOURCE);
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return CharBuffer.wrap(sourceCode);
        }
    }

    /**
     * JavaClass 文件对象
     */
    class JavaClassFileObject extends SimpleJavaFileObject {

        private String className;

        protected JavaClassFileObject(String className) {
            super(URI.create(className), JavaFileObject.Kind.CLASS);
            this.className = className;
        }

        @Override
        public OutputStream openOutputStream() {
            return new FilterOutputStream(new ByteArrayOutputStream()) {
                @Override
                public void close() throws IOException {
                    ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
                    byteCodes.put(className, bos.toByteArray());
                    bos.close();
                    out.close();
                }
            };
        }
    }

}
