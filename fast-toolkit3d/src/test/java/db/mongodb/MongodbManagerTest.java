package db.mongodb;

import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.vo.db.PageEntity;
import com.thankjava.toolkit3d.vo.db.Sort;
import com.thankjava.toolkit3d.vo.db.SortType;
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


//        int index = 0;
//        for (int i = 0; i < 100; i++) {
//            Test test = new Test();
//            test.setTime(new Date().getTime());
//            test.setValue(String.valueOf(i));
//            System.out.println(mongoDBManager.insertOne("test", test));
//        }

//        test.setValue("10");
//        System.out.println(mongoDBManager.updateOneByObjectId("test",test,"5a24f854eba60917d0191f8f"));

//        PageEntity<Test> pageEntity = PageEntity.newPageEntity(Test.class, new Test());
//        pageEntity
//                .addSort(new Sort("value", SortType.desc))
//                .addSort(new Sort("create_time", SortType.asc))
//                .setPageNumber(5)
//                .setPageSize(5)
//        ;
//        mongoDBManager.findByPage("test", pageEntity);
//        System.out.println(FastJson.toJSONString(pageEntity));

        mongoDBManager.deleteOneByObjectId("logs","5b76db371bba200ca44124fe");
    }
}
