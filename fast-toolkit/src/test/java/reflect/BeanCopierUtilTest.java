package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

import com.thankjava.toolkit.core.reflect.BeanCopierUtil;

import com.thankjava.toolkit.core.reflect.ReflectUtil;
import reflect.bean.entity.Entity1;
import reflect.bean.vo.Vo;
import reflect.bean.vo.Vo1;

public class BeanCopierUtilTest {

    public static void main(String[] args) throws InterruptedException {

//        Entity1 e1 = new Entity1();
//        e1.setBol(false);
//
//        List<String> listStr = new ArrayList<>();
//        listStr.add("你好");
//        e1.setListStr(listStr);
//        String[] strArr = new String[]{"aa", "bb", "cc"};
//        e1.setStrArr(strArr);
//
//        Vo1 v1 = BeanCopierUtil.copy(e1, Vo1.class);
//
//
        Entity1 entity1 = new Entity1();
        entity1.setBol(false);
        entity1.setInte(12);
        entity1.setStr("set in entity");

        Vo1 vo1 = new Vo1();
        vo1.setBol(true);
        vo1.setInte(13);

        System.out.println(BeanCopierUtil.copy(entity1, vo1).getInte());;

        System.out.println(entity1.getInte());
        System.out.println(vo1.getInte());

//        Vo1 vo1 = new Vo1();
//
//        List<Vo> vos = new ArrayList<>();
//        Vo vo = new Vo();
//        vo.setTime(new Date());
//        vos.add(vo);
//
//        vo1.setList(vos);
//
//        System.out.println(BeanCopierUtil.copy(vo1, Entity1.class).getList());

//        System.out.println(new LinkedHashMap<>() instanceof Map);
    }

}