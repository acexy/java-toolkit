package tuling;

import org.thankjava.toolkit3d.api.tuling.ChatRobot;
import org.thankjava.toolkit3d.utils.fastjson.FastJson;

public class ChatRobotTest {

	public static void main(String[] args) {
		ChatRobot chatRobot = new ChatRobot("");
		System.out.println(FastJson.toJsonString(chatRobot.chat("eb2edb739","呵呵")));
	}
}
