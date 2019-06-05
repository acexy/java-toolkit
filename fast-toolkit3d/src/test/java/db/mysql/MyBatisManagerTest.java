package db.mysql;

import com.thankjava.toolkit.core.thread.ThreadPool;
import com.thankjava.toolkit3d.bean.db.PageEntity;
import com.thankjava.toolkit3d.bean.db.Sort;
import com.thankjava.toolkit3d.bean.db.SortType;
import com.thankjava.toolkit3d.core.db.BasicDBManagerBuilder;
import com.thankjava.toolkit3d.core.fastjson.FastJson;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;

import db.mysql.mapper.TestMapper;
import db.mysql.po.Test;

import java.util.UUID;

public class MyBatisManagerTest {

    public static void main(String[] args) {

        final MyBatisManager manager = BasicDBManagerBuilder.buildMyBatisManager();
        final TestMapper mapper = manager.getMapper(TestMapper.class);


//        ThreadPool pool = new ThreadPool(5, 10, 600000, 1000000);
//
//        for (int i = 0; i < 100; i++) {
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    Test test = new Test();
//                    test.setValue(UUID.randomUUID().toString());
//                    mapper.save(test);
//                    System.out.println(test.getId());
//                    manager.commit(mapper);
//                }
//            });
//        }


//        System.out.println(FastJson.toJSONString(mapper.select(new Test("0a2a7e26-1a60-44cb-a848-181fd2049ca9"))));

        PageEntity<Test> pageEntity = PageEntity.newPageEntity(Test.class, null);
        pageEntity.addSort(new Sort("id", SortType.desc));
        pageEntity.setPageSize(2);
        mapper.selectByPage(pageEntity);
        System.out.println(FastJson.toJSONString(pageEntity));


    }

}
