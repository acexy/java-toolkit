package db.mysql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.thankjava.toolkit.radom.Sequence;
import org.thankjava.toolkit.thread.ThreadPool;
import org.thankjava.toolkit3d.db.mysql.MyBatisManager;
import org.thankjava.toolkit3d.db.mysql.datasource.MySQLManager;

import db.mysql.mapper.TestMapper;
import db.mysql.po.Test;

public class MyBatisManagerTest {

	public static void main(String[] args) {
		
		final MyBatisManager myBatisManager = MySQLManager.getInstance();
		
		ThreadPool pool = new ThreadPool(50, 100, 600000, 1000000);
		for (int i = 0; i < 1000000L; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					
					SqlSessionFactory sessionFactory = myBatisManager.getSqlSessionFactory();
					SqlSession session = sessionFactory.openSession();
					TestMapper mapper = (TestMapper) session.getMapper(TestMapper.class);
					Test test = new Test();
					test.setValue(Sequence.uuid()[0]);
					mapper.save(test);
					System.out.println(test.getId());
					myBatisManager.commitAndcloseSqlSession(session);;
				}
			});
		}
		
	}
	
}
