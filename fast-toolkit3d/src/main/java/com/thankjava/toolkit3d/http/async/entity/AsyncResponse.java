package com.thankjava.toolkit3d.http.async.entity;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import com.thankjava.toolkit3d.http.async.consts.Charset;

public class AsyncResponse {

    /**
     * 返回http状态码
     */
    private int httpCode;
    /**
     * 返回的头部信息
     */
    private Headers header;

    /**
     * 返回的cookies信息
     */
    private Cookies cookies;

    /**
     * 返回的内容信息
     */
    private String dataString;
    private boolean setDataString = false;

    /**
     * 返回的流 byte数组
     */
    private byte[] dataByteArray;
    private boolean setDataByteArray = false;

    /**
     * 原始的 httpEntity 数据
     */
    private HttpEntity httpEntity;

    private Charset charset;

    public Headers getHeader() {
        return header;
    }

    public void setHeader(Headers header) {
        this.header = header;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getDataString() {
        try {
            if (!setDataString) {
                dataString = EntityUtils.toString(httpEntity, charset.charset);
                setDataString = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dataString;
    }

    public byte[] getDataByteArray() {
        try {
            if (!setDataByteArray) {
                dataByteArray = EntityUtils.toByteArray(httpEntity);
                setDataByteArray = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return dataByteArray;
    }

    public boolean isEmptyDataString() {
        if (getDataString() == null || dataString.length() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("httpCode: [");
        buffer.append(String.valueOf(httpCode));
        buffer.append("] header: [");
        buffer.append(header == null ? "" : header.toString());
        buffer.append("] cookies: [");
        buffer.append(cookies == null ? "" : cookies.toString());
        buffer.append("] dataString: [");
        buffer.append(getDataString() == null ? "" : getDataString());
        buffer.append("]");
        return buffer.toString();
    }
}
