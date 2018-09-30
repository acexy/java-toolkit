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
        nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     */
    public Parameters(String text) {
        this.text = text;
    }

    /**
     * 向post body发送普通字符串
     *
     * @param text
     * @param contentType
     */
    public Parameters(String text, RequestContentType contentType) {
        this.text = text;
        this.contentType = contentType.code;
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
