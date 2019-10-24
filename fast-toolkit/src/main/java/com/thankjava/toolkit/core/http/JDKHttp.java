package com.thankjava.toolkit.core.http;

import com.thankjava.toolkit.bean.common.Charset;

import java.io.*;
import java.net.*;
import java.util.Map;


/**
 * 基础的基于jdk的http请求功能
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年1月4日 下午6:16:08
 */
public final class JDKHttp {

    private static final String CHARSET = Charset.utf8.charset;

    private String httpUrl;

    private String postBodyString;
    private byte[] postBodyByteArray;

    private String requestCharset;
    private String responseCharset = CHARSET;

    private Map<String, String> headers;

    public JDKHttp(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    /**
     * 设置post body体 (字符串)
     *
     * @param bodyString
     * @param charset    不设置默认utf-8
     * @return
     */
    public JDKHttp setPostBody(String bodyString, Charset... charset) {
        this.postBodyString = bodyString;
        this.requestCharset = (charset == null || charset.length == 0) ? CHARSET : charset[0].charset;
        return this;
    }

    /**
     * 设置http 请求头
     *
     * @param headers
     * @return
     */
    public JDKHttp setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public JDKHttp setResponseCharset(Charset charset) {
        this.responseCharset = charset != null ? charset.charset : CHARSET;
        return this;
    }

    /**
     * 设置post body请求体(byte数组数据) 若设置了bodyString 则 bodyByteArray不生效
     *
     * @param bodyByteArray
     * @return
     */
    public JDKHttp setPostBody(byte[] bodyByteArray) {
        this.postBodyByteArray = bodyByteArray;
        return this;
    }

    private URL getUrl() {
        try {
            return new URL(httpUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setHttpHeaders(HttpURLConnection urlConnection) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> h : headers.entrySet()) {
                urlConnection.setRequestProperty(h.getKey(), h.getValue());
            }
        }
    }

    private HttpURLConnection getConnection() {
        URL url = getUrl();
        if (url == null) return null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            setHttpHeaders(urlConnection);
            return urlConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream execute(HttpURLConnection urlConnection) {
        try {
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getResponseString(InputStream inputStream) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, responseCharset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getResponseByteArray(InputStream inputStream) {
        try {
            byte[] array = new byte[inputStream.available()];
            inputStream.read(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setPostData(HttpURLConnection urlConnection) {
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        try {
            urlConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try {


            if (postBodyString != null) {
                OutputStreamWriter outputStream = new OutputStreamWriter(urlConnection.getOutputStream(), requestCharset);
                outputStream.write(postBodyString);
                outputStream.flush();
                outputStream.close();
            } else {
                if (postBodyByteArray != null && postBodyByteArray.length > 0) {
                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(postBodyByteArray);
                    outputStream.flush();
                    outputStream.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 发起基础的http get请求
     *
     * @return
     */
    public String doGetResponseString() {
        HttpURLConnection urlConnection = getConnection();
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        if (urlConnection == null) return null;
        InputStream inputStream = execute(urlConnection);
        if (inputStream == null) return null;
        return getResponseString(inputStream);
    }

    /**
     * 发起 get 请求返回的数据解析为byte数组
     *
     * @return
     */
    public byte[] doGetResponseByteArray() {
        HttpURLConnection urlConnection = getConnection();
        try {
            urlConnection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        if (urlConnection == null) return null;
        InputStream inputStream = execute(urlConnection);
        if (inputStream == null) return null;
        return getResponseByteArray(inputStream);
    }

    /**
     * 发起post请求
     * @return
     */
    public String doPostResponseString() {
        HttpURLConnection urlConnection = getConnection();
        if (urlConnection == null) return null;
        setPostData(urlConnection);
        InputStream inputStream = execute(urlConnection);
        if (inputStream == null) return null;
        return getResponseString(inputStream);
    }

    /**
     * 发起post请求
     * @return
     */
    public byte[] doPostResponseByteArray() {
        HttpURLConnection urlConnection = getConnection();
        if (urlConnection == null) return null;
        setPostData(urlConnection);
        InputStream inputStream = execute(urlConnection);
        if (inputStream == null) return null;
        return getResponseByteArray(inputStream);
    }


    /**
     * 参数Encode
     * <p>Function: encode</p>
     * <p>Description: </p>
     *
     * @param string
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:55:53
     */
    public static String urlEncode(String string, Charset... charset) {
        try {
            return URLEncoder.encode(string, (charset != null && charset.length > 0) ? charset[0].charset : CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
