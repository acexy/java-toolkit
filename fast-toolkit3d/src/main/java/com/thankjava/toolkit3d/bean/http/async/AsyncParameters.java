package com.thankjava.toolkit3d.bean.http.async;

import com.thankjava.toolkit.bean.common.Charset;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsyncParameters {

    private String contentType = null;
    private List<NameValuePair> nameValuePairs = null;
    private String text = null;
    private byte[] byteData = null;
    private String charset = null;
    private File file = null;

    /**
     * 新增 from-urlencode / GET 请求参数
     */
    public AsyncParameters(String name, String value) {
        nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(name, value));
        this.contentType = ContentType.APPLICATION_FORM_URLENCODED.toString();
    }

    /**
     * 新增 from-urlencode / GET 请求参数
     */
    public AsyncParameters(Map<String, String> parameters) {

        if (parameters == null || parameters.size() == 0) {
            return;
        }
        nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        this.contentType = ContentType.APPLICATION_FORM_URLENCODED.toString();

    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     */
    public AsyncParameters(String text) {
        this.text = text;
        this.contentType = ContentType.TEXT_PLAIN.toString();
    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     * @param contentType
     */
    public AsyncParameters(String text, ContentType contentType) {
        this.text = text;
        this.contentType = contentType.toString();
    }

    /**
     * 向post body发送字符串数据
     *
     * @param text
     * @param contentType
     * @param charset
     */
    public AsyncParameters(String text, String contentType, Charset... charset) {
        this.text = text;
        this.contentType = contentType;
        if (charset != null && charset.length > 0) {
            this.charset = charset[0].charset;
        }
    }


    /**
     * 设置二进制数据 默认 content-type: application/octet-stream
     *
     * @param byteData
     */
    public AsyncParameters(byte[] byteData, Charset... charset) {
        this.byteData = byteData;

        if (charset != null && charset.length > 0) {
            this.contentType = ContentType.create(ContentType.APPLICATION_OCTET_STREAM.getMimeType(), charset[0].charset).toString();
        } else {
            this.contentType = ContentType.create(ContentType.APPLICATION_OCTET_STREAM.getMimeType()).toString();
        }

    }

    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public AsyncParameters(byte[] byteData, ContentType contentType) {
        this.byteData = byteData;
        this.contentType = contentType.toString();
    }

    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public AsyncParameters(byte[] byteData, String contentType, Charset... charset) {
        this.byteData = byteData;
        this.contentType = contentType;
        if (charset != null && charset.length > 0) {
            this.charset = charset[0].charset;
        }
    }

    /**
     * 设置文件
     * @param file
     * @param contentType
     */
    public AsyncParameters(File file, ContentType contentType) {
        this.file = file;
        this.contentType = contentType.toString();
    }

    public AsyncParameters(File file, String contentType, Charset... charset) {
        this.file = file;
        this.contentType = contentType;
        if (charset != null && charset.length > 0) {
            this.charset = charset[0].charset;
        }
    }

    /**
     * 追加 from-urlencode / GET 参数
     *
     * @param key
     * @param value
     * @return
     */
    public AsyncParameters append(String key, String value) {
        if (nameValuePairs == null) {
            nameValuePairs = new ArrayList<NameValuePair>();
        }
        nameValuePairs.add(new BasicNameValuePair(key, value));
        return this;
    }

    public AsyncParameters append(String text) {
        this.text = text;
        this.contentType = ContentType.TEXT_PLAIN.toString();
        return this;
    }

    public AsyncParameters append(String text, ContentType contentType) {
        this.text = text;
        this.contentType = contentType.toString();
        return this;
    }

    public List<NameValuePair> getNameValuePair() {
        return nameValuePairs;
    }

    public String getText() {
        return text;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getByteData() {
        return byteData;
    }

    public String getCharset() {
        return charset;
    }

    public File getFile() {
        return file;
    }
}
