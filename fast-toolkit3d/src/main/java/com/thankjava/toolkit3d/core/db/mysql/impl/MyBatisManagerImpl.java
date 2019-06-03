package com.thankjava.toolkit3d.core.db.mysql.impl;

import com.thankjava.toolkit.core.utils.SourceLoaderUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;

class MyBatisManagerImpl implements MyBatisManager {

    private static SqlSessionFactory sqlSessionFactory;

    private static MyBatisManagerImpl manager = null;

    private MyBatisManagerImpl() {
    }

    private static String sourceName = "mybatis-config.xml";

    private static MyBatisManager init(String configName) {
        if (manager == null) {
            manager = new MyBatisManagerImpl();
        } else {
            return manager;
        }

        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(SourceLoaderUtil.getResourceAsReader(sourceName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public void closeSqlSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    public void commitAndCloseSqlSession(SqlSession session) {
        if (session != null) {
            session.commit();
            session.close();
        }
    }
}
