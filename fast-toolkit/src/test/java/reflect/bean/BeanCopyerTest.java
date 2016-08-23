package reflect.bean;

import java.util.ArrayList;
import java.util.List;

import org.thankjava.toolkit.utils.reflect.BeanCopier;

import reflect.bean.entity.Entity1;
import reflect.bean.vo.Vo1;

public class BeanCopyerTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		
//		Entity1 e1 = new Entity1();
//		e1.setBol(false);
//		
//		List<String> listStr = new ArrayList<>();
//		listStr.add("你好");
//		e1.setListStr(listStr);
//		String[] strArr = new String[]{"aa","bb","cc"};
//		e1.setStrArr(strArr);
//		
//		Vo1 v1 = BeanCopier.copy(e1, Vo1.class);
//		System.out.println(JSONObject.toJSONString(v1));
		
		
		{
			long st = System.currentTimeMillis();
			
			Entity1 e1 = new Entity1();
			e1.setBol(false);
			
			List<String> listStr = new ArrayList<>();
			listStr.add("1");
//			e1.setListStr(listStr);
			
			
			String[] strArr = new String[]{"aa","bb","cc"};
//			e1.setStrArr(strArr);
			
			for(int i = 0 ; i < 100000 ; i ++){
				Vo1 v1 = new Vo1();
				v1.setBol(e1.getBol());
//				v1.setListStr(e1.getListStr());
//				v1.setStrArr(e1.getStrArr());
			}
			System.out.println("use time : " + (System.currentTimeMillis() - st));
		}
		
		{
			long st = System.currentTimeMillis();
			Entity1 e1 = new Entity1();
			e1.setBol(false);
			List<String> listStr = new ArrayList<>();
			listStr.add("你好");
//			e1.setListStr(listStr);
			String[] strArr = new String[]{"aa","bb","cc"};
//			e1.setStrArr(strArr);
			
			for(int i = 0 ; i < 100000 ; i ++){
				BeanCopier.copy(e1, Vo1.class);
			}
			
			
			System.out.println("use time : " + (System.currentTimeMillis() - st));
		}
	}

}
