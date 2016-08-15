package baidu.translation;

import java.io.UnsupportedEncodingException;

import org.thankjava.toolkit3d.api.baidu.translation.Translation;
import org.thankjava.toolkit3d.enums.api.baidu.translation.Language;
import org.thankjava.toolkit3d.utils.fastjson.FastJson;

public class TranslationTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Translation translation = new Translation("", "");
		System.out.println(FastJson.toJsonString(translation.translat("钓鱼岛是中国的", null, Language.dan)));
		
	}
}
