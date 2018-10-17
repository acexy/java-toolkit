package db.mongodb;

import com.thankjava.toolkit3d.core.db.mongodb.MongoManager;
import com.thankjava.toolkit3d.core.db.mongodb.datasource.MongoManagerImpl;


public class MongodbManagerTest {


    public static void main(String[] args) {
        MongoManager mongoManager = MongoManagerImpl.getSingleton();
//        MongoManager mongoManager = MongoManagerImpl.getSingleton("/Users/acexy/Downloads/mongodb.properties");


//        int index = 0;
//        for (int i = 0; i < 100; i++) {
//            Test test = new Test();
//            test.setTime(new Date().getTime());
//            test.setValue(String.valueOf(i));
//            System.out.println(mongoManager.insertOne("test", test));
//        }

//        test.setValue("10");
//        System.out.println(mongoManager.updateOneByObjectId("test",test,"5a24f854eba60917d0191f8f"));

//        PageEntity<Test> pageEntity = PageEntity.newPageEntity(Test.class, new Test());
//        pageEntity
//                .addSort(new Sort("value", SortType.desc))
//                .addSort(new Sort("create_time", SortType.asc))
//                .setPageNumber(5)
//                .setPageSize(5)
//        ;
//        mongoManager.findByPage("test", pageEntity);
//        System.out.println(FastJson.toJSONString(pageEntity));

        mongoManager.deleteOneByObjectId("logs","5b76db371bba200ca44124fe");
    }
}
