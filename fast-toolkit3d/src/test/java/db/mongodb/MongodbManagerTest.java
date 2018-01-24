package db.mongodb;

import com.thankjava.toolkit3d.fastjson.FastJson;
import db.mongodb.po.Test;
import org.bson.Document;
import com.thankjava.toolkit.radom.Sequence;
import com.thankjava.toolkit.thread.ThreadPool;
import com.thankjava.toolkit3d.db.mongodb.MongoDBManager;
import com.thankjava.toolkit3d.db.mongodb.datasource.MongoDriverManager;

import java.util.Date;


public class MongodbManagerTest {



    public static void main(String[] args) {
        MongoDBManager mongoDBManager = MongoDriverManager.getSingleton();
//        MongoDBManager mongoDBManager = MongoDriverManager.getSingleton("/Users/acexy/Downloads/mongodb.properties");
        Test test = new Test();
        test.setTime(new Date().getTime());
        test.setValue("1");
        System.out.println(mongoDBManager.insertOne("test", test));


//        test.setValue("美国");
//        System.out.println(mongoDBManager.updateOneByObjectId("test",test,"5a24f854eba60917d0191f8f"));
    }
}
