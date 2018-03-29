package fastjson;

import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.vo.mail.MailEntity;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    public static void main(String[] args) {


        MailEntity mailEntity = new MailEntity("1", "2", "3", "4", "5", "6", "7");
        System.out.println(FastJson.toJSONObject(mailEntity));

        List<Entity> list = new ArrayList<>();
        list.add(new Entity().setKey("key1").setValue("value1"));
        list.add(new Entity().setKey("key2").setValue("value2"));

        String json = FastJson.toJSONString(FastJson.toJSONArray(list));
        System.out.println(FastJson.toJSONArray(json));
    }
}
