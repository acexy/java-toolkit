package com.thankjava.toolkit3d.bean.mail;

import java.util.Properties;

/**
 * 邮件请求体
 * <p>Function: MailBean</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2014年10月29日 下午4:04:56
 */
public class MailContent {


    private String mailServerHost;
    private String mailServerPort;

    // 邮件发送者的地址
    private String fromAddress;

    // 邮件接收者的地址
    private String toAddress;

    // 登陆邮件发送服务器的用户名和密码
    private String authUsername;
    private String authPassword;

    // 邮件主题
    private String subject;

    // 邮件的文本内容
    private String content;

    /**
     * 邮件发送参数限定
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param serverHost   邮件供应商地址
     * @param serverPort   邮件供应商端口
     * @param toAddr       收件人地址
     * @param authUsername 发件人邮箱地址
     * @param authPassword 发件人密码
     * @param subject      邮件标题
     * @param content      邮件内容(支持标准邮件HTML结构)
     */
    public MailContent(String serverHost, String serverPort, String toAddr, String authUsername, String authPassword, String subject, String content) {
        this.mailServerHost = serverHost;
        this.mailServerPort = serverPort;
        this.fromAddress = authUsername;
        this.toAddress = toAddr;
        this.authUsername = authUsername;
        this.authPassword = authPassword;
        this.subject = subject;
        this.content = content;
    }

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param mailService  内置已有的邮件服务商
     * @param toAddr       收件人地址
     * @param authUsername 发件人邮箱地址
     * @param authPassword 发件人密码
     * @param subject      邮件标题
     * @param content      邮件内容(支持标准邮件HTML结构)
     */
    public MailContent(MailService mailService, String toAddr, String authUsername, String authPassword, String subject, String content) {
        this.mailServerHost = mailService.getService();
        this.mailServerPort = mailService.getPort();
        this.fromAddress = authUsername;
        this.toAddress = toAddr;
        this.authUsername = authUsername;
        this.authPassword = authPassword;
        this.subject = subject;
        this.content = content;
    }


    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this.mailServerHost);
        properties.put("mail.smtp.port", this.mailServerPort);
        properties.put("mail.smtp.auth", "true");
        return properties;
    }


    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}