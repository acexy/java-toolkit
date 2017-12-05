package fastjson;

import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.vo.mail.MailEntity;

public class FastJsonTest {

    public static void main(String[] args) {


        MailEntity mailEntity = new MailEntity("1", "2", "3", "4", "5", "6", "7");

        System.out.println(FastJson.toJSONObject(mailEntity));
    }
}
