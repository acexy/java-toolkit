package com.thankjava.toolkit3d.bean.http;

import com.thankjava.toolkit.bean.common.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AsyncResponse {

    public AsyncResponse() {
    }

    public AsyncResponse(Throwable e) {
        exception = e;
    }

    /**
     * 如果请求异常将返回异常信息
     */
    private Throwable exception = null;

    /**
     * 返回http状态码
     */
    private int httpCode = -1;
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

    /**
     * 获取字符串类型的数据
     *
     * @return
     */
    public String getDataString() {
        if (exception == null) {
            try {
                if (!setDataString) {
                    dataString = EntityUtils.toString(httpEntity, charset.charset);
                    setDataString = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return dataString;
    }

    /**
     * 将返回数据以byte数组形式输出
     *
     * @return
     */
    public byte[] getDataByteArray() {
        if (exception == null) {
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
        return null;
    }

    /**
     * 获取异常信息
     *
     * @return
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * 是否没有异常且状态为http 200
     *
     * @return
     */
    public boolean isOK() {
        if (!hasException() && httpCode == 200) {
            return true;
        }
        return false;
    }


    /**
     * 是否发生异常
     *
     * @return
     */
    public boolean hasException() {
        return exception != null;
    }

    @Override
    public String toString() {
        if (exception != null) {
            return "request exception: \n\t" + exception.toString();
        }
        return "httpCode: [" +
                httpCode +
                "] header: [" +
                (header == null ? "" : header.toString()) +
                "] cookies: [" +
                (cookies == null ? "" : cookies.toString()) +
                "] dataString: [" +
                (getDataString() == null ? "" : getDataString()) +
                "]";
    }
}
