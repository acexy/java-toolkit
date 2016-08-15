package mail;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.thankjava.toolkit.enums.mail.MailService;
import org.thankjava.toolkit3d.utils.mail.MailSender;
import org.thankjava.toolkit3d.vo.utils.mail.MailEntity;

public class MailTest {

	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		MailEntity mailEntity = new MailEntity(
				MailService.ALIYUN,
				"zhaoxiaoyao@f-road.com.cn",
				"thankjava@aliyun.com",
				"thankjava520",
				"来自java的邮件",
				"来自java的测试邮件"
		);
		MailSender.sendMail(mailEntity,new File("F:/Download/Browser/rootCA.crt"));
	}
}
