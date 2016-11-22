package reflect;

import java.lang.reflect.Field;

import org.thankjava.toolkit.reflect.ReflectHelper;

import reflect.bean.ChiClass;

public class ReflectHelperTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		ChiClass cc = new ChiClass();
		Field[] fields = ReflectHelper.getFieldArrayExcludeUID(cc.getClass());
		
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.get(cc));
		}
	}
}
