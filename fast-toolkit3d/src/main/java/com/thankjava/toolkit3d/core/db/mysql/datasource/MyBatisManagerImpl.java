package com.thankjava.toolkit3d.core.db.mysql.datasource;

import java.io.Reader;

import com.thankjava.toolkit.core.utils.ClassSourceLoaderUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;

public class MyBatisManagerImpl implements MyBatisManager{

	private static SqlSessionFactory sqlSessionFactory;

	private static MyBatisManagerImpl manager = null;
	
	public static MyBatisManagerImpl getInstance(){
		if(manager == null){
			manager = new MyBatisManagerImpl();
		}
		return manager;
	}
	
	public static MyBatisManagerImpl getInstance(boolean isConfigFromSources, String configUri){
		if(manager == null){
			manager = new MyBatisManagerImpl(isConfigFromSources,configUri);
		}
		return manager;
	}
	
	/**
	 * 配置文件路径
	 */
	private String configUri = "mybatis-config.xml";
	
	/**
	 * 配置文件默认位置是否来至于项目内部资源
	 */
	private boolean isConfigFromSources = true;
	
	/**
	 * 默认MySQL初始化
	* 加载项目resources内部mybatis-config.xml文件
	* <p>Title: 构造Default</p>
	* <p>Description: </p>
	 */
	private MyBatisManagerImpl(){
		init();
	}
	
	/**
	 * 
	* MySQL初始化
	* <p>Title: </p>
	* <p>Description: </p>
	* @param isConfigFromSources 配置文件是否来着项目内部resource
	* @param configUri	配置文件位置
	 */
	private MyBatisManagerImpl(boolean isConfigFromSources, String configUri){
		this.isConfigFromSources = isConfigFromSources;
		this.configUri = configUri;
		init();
	}
	
	private void init() {
		String resource = configUri;

		if(isConfigFromSources){
			Reader reader = null;
			try {
				reader = ClassSourceLoaderUtil.getResourceAsReader(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			
		}
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
	public void closeSqlSession(SqlSession session){
		if(session != null){
			session.close();
		}
	}
	
	public void commitAndCloseSqlSession(SqlSession session){
		if(session != null){
			session.commit();
			session.close();
		}
	}
}