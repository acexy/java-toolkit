package com.thankjava.toolkit3d.db.mongodb.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.*;
import com.mongodb.client.result.DeleteResult;
import com.thankjava.toolkit3d.vo.db.PageEntity;
import com.thankjava.toolkit3d.vo.db.Sort;
import com.thankjava.toolkit3d.vo.db.SortType;
import org.bson.BSON;
import org.bson.Document;
import com.thankjava.toolkit.resource.SourceLoader;
import com.thankjava.toolkit3d.db.mongodb.MongoDBManager;
import com.thankjava.toolkit3d.fastjson.FastJson;

import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MongoDriverManager implements MongoDBManager {

    private static MongoDatabase mongoDatabase = null;

    private final static String OPERATOR_SET = "$set";

    private final static String OBJECT_ID_KEY = "_id";

    private static MongoDBManager manager = null;

    private MongoDriverManager() {
        init(SourceLoader.getResourceAsReader("mongodb.properties"));
        manager = this;
    }

    private MongoDriverManager(String configFilePath) {
        try {
            init(new FileReader(configFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        manager = this;
    }

    /**
     * 获取单例模式的mongo驱动
     * 配置文件自动加载工程内部 classpath下 mongodb.properties
     *
     * @return
     */
    public static MongoDBManager getSingleton() {
        if (manager == null) {
            new MongoDriverManager();
        }
        return manager;
    }

    /**
     * 获取单例模式的mongo驱动
     *
     * @param filePath 配置文件源
     * @return
     */
    public static MongoDBManager getSingleton(String filePath) {
        if (manager == null) {
            new MongoDriverManager(filePath);
        }
        return manager;
    }

    private void init(Reader reader) {
        try {
            MongoClient mongoClient = null;

            Properties props = new Properties();
            props.load(reader);

            Builder build = new Builder();
            build.connectionsPerHost(Integer.valueOf(props.getProperty("mongo.pool.connectionsPerHost")));
            build.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(props.getProperty("mongo.pool.threadsAllowedToBlockForConnectionMultiplier")));
            build.maxWaitTime(Integer.valueOf(props.getProperty("mongo.pool.maxWaitTime")));
            build.connectTimeout(Integer.valueOf(props.getProperty("mongo.pool.connectTimeout")));

            MongoClientURI mongoClientURI = new MongoClientURI(props.getProperty("mongo.connString"), build);
            mongoClient = new MongoClient(mongoClientURI);
            mongoDatabase = mongoClient.getDatabase(mongoClientURI.getDatabase());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文档（表）
     * <p>Function: getDBCollection</p>
     * <p>Description: </p>
     *
     * @param docName
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月8日 上午11:33:51
     * @version 1.0
     */
    private MongoCollection<Document> getDBCollection(String docName) {
        if (docName == null || docName.length() == 0) {
            return null;
        }
        return mongoDatabase.getCollection(docName);
    }


    private Document t2Doc(Object t) {
        if (t == null) {
            return null;
        }
        Document doc = Document.parse(FastJson.toJSONString(t));
        if (doc.get(OBJECT_ID_KEY) != null) {
            doc.put(OBJECT_ID_KEY, new ObjectId((String) doc.get(OBJECT_ID_KEY)));
        }
        return doc;
    }

    private <T> T doc2T(Document doc, Class<T> clazz) {
        if (doc == null) {
            return null;
        }
        Object objectId = doc.get(OBJECT_ID_KEY);
        if (objectId != null && objectId instanceof ObjectId) {
            doc.put(OBJECT_ID_KEY, ((ObjectId) objectId).toHexString());
        }
        return FastJson.toObject(FastJson.toJSONString(doc), clazz);
    }

    @Override
    public long count(String docName, Document docFilter) {
        MongoCollection<Document> collection = getDBCollection(docName);
        if (docFilter == null) {
            return collection.count();
        }
        return collection.count(docFilter);
    }

    @Override
    public String insertOne(String docName, Document doc) {
        if (doc == null) {
            return null;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        try {
            collection.insertOne(doc);
            return doc.getObjectId(OBJECT_ID_KEY).toHexString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertMany(String docName, List<Document> docs) {
        if (docs == null || docs.size() == 0) {
            return false;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        try {
            collection.insertMany(docs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String insertOne(String docName, Object t) {
        if (t == null) {
            return null;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        try {
            Document doc = t2Doc(t);
            collection.insertOne(doc);
            return doc.getObjectId(OBJECT_ID_KEY).toHexString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertMany(List<Object> ts, String docName) {
        if (ts == null || ts.size() == 0) {
            return false;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        try {
            List<Document> docs = new ArrayList<Document>();
            for (Object t : ts) {
                docs.add(t2Doc(t));
            }
            collection.insertMany(docs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Document findByObjectId(String docName, String objectHexString) {
        if (objectHexString == null) {
            return null;
        }
        ObjectId objectId = new ObjectId(objectHexString);
        Document doc = new Document();
        doc.put(OBJECT_ID_KEY, objectId);

        MongoCursor<Document> cursor = baseFind(docName, doc);
        doc = null;
        if (cursor.hasNext()) {
            doc = cursor.next();
        }
        return doc;
    }

    @Override
    public <T> T findByObjectId(String docName, String objectHexString, Class<T> clazz) {
        Document doc = findByObjectId(docName, objectHexString);
        return doc2T(doc, clazz);
    }

    private MongoCursor<Document> baseFind(String docName, Document docFilter) {
        MongoCollection<Document> collection = getDBCollection(docName);
        FindIterable<Document> result;
        if (docFilter == null) {
            result = collection.find();
        } else {
            result = collection.find(docFilter);
        }
        MongoCursor<Document> cursor = null;
        if (result != null) {
            cursor = result.iterator();
        }
        return cursor;
    }

    @Override
    public List<Document> findMany(String docName, Document docFilter) {
        List<Document> docs = new ArrayList<Document>();
        MongoCursor<Document> cursor = baseFind(docName, docFilter);
        while (cursor.hasNext()) {
            docs.add(cursor.next());
        }
        cursor.close();
        return docs;
    }

    @Override
    public List<Document> findMany(String docName, Object tFilter) {
        if (tFilter == null) {
            return findMany(docName, null);
        }
        return findMany(docName, t2Doc(tFilter));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findMany(String docName, Document docFilter, Class<T> clazz) {
        List<Document> docs = findMany(docName, docFilter);
        List<T> objs = new ArrayList<T>();
        for (Document doc : docs) {
            objs.add(doc2T(doc, clazz));
        }
        return objs;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findMany(String docName, Object tFilter, Class<T> clazz) {
        List<Document> docs = findMany(docName, tFilter);
        List<T> objs = new ArrayList<T>();
        for (Document doc : docs) {
            objs.add(doc2T(doc, clazz));
        }
        return objs;
    }


    private UpdateResult baseUpdateOne(String docName, Document doc, Document docFilter) {
        if (doc == null || doc.size() == 0) {
            return null;
        }
        if (docFilter == null) {
            docFilter = new Document();
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        return collection.updateOne(docFilter, new Document(OPERATOR_SET, doc));
    }

    private DeleteResult baseDeleteOne(String docName, Document doc) {
        if (doc == null || doc.size() == 0) {
            return null;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        return collection.deleteOne(doc);
    }

    private DeleteResult baseDeleteMany(String docName, Document doc) {
        if (doc == null || doc.size() == 0) {
            return null;
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        return collection.deleteOne(doc);
    }

    private UpdateResult baseUpdateMany(String docName, Document doc, Document docFilter) {
        if (doc == null || doc.size() == 0) {
            return null;
        }
        if (docFilter == null) {
            docFilter = new Document();
        }
        MongoCollection<Document> collection = getDBCollection(docName);
        return collection.updateMany(docFilter, new Document(OPERATOR_SET, doc));
    }

    @Override
    public boolean updateOne(String docName, Document doc, Document docFilter) {
        UpdateResult upResult = baseUpdateOne(docName, doc, docFilter);
        if (upResult == null) {
            return false;
        }
        long matched = upResult.getMatchedCount();//匹配上的数据条数
        long modified = upResult.getModifiedCount();//已修改的数据条数
        if (matched == 1 && modified == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOneByObjectId(String docName, Document doc, String objectHexString) {
        if (objectHexString == null) {
            return false;
        }
        Document docFilter = new Document(OBJECT_ID_KEY, new ObjectId(objectHexString));
        return updateOne(docName, doc, docFilter);
    }

    @Override
    public boolean updateOneByObjectId(String docName, Object t, String objectHexString) {
        if (objectHexString == null) {
            return false;
        }
        return updateOneByObjectId(docName, t2Doc(t), objectHexString);
    }

    @Override
    public boolean updateMany(String docName, Document doc, Document docFilter) {
        UpdateResult upResult = baseUpdateMany(docName, doc, docFilter);
        if (upResult == null) {
            return false;
        }
        long matched = upResult.getMatchedCount();//匹配上的数据条数
        long modified = upResult.getModifiedCount();//已修改的数据条数
        if (matched > 0 && modified > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOne(String docName, Object t, Object tFilter) {
        return updateOne(docName, t2Doc(t), t2Doc(tFilter));
    }

    @Override
    public boolean updateMany(String docName, Object t, Object tFilter) {
        return updateMany(docName, t2Doc(t), t2Doc(tFilter));
    }

    @Override
    public <T> void findByPage(String docName, PageEntity<T> pageEntity) {

        if (docName == null || pageEntity == null) return;
        MongoCollection<Document> collection = getDBCollection(docName);
        pageEntity.setTotalCount(this.count(docName, t2Doc(pageEntity.getQueryCondition())));
        FindIterable findIterable = collection.find(t2Doc(pageEntity.getQueryCondition())).skip(pageEntity.getPageSize() * (pageEntity.getPageNumber() - 1)).limit(pageEntity.getPageSize());
        if (pageEntity.getSorts().size() > 0) {
            List<Sort> sorts = pageEntity.getSorts();
            Document sortCondition = new Document();
            for (Sort sort : sorts) {
                sortCondition.put(sort.getColumn(), sort.getSortType().code);
            }
            findIterable.sort(sortCondition);
        }

        List<Document> docs = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            docs.add(cursor.next());
        }
        cursor.close();

        ArrayList<T> objs = new ArrayList<T>();

        for (Document doc : docs) {
            objs.add(doc2T(doc, pageEntity.getTClass()));
        }
        pageEntity.setList(objs);
    }

    @Override
    public void delOneByObjectId(String docName, String objectHexString) {
        baseDeleteOne(docName,new Document(OBJECT_ID_KEY,new ObjectId(objectHexString)));
    }


    @Override
    public Document findOne(String docName, Document docFilter) {
        MongoCursor<Document> cursor = baseFind(docName, docFilter);
        Document doc = null;
        if (cursor.hasNext()) {
            doc = cursor.next();
        }
        cursor.close();
        return doc;
    }


    @Override
    public <T> T findOne(String docName, Object tFilter, Class<T> clazz) {
        Document doc = findOne(docName, t2Doc(tFilter));
        return doc2T(doc, clazz);
    }


    @Override
    public Document findOne(String docName, Object t) {
        return findOne(docName, t2Doc(t));
    }


    @Override
    public <T> T findOne(String docName, Document docFilter, Class<T> clazz) {
        Document doc = findOne(docName, docFilter);
        return doc2T(doc, clazz);
    }

}
