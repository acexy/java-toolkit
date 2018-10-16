package db.mysql;

import com.thankjava.toolkit3d.core.fastjson.FastJson;
import org.apache.ibatis.session.SqlSession;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;
import com.thankjava.toolkit3d.core.db.mysql.datasource.MySQLManager;

import db.mysql.mapper.TestMapper;
import db.mysql.po.Test;

public class MyBatisManagerTest {

    public static void main(String[] args) {

        final MyBatisManager myBatisManager = MySQLManager.getInstance();
        SqlSession session = myBatisManager.getSqlSession();
        TestMapper mapper = session.getMapper(TestMapper.class);

//		ThreadPool pool = new ThreadPool(50, 100, 600000, 1000000);
//		for (int i = 0; i < 1000000L; i++) {
//			pool.execute(new Runnable() {
//				@Override
//				public void run() {
//
//					SqlSessionFactory sessionFactory = myBatisManager.getSqlSessionFactory();
//					SqlSession session = sessionFactory.openSession();
//					TestMapper mapper = (TestMapper) session.getMapper(TestMapper.class);
//					Test test = new Test();
//					test.setValue(UUID.randomUUID().toString());
//					mapper.save(test);
//					System.out.println(test.getId());
//					myBatisManager.commitAndCloseSqlSession(session);;
//				}
//			});
//		}

        System.out.println(FastJson.toJSONString(mapper.select(new Test("7074e6ed9c7b455c94c3b69ca5ac7006"))));

//        PageEntity<Test> pageEntity = PageEntity.newPageEntity(Test.class, new Test("7074e6ed9c7b455c94c3b69ca5ac7006"));
//        pageEntity.addSort(new Sort("id", SortType.desc));
//        mapper.selectByPage(pageEntity);
//        System.out.println(FastJson.toJSONString(pageEntity));


    }

}
