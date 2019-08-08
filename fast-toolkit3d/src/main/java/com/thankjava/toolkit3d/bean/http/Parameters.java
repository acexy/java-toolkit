package com.thankjava.toolkit3d.bean.http;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thankjava.toolkit.bean.common.Charset;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Parameters {

    private String contentType = null;
    private List<NameValuePair> nameValuePairs = null;
    private String text = null;
    private byte[] byteData = null;
    private String contentEncoding = null;
    private File file = null;

    /**
     * 新增from-urlencode参数
     */
    public Parameters(String name, String value) {
        nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(name, value));
        this.contentType = RequestContentType.APPLICATION_FORM_URLENCODED.code;
    }

    /**
     * 新增from-urlencode参数
     */
    public Parameters(Map<String, String> parameters) {

        if (parameters == null || parameters.size() == 0) {
            return;
        }
        nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        this.contentType = RequestContentType.APPLICATION_FORM_URLENCODED.code;

    }

    /**
     * 追加 from-urlencode参数
     *
     * @param key
     * @param value
     * @return
     */
    public Parameters append(String key, String value) {
        if (nameValuePairs == null) {
            nameValuePairs = new ArrayList<NameValuePair>();
        }
        nameValuePairs.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     */
    public Parameters(String text) {
        this.text = text;
        this.contentType = RequestContentType.TEXT_PLAIN.code;
    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     * @param contentType
     */
    public Parameters(String text, RequestContentType contentType, Charset... contentEncoding) {

        this.text = text;
        this.contentType = contentType.code;

        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }

    }

    /**
     * 向post body发送字符串数据
     *
     * @param text
     * @param contentType
     * @param contentEncoding
     */
    public Parameters(String text, String contentType, Charset... contentEncoding) {
        this.text = text;
        this.contentType = contentType;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
    }


    /**
     * 设置二进制数据 默认 content-type: application/octet-stream
     *
     * @param byteData
     */
    public Parameters(byte[] byteData, Charset... contentEncoding) {
        this.byteData = byteData;
        this.contentType = RequestContentType.APPLICATION_OCTET_STREAM.code;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
    }

    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public Parameters(byte[] byteData, RequestContentType contentType, Charset... contentEncoding) {
        this.byteData = byteData;
        this.contentType = contentType.code;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
    }


    /**
     * 设置二进制数据
     *
     * @param byteData
     * @param contentType
     */
    public Parameters(byte[] byteData, String contentType, Charset... contentEncoding) {
        this.byteData = byteData;
        this.contentType = contentType;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
    }

    public Parameters(File file, RequestContentType contentType, Charset... contentEncoding) {
        this.file = file;
        this.contentType = contentType.code;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
    }

    public Parameters(File file, String contentType, Charset... contentEncoding) {
        this.file = file;
        this.contentType = contentType;
        if (contentEncoding != null && contentEncoding.length > 0) {
            this.contentEncoding = contentEncoding[0].charset;
        }
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

    public String getContentEncoding() {
        return contentEncoding;
    }

    public File getFile() {
        return file;
    }
}
