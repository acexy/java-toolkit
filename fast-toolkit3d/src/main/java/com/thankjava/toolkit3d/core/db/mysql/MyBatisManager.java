package com.thankjava.toolkit3d.core.db.mysql;

import org.apache.ibatis.session.SqlSession;

/**
 * MySQL&MyBatis
 * 依赖于 maven[com.alibaba:druid]
 * 依赖于 maven[mysql:mysql-connector-java]
 * 依赖于 maven[org.mybatis:mybatis]
 * <p>Function: MyBatisManager</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年3月7日 下午4:29:28
 */
public interface MyBatisManager {

    /**
     * 获取SqlSession
     * <p>Function: newSqlSession</p>
     * <p>Description: </p>
     *
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月13日 下午4:21:34
     * @version 1.0
     */
    SqlSession newSqlSession();

    /**
     * 关闭SqlSession
     * <p>Function: closeSqlSession</p>
     * <p>Description: </p>
     *
     * @param session
     * @author acexy@thankjava.com
     * @date 2016年1月13日 下午4:21:46
     * @version 1.0
     */
    void closeSqlSession(SqlSession session);

    /**
     * 提交事物
     * @param session
     */
    void commit(SqlSession session);

    /**
     * 关闭并提交SqlSession
     * <p>Function: commitAndcloseSqlSession</p>
     * <p>Description: </p>
     *
     * @param session
     * @author acexy@thankjava.com
     * @date 2016年1月13日 下午4:21:59
     * @version 1.0
     */
    void commitAndCloseSqlSession(SqlSession session);

    /**
     * 获取 Mapper 实例
     *
     * @param t
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<? extends BasicFastToolkit3dMapper>  t);

    /**
     * 关闭Mapper实例对应的session信息
     * @param mapper
     */
    void closeSqlSession(BasicFastToolkit3dMapper mapper);

    /**
     * 关闭并提交Mapper实例对应的session信息
     * @param mapper
     */
    void commitAndCloseSqlSession(BasicFastToolkit3dMapper mapper);

    /**
     * 提交事物
     * @param mapper
     */
    void commit(BasicFastToolkit3dMapper mapper);

    /**
     * 获取 Mapper对应的SqlSession
     * @param mapper
     * @return
     */
    SqlSession getMapperSqlSession(BasicFastToolkit3dMapper mapper);

}