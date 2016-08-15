package aikuaidi;

import org.thankjava.toolkit3d.api.aikuaidi.Expressage;
import org.thankjava.toolkit3d.enums.api.aikuaidi.ExpreService;
import org.thankjava.toolkit3d.utils.fastjson.FastJson;

public class ExpressageTest {

	public static void main(String[] args) {
		Expressage expressage = new Expressage("");
		System.out.println(FastJson.toJsonString(expressage.query("", ExpreService.yuantong)));
	}
}
