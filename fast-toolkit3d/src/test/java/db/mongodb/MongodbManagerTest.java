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

    final static MongoDBManager mongoDBManager = new MongoDriverManager();

    public static void main(String[] args) {
        Test test = new Test();
        test.setTime(new Date().getTime());
        test.setValue("1");
        System.out.println(mongoDBManager.insertOne("test", test));

        System.out.println(mongoDBManager.findByObjectId("test", "5a240c6f1d3ced51b8c60541"));
    }
}
