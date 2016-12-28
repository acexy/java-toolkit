package tuling;

import com.thankjava.toolkit3d.api.tuling.ChatRobot;
import com.thankjava.toolkit3d.fastjson.FastJson;

public class ChatRobotTest {

	public static void main(String[] args) {
		ChatRobot chatRobot = new ChatRobot("");
		System.out.println(FastJson.toJsonString(chatRobot.chat("eb2edb739","呵呵")));
	}
}
