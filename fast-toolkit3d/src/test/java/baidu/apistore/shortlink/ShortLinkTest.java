package baidu.apistore.shortlink;

import org.thankjava.toolkit3d.api.baidu.apistore.shortlink.ShortLink;
import org.thankjava.toolkit3d.enums.api.baidu.apistore.shortlink.ShortLinkType;
import org.thankjava.toolkit3d.utils.fastjson.FastJson;
import org.thankjava.toolkit3d.vo.api.baidu.apistore.shortlink.ShortLinkData;

public class ShortLinkTest {

	public static void main(String[] args) {
		ShortLink shortLink = new ShortLink("e4beccedfcb29b0e791b28d0384aea14");
		ShortLinkData shortLinkData = shortLink.link(ShortLinkType.BAIDU_LIN, new String[]{"http://www.baidu.com","http://svn.thankjava.com"});
		System.out.println(FastJson.toJsonString(shortLinkData));
	}
}
