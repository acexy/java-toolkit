package db.mongodb;

import org.bson.Document;
import com.thankjava.toolkit.radom.Sequence;
import com.thankjava.toolkit.thread.ThreadPool;
import com.thankjava.toolkit3d.db.mongodb.MongoDBManager;
import com.thankjava.toolkit3d.db.mongodb.datasource.MongoDriverManager;


public class MongodbManagerTest {

	public static void main(String[] args) {
		final MongoDBManager mongoDBManager = new MongoDriverManager();
//		System.out.println(mongoDBManager.count("test",null));
		System.out.println(mongoDBManager.count("test",new Document("key","value")));
//		System.out.println(mongoDBManager.insert("test", new Document("key","value")));
//		System.out.println(mongoDBManager.insertObject("test", new Test(1L,"你好")));
//		System.out.println(mongoDBManager.count("test",null));
//		
//		
//		
		ThreadPool pool = new ThreadPool(50, 100, 600000, 1000000);
		for (int i = 0; i < 1000000L; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(mongoDBManager.insertOne("test", new Document(Sequence.uuid()[0],Sequence.uuid()[0])));
				}
			});
		}
//		
//		ArrayList<Object> tests = new ArrayList<Object>();
//		tests.add(new Test(1L,"bbb"));
//		tests.add(new Test(2L,"bbb"));
//		tests.add(new Test(3L,"bbb"));
//		tests.add(new Test(4L,"bbb"));
//		tests.add(new Test(5L,"bbb"));
//		mongoDBManager.insertObjects("test",tests);
		
//		System.out.println(FastJson.toJsonString(mongoDBManager.find("test", null)));
//		System.out.println(FastJson.toJsonString(mongoDBManager.findObject("test", null,Test.class)));
//		System.out.println(mongoDBManager.updateMany(
//				"test",
//				new Document("a","2"),
//				null
//				));
	}
}
