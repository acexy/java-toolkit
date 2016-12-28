package com.thankjava.toolkit3d.vo.mail;

import java.util.Properties;

import com.thankjava.toolkit3d.enums.mail.MailService;

/**
 * 邮件请求体
* <p>Function: MailBean</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2014年10月29日 下午4:04:56
* @version 1.0
 */
public class MailEntity {

	
	private String mailServerHost;
	private String mailServerPort = "25";
	
	// 邮件发送者的地址
	private String fromAddress;
	// 邮件接收者的地址
	private String toAddress;
	
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	
	// 是否需要身份验证
	private boolean validate = true;
	
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;

	/**
	 * 邮件发送参数限定
	* <p>Title: </p>
	* <p>Description: </p>
	* @param serverHost 邮件供应商地址
	* @param serverPort 邮件供应商端口
	* @param toAddr 收件人地址
	* @param uName 发件人邮箱地址
	* @param uPwd 发件人密码
	* @param subject 邮件标题
	* @param content 邮件内容(支持标准邮件HTML结构)
	 */
	public MailEntity(String serverHost,String serverPort,String toAddr,String uName,String uPwd,String subject,String content){
		this.mailServerHost = serverHost;
		this.mailServerPort = serverPort;
		this.fromAddress = uName;
		this.toAddress = toAddr;
		this.userName = uName;
		this.password = uPwd;
		this.subject = subject;
		this.content = content;
	}
	
	/**
	 * 
	* <p>Title: </p>
	* <p>Description: </p>
	* @param mailService 内置已有的邮件服务商
	* @param toAddr 收件人地址
	* @param uName 发件人邮箱地址
	* @param uPwd 发件人密码
	* @param subject 邮件标题
	* @param content 邮件内容(支持标准邮件HTML结构)
	 */
	public MailEntity(MailService mailService,String toAddr,String uName,String uPwd,String subject,String content){
		this.mailServerHost = mailService.getService();
		this.mailServerPort = mailService.getPort();
		this.fromAddress = uName;
		this.toAddress = toAddr;
		this.userName = uName;
		this.password = uPwd;
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
		properties.put("mail.smtp.auth", validate ? "true" : "false");
		return properties;
	}

	
	public String getMailServerHost() {
		return mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean isValidate() {
		return validate;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
}