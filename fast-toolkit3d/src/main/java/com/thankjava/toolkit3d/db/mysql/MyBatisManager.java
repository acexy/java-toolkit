package com.thankjava.toolkit3d.db.mysql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * MySQL&MyBatis
* 依赖于 maven[com.alibaba:druid]
* 依赖于 maven[mysql:mysql-connector-java]
* 依赖于 maven[org.mybatis:mybatis]
* <p>Function: MyBatisManager</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年3月7日 下午4:29:28
* @version 1.0
 */
public interface MyBatisManager {

	/**
	 * 获取SqlSessionFactory
	* <p>Function: getSqlSessionFactory</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年1月13日 下午4:21:15
	* @version 1.0
	* @return
	 */
	public SqlSessionFactory getSqlSessionFactory();
	
	/**
	 * 获取SqlSession
	* <p>Function: getSqlSession</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年1月13日 下午4:21:34
	* @version 1.0
	* @return
	 */
	public SqlSession getSqlSession();
	
	/**
	 * 关闭SqlSession
	* <p>Function: closeSqlSession</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年1月13日 下午4:21:46
	* @version 1.0
	* @param session
	 */
	public void closeSqlSession(SqlSession session);
	
	/**
	 * 关闭并提交SqlSession
	* <p>Function: commitAndcloseSqlSession</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年1月13日 下午4:21:59
	* @version 1.0
	* @param session
	 */
	public void commitAndCloseSqlSession(SqlSession session);

}