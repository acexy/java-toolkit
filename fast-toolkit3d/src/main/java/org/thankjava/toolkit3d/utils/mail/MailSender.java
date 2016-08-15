package org.thankjava.toolkit3d.utils.mail;

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

import org.thankjava.toolkit3d.vo.utils.mail.MailEntity;

/**
 * 邮件发送工具
* <p>Function: MailSender</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年1月4日 下午6:16:08
* @version 1.0
 */
public final class MailSender {
	
	private MailSender(){};
	
	class AuthEntity extends Authenticator {

		String userName = null;
		String password = null;

		protected AuthEntity(String username, String password) {
			this.userName = username;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}
	
	
	public static boolean sendMail(MailEntity mailEntity) throws MessagingException {
		// 判断是否需要身份认证
		AuthEntity authEntity = null;
		Properties pro = mailEntity.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailEntity.isValidate()) {
			authEntity = new MailSender().new AuthEntity(mailEntity.getUserName(),mailEntity.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authEntity);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailEntity.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailEntity.getToAddress());
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailEntity.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		// 设置HTML内容
		html.setContent(mailEntity.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		// 将MiniMultipart对象设置为邮件内容
		mailMessage.setContent(mainPart);
		// 发送邮件
		Transport.send(mailMessage);
		return true;
	}
	
	
	
	public static boolean sendMail(MailEntity mailEntity,File attachment) throws MessagingException, UnsupportedEncodingException {
		// 判断是否需要身份认证
		AuthEntity authEntity = null;
		Properties pro = mailEntity.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailEntity.isValidate()) {
			authEntity = new MailSender().new AuthEntity(mailEntity.getUserName(),mailEntity.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authEntity);
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailEntity.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailEntity.getToAddress());
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailEntity.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		// 设置HTML内容
		html.setContent(mailEntity.getContent(), "text/html; charset=utf-8");
		
		if (attachment != null) {
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachment);
			attachmentBodyPart.setDataHandler(new DataHandler(source));

			// 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
			// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			// messageBodyPart.setFileName("=?GBK?B?" +
			// enc.encode(attachment.getName().getBytes()) + "?=");

			// MimeUtility.encodeWord可以避免文件名乱码
			attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
			mainPart.addBodyPart(attachmentBodyPart);
		}
		
		
		mainPart.addBodyPart(html);
		// 将MiniMultipart对象设置为邮件内容
		mailMessage.setContent(mainPart);
		// 发送邮件
		Transport.send(mailMessage);
		return true;
	}

}
