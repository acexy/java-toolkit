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

    private ContentType contentType = null;
    private List<NameValuePair> nameValuePairs = null;
    private String bodyString = null;
    private byte[] byteData = null;
    private String charset = null;
    private File file = null;

    /**
     * 新增 from-urlencode / GET 请求参数
     */
    public AsyncParameters(String name, String value) {
        nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(name, value));
        this.contentType = ContentType.APPLICATION_FORM_URLENCODED.withCharset(Charset.utf8.charset);
    }

    /**
     * 新增 from-urlencode / GET 请求参数
     */
    public AsyncParameters(Map<String, String> params) {

        if (params == null || params.size() == 0) {
            return;
        }
        nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        this.contentType = ContentType.APPLICATION_FORM_URLENCODED.withCharset(Charset.utf8.charset);

    }

    /**
     * 向post body发送普通字符串
     *
     * @param json
     */
    public AsyncParameters(String json) {
        this.bodyString = json;
        this.contentType = ContentType.APPLICATION_JSON.withCharset(Charset.utf8.charset);
    }

    /**
     * 向post body发送普通字符串
     *
     * @param bodyString
     * @param contentType
     */
    public AsyncParameters(String bodyString, ContentType contentType) {
        this.bodyString = bodyString;
        this.contentType = contentType.withCharset(Charset.utf8.charset);
    }

    /**
     * 向post body发送字符串数据
     *
     * @param bodyString
     * @param contentType
     * @param charset
     */
    public AsyncParameters(String bodyString, String contentType, Charset... charset) {
        this.bodyString = bodyString;
        this.contentType = ContentType.create(contentType).withCharset(charset != null && charset.length > 0 ? charset[0].charset : Charset.utf8.charset);
    }


    /**
     * 设置二进制数据 默认 content-type: application/octet-stream
     *
     * @param byteData
     */
    public AsyncParameters(byte[] byteData) {
        this.byteData = byteData;
        this.contentType = ContentType.create(ContentType.APPLICATION_OCTET_STREAM.getMimeType()).withCharset(Charset.utf8.charset);

    }

    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public AsyncParameters(byte[] byteData, ContentType contentType) {
        this.byteData = byteData;
        this.contentType = contentType.withCharset(Charset.utf8.charset);
    }

    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public AsyncParameters(byte[] byteData, String contentType, Charset... charset) {
        this.byteData = byteData;
        this.contentType = ContentType.create(contentType).withCharset(charset != null && charset.length > 0 ? charset[0].charset : Charset.utf8.charset);
    }

    /**
     * 设置文件
     *
     * @param file
     * @param contentType
     */
    public AsyncParameters(File file, ContentType contentType) {
        this.file = file;
        this.contentType = ContentType.MULTIPART_FORM_DATA.withCharset(Charset.utf8.charset);
    }

    /**
     * 设置文件
     *
     * @param file
     * @param contentType
     * @param charset
     */
    public AsyncParameters(File file, String contentType, Charset... charset) {
        this.file = file;
        this.contentType = ContentType.create(contentType).withCharset(charset != null && charset.length > 0 ? charset[0].charset : Charset.utf8.charset);
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

    public AsyncParameters append(Map<String, String> values) {
        if (nameValuePairs == null) {
            nameValuePairs = new ArrayList<NameValuePair>();
        }

        for (Map.Entry<String, String> value : values.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(value.getKey(), value.getValue()));
        }
        return this;
    }

    public AsyncParameters setJsonBodyString(String json) {
        this.bodyString = json;
        this.contentType = ContentType.APPLICATION_JSON.withCharset(Charset.utf8.charset);
        return this;
    }

    public AsyncParameters setBodyString(String bodyString, ContentType contentType) {
        this.bodyString = bodyString;
        this.contentType = contentType;
        return this;
    }

    public List<NameValuePair> getNameValuePair() {
        return nameValuePairs;
    }

    public String getBodyString() {
        return bodyString;
    }

    public ContentType getContentType() {
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
