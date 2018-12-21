package db.mongodb;

import com.thankjava.toolkit3d.core.db.mongodb.MongoManager;
import com.thankjava.toolkit3d.core.db.mongodb.impl.MongoManagerImpl;
import db.mysql.po.Test;
import org.bson.Document;

import java.util.List;


public class MongodbManagerTest {


    public static void main(String[] args) {
//        MongoManager mongoManager = MongoManagerImpl.getSingleton();
        MongoManager mongoManager = MongoManagerImpl.getSingleton("/Users/acexy/Development/config/acexy/java-toolkit/mongodb.properties");


//        Test test = new Test();
//        test.setValue("1");
//        System.out.println(mongoManager.insertOne("test", test));


        List<Document> docs = mongoManager.findMany("images", null);
        System.out.println(docs.size());
        String string = "D0JA,D0JB,D0JC,D0JD,D0JE,D0JF,D0JG,D0JH,D0JI,D0JJ,D0JK,D0JL,D0JM,D0JN,D0JO,D0JP,D0JQ,D0JR,D0JS,D0JT,D0JU,D0JV,D0JW,D0JX,D0JY,D0JZ,D0Ja,D0Jb,D0Jc,D0Jd,D0Je,D0Jf,D0Jg,D0Jh,D0Ji,D0Jj,D0Jk,D0Jl,D0Jm,D0Jn,D0Jo,D0Jp,D0Jq,D0Jr,D0Js,D0Jt,D0Ju,D0Jv,D0Jw,D0Jx,D0Jy,D0Jz,D0J0,D0J1,D0J2,D0J3,D0J4,D0J5,D0J6,D0J7,D0J8,D0J9,D0J+,D0J-,D0KA,D0KB,D0KC,D0KD,D0KE,D0KF,D0KG,D0KH,D0KI,D0KJ,D0KK,D0KL,D0KM,D0KN,D0KO,D0KP,D0KQ,D0KR,D0KS";

        String[] strs = string.split(",");
        System.out.println(strs.length);
        int index = 0;
        for (String str : strs) {
            mongoManager.updateOne("images", new Document("urn", "/" + str), new Document("_id", docs.get(index).get("_id")));
            index++;
        }
//        mongoManager.deleteOneByObjectId("logs", "5b76db371bba200ca44124fe");
    }
}
