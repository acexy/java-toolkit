package mail;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.thankjava.toolkit3d.enums.mail.MailService;
import com.thankjava.toolkit3d.mail.MailSender;
import com.thankjava.toolkit3d.vo.mail.MailEntity;

public class MailTest {

	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		
		MailEntity mailEntity = new MailEntity(
				MailService.ALIYUN, //选择发送邮件的供应商服务 --> 可以通过例外一个构造函数手动指定
				"****@**.**",//对方收件人邮箱
				"****@**.**",//发件人(自己)邮箱
				"************",//发件人密码
				"邮箱标题",
				"来自java的测试邮件"//邮件内容(支持标准邮件HTML结构)
		);
		
		MailSender.sendMail(mailEntity,new File("F:/Download/Browser/rootCA.crt"));//发送带附件的邮件
		MailSender.sendMail(mailEntity);//发送不带附件的邮件
	}
}
