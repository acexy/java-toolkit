package reflect;

import com.thankjava.toolkit.core.reflect.ReflectUtil;

import reflect.bean.ChiClass;

public class ReflectUtilTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        ChiClass cc = new ChiClass();

//        System.out.println(ReflectUtil.getField(ChiClass.class, "name1"));


        Object i = (int)1;
        System.out.println(i instanceof Integer);
//		Field[] fields = ReflectUtil.getFieldArrayExcludeUID(cc.getClass());
//
//		for (Field field : fields) {
//			field.setAccessible(true);
//			System.out.println(field.get(cc));
//		}
    }
}
