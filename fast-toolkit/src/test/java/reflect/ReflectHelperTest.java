package reflect;

import com.thankjava.toolkit.core.reflect.ReflectHelper;

import reflect.bean.ChiClass;

public class ReflectHelperTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		ChiClass cc = new ChiClass();

		System.out.println(ReflectHelper.getField(ChiClass.class,"name1"));

//		Field[] fields = ReflectHelper.getFieldArrayExcludeUID(cc.getClass());
//
//		for (Field field : fields) {
//			field.setAccessible(true);
//			System.out.println(field.get(cc));
//		}
	}
}
