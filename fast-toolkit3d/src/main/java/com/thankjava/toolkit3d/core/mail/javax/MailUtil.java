package com.thankjava.toolkit3d.core.mail.javax;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.thankjava.toolkit3d.bean.mail.MailContent;

/**
 * 邮件发送工具
 * <p>Function: MailUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年1月4日 下午6:16:08
 */
public final class MailUtil {

    private MailUtil() {
    }

    class AuthEntity extends Authenticator {

        String userName;
        String password;

        protected AuthEntity(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

    /**
     * 发送基本HTML邮件
     *
     * @param mailContent
     * @return
     * @throws MessagingException
     */
    public static void sendMail(MailContent mailContent) throws MessagingException {
        // 判断是否需要身份认证
        Properties pro = mailContent.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        AuthEntity authEntity = new MailUtil().new AuthEntity(mailContent.getAuthUsername(), mailContent.getAuthPassword());
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authEntity);
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        Address from = new InternetAddress(mailContent.getFromAddress());
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);
        // 创建邮件的接收者地址，并设置到邮件消息中
        Address to = new InternetAddress(mailContent.getToAddress());
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipient(Message.RecipientType.TO, to);
        // 设置邮件消息的主题
        mailMessage.setSubject(mailContent.getSubject());
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(mailContent.getContent(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // 将MiniMultipart对象设置为邮件内容
        mailMessage.setContent(mainPart);
        // 发送邮件
        Transport.send(mailMessage);
    }


    /**
     * 发送可带附件的邮件
     *
     * @param mailContent
     * @param attachment  目标附件File
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void sendMail(MailContent mailContent, File attachment) throws MessagingException, UnsupportedEncodingException {
        // 判断是否需要身份认证
        Properties pro = mailContent.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        AuthEntity authEntity = new MailUtil().new AuthEntity(mailContent.getAuthUsername(), mailContent.getAuthPassword());
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authEntity);
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        Address from = new InternetAddress(mailContent.getFromAddress());
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);
        // 创建邮件的接收者地址，并设置到邮件消息中
        Address to = new InternetAddress(mailContent.getToAddress());
        mailMessage.setRecipient(Message.RecipientType.TO, to);
        // 设置邮件消息的主题
        mailMessage.setSubject(mailContent.getSubject());
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(mailContent.getContent(), "text/html; charset=utf-8");

        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            mainPart.addBodyPart(attachmentBodyPart);
        }

        mainPart.addBodyPart(html);
        // 将MiniMultipart对象设置为邮件内容
        mailMessage.setContent(mainPart);
        // 发送邮件
        Transport.send(mailMessage);
    }

}
