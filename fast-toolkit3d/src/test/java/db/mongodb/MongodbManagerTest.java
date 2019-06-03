package db.mongodb;

import com.thankjava.toolkit3d.core.db.BasicDBManagerBuilder;
import com.thankjava.toolkit3d.core.db.mongodb.MongoManager;
import db.mysql.po.Test;
import org.bson.Document;

import java.util.List;


public class MongodbManagerTest {


    public static void main(String[] args) {
        MongoManager mongoManager = BasicDBManagerBuilder.buildMongoManager("/Users/acexy/Development/config/acexy/mongodb.properties");


        Test test = new Test();
        test.setValue("1");
        System.out.println(mongoManager.insertOne("test", test));

    }
}
