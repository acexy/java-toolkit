package com.thankjava.toolkit.core.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;


/**
 * 基础的基于jdk的http请求功能
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年1月4日 下午6:16:08
 */
public final class JDKHttp {

    private JDKHttp() {
    }

    private static final String CHARSET = "utf-8";

    /**
     * 使用GET方式请求目标网站
     *
     * @param url 完整的请求url
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月4日 下午5:59:17
     */
    public static String get(String url, Map<String, String>... head) {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {

            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            if (head != null && head.length != 0) {
                Map<String, String> header = head[0];
                for (Map.Entry<String, String> h : header.entrySet()) {
                    connection.setRequestProperty(h.getKey(), h.getValue());
                }
            }
            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 使用POST方式请求目标网站
     * @param bodyString 传输字符串数据
     * @param bodyByteArray 传输byte数据 * 若bodyString已设值 则该参数无效
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月4日 下午5:59:17
     */
    public static String post(String url, String bodyString, byte[] bodyByteArray, Map<String, String>... head) {

        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            if (head != null && head.length != 0) {
                Map<String, String> header = head[0];
                for (Map.Entry<String, String> h : header.entrySet()) {
                    connection.setRequestProperty(h.getKey(), h.getValue());
                }
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);

            if (bodyString != null) {
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(bodyString);
                outputStream.flush();
                outputStream.close();
            }

            if (bodyByteArray != null) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(bodyByteArray);
                outputStream.flush();
                outputStream.close();
            }

            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
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
    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
