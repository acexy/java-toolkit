package db.mongodb;

import com.thankjava.toolkit3d.core.db.mongodb.MongoManager;
import com.thankjava.toolkit3d.core.db.mongodb.datasource.MongoManagerImpl;
import db.mysql.po.Test;


public class MongodbManagerTest {


    public static void main(String[] args) {
        MongoManager mongoManager = MongoManagerImpl.getSingleton();
//        MongoManager mongoManager = MongoManagerImpl.getSingleton("/Users/acexy/Downloads/mongodb.properties");


        Test test = new Test();
        test.setValue("1");
        System.out.println(mongoManager.insertOne("test", test));


        mongoManager.deleteOneByObjectId("logs", "5b76db371bba200ca44124fe");
    }
}
