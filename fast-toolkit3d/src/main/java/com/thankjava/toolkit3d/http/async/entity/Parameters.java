package com.thankjava.toolkit3d.http.async.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Parameters {

    private List<NameValuePair> nameValuePairs = null;

    private String text = null;
    private String contentType = null;

    /**
     * 新增from-urlencode参数
     */
    public Parameters(String name, String value) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(name, value));
    }

    /**
     * 新增from-urlencode参数
     */
    public Parameters(Map<String, String> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return;
        }
        this.nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            this.nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 普通字符串数据
     *
     * @param text
     */
    public Parameters(String text) {
        this.text = text;
    }

    public Parameters(String text, RequestContentType contentType) {
        this.text = text;
        this.contentType = contentType.code;
    }

    public Parameters(byte[] byteArray) {

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
}
