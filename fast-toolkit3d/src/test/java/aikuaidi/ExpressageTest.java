package aikuaidi;

import com.thankjava.toolkit3d.enums.api.aikuaidi.ExpreService;
import com.thankjava.toolkit3d.api.aikuaidi.Expressage;
import com.thankjava.toolkit3d.fastjson.FastJson;

public class ExpressageTest {

	public static void main(String[] args) {
		Expressage expressage = new Expressage("");
		System.out.println(FastJson.toJsonString(expressage.query("", ExpreService.yuantong)));
	}
}
