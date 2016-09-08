package org.thankjava.toolkit3d.db.mongodb.datasource;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.bson.Document;
import org.thankjava.toolkit.resource.SourceLoader;
import org.thankjava.toolkit3d.db.mongodb.MongoDBManager;
import org.thankjava.toolkit3d.fastjson.FastJson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

public class MongoDriverManager implements MongoDBManager{

	private static MongoDatabase mongoDatabase = null;
	
	final String OPERATOR$SET = "$set";
	
	static {
		Reader reader = null;
		MongoClient mongoClient = null;
		try {
			Properties props = new Properties();
			reader = SourceLoader.getResourceAsReader("mongodb.properties");
			props.load(reader);

			Builder build = new Builder();
			build.connectionsPerHost(Integer.valueOf(props.getProperty("mongo.pool.connectionsPerHost")));
			build.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(props.getProperty("mongo.pool.threadsAllowedToBlockForConnectionMultiplier")));
			build.maxWaitTime(Integer.valueOf(props.getProperty("mongo.pool.maxWaitTime")));
			build.connectTimeout(Integer.valueOf(props.getProperty("mongo.pool.connectTimeout")));
			MongoClientOptions myOptions = build.build();
			
			String databaseName = props.getProperty("mongo.databaseName");
			String uname = props.getProperty("mongo.uname");
			String pwd = props.getProperty("mongo.pwd");
			
			if(uname == null || uname.length() == 0 || pwd == null || pwd.length() == 0){
				mongoClient = new MongoClient(new ServerAddress(props.getProperty("mongo.host"), Integer.valueOf(props.getProperty("mongo.port"))), myOptions);
			}else{
				MongoCredential credentia = MongoCredential.createCredential(uname, databaseName, pwd.toCharArray());
				mongoClient = new MongoClient(new ServerAddress(props.getProperty("mongo.host"), Integer.valueOf(props.getProperty("mongo.port"))),Arrays.asList(credentia), myOptions);
			}
			mongoDatabase = mongoClient.getDatabase(databaseName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader != null){
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
	* @author zhaoxy@thankjava.com
	* @date 2016年3月8日 上午11:33:51
	* @version 1.0
	* @param docName
	* @return
	 */
	private MongoCollection<Document> getDBCollection(String docName){
		if(docName == null || docName.length() == 0){
			return null;
		}
		return mongoDatabase.getCollection(docName);
	}
	
	
	private Document t2Doc(Object t){
		if(t == null){
			return null;
		}
		return new Document(FastJson.toMap(t));
	}
	
	private Object doc2T(Document doc,Class<?> clazz){
		if(doc == null){
			return null;
		}
		return FastJson.toObject(FastJson.toJsonString(doc), clazz);
	}
	
	@Override
	public long count(String docName,Document docFilter) {
		MongoCollection<Document> collection = getDBCollection(docName);
		if(docFilter == null){
			return collection.count();
		}
		return collection.count(docFilter);
	}

	@Override
	public boolean insertOne(String docName, Document doc) {
		if(doc == null){
			return false;
		}
		MongoCollection<Document> collection = getDBCollection(docName);
		try {
			collection.insertOne(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertMany(String docName, List<Document> docs) {
		if(docs == null || docs.size() == 0){
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
	public boolean insertOne(String docName, Object t) {
		if(t == null){
			return false;
		}
		MongoCollection<Document> collection = getDBCollection(docName);
		try {
			collection.insertOne(t2Doc(t));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean insertMany(List<Object> ts, String docName) {
		if(ts == null || ts.size() == 0){
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

	private MongoCursor<Document> baseFind(String docName, Document docFilter){
		MongoCollection<Document> collection = getDBCollection(docName);
		FindIterable<Document> result;
		if(docFilter == null){
			result = collection.find();
		}else{
			result = collection.find(docFilter);
		}
		MongoCursor<Document> cursor = null;
		if(result != null){
			cursor = result.iterator();
		}
		return cursor;
	}
	
	@Override
	public List<Document> findMany(String docName, Document docFilter) {
		List<Document> docs = new ArrayList<Document>();
		MongoCursor<Document> cursor = baseFind(docName, docFilter);
		while(cursor.hasNext()){
			docs.add(cursor.next());
		}
		cursor.close();
		return docs;
	}
	
	@Override
	public List<Document> findMany(String docName, Object tFilter) {
		if(tFilter == null){
			return findMany(docName, null);
		}
		return findMany(docName, t2Doc(tFilter));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findMany(String docName,Document docFilter,Class<T> clazz) {
		List<Document> docs = findMany(docName, docFilter);
		List<T> objs = new ArrayList<T>();
		for (Document doc : docs) {
			objs.add((T)doc2T(doc, clazz));
		}
		return objs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findMany(String docName,Object tFilter,Class<T> clazz) {
		List<Document> docs = findMany(docName, tFilter);
		List<T> objs = new ArrayList<T>();
		for (Document doc : docs) {
			objs.add((T)doc2T(doc, clazz));
		}
		return objs;
	}

	
	private UpdateResult baseUpdateOne(String docName, Document doc, Document docFilter){
		if(doc == null || doc.size() == 0){
			return null;
		}
		if(docFilter == null){
			docFilter = new Document();
		}
		MongoCollection<Document> collection = getDBCollection(docName);
		return collection.updateOne(docFilter, new Document(OPERATOR$SET,doc));
	}
	
	private UpdateResult baseUpdateMany(String docName, Document doc, Document docFilter){
		if(doc == null || doc.size() == 0){
			return null;
		}
		if(docFilter == null){
			docFilter = new Document();
		}
		MongoCollection<Document> collection = getDBCollection(docName);
		return collection.updateMany(docFilter, new Document(OPERATOR$SET,doc));
	}
	
	@Override
	public boolean updateOne(String docName, Document doc, Document docFilter) {
		UpdateResult upResult = baseUpdateOne(docName, doc, docFilter);
		if(upResult == null){
			return false;
		}
		long matched = upResult.getMatchedCount();//匹配上的数据条数
		long modified = upResult.getModifiedCount();//已修改的数据条数
		if(matched == 1 && modified == 1){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateMany(String docName, Document doc, Document docFilter) {
		UpdateResult upResult = baseUpdateMany(docName, doc, docFilter);
		if(upResult == null){
			return false;
		}
		long matched = upResult.getMatchedCount();//匹配上的数据条数
		long modified = upResult.getModifiedCount();//已修改的数据条数
		if(matched > 0 && modified > 0){
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
	public Document findOne(String docName, Document docFilter) {
		MongoCursor<Document> cursor = baseFind(docName, docFilter);
		Document doc = null;
		if(cursor.hasNext()){
			doc = cursor.next();
		}
		cursor.close();
		return doc;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T findOne(String docName, Object tFilter, Class<T> clazz) {
		Document doc = findOne(docName, t2Doc(tFilter));
		return (T) doc2T(doc, clazz);
	}


	@Override
	public Document findOne(String docName, Object t) {
		return findOne(docName, t2Doc(t));
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T findOne(String docName, Document docFilter, Class<T> clazz) {
		Document doc = findOne(docName, docFilter);
		return (T) doc2T(doc, clazz);
	}
	
}
