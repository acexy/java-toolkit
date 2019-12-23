package com.thankjava.toolkit3d.core.db.mysql.impl;

import com.thankjava.toolkit.core.utils.SourceLoaderUtil;
import com.thankjava.toolkit3d.core.db.mysql.BasicFastToolkit3dMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;

import java.util.HashMap;
import java.util.Map;

class MyBatisManagerImpl implements MyBatisManager {

    private static SqlSessionFactory sqlSessionFactory;

    private static MyBatisManagerImpl manager = null;

    private MyBatisManagerImpl() {
    }

    private static Map<Object, SqlSession> sessions = new HashMap<>();

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


    @Override
    public SqlSession newSqlSession() {
        return sqlSessionFactory.openSession();
    }

    @Override
    public void closeSqlSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public void commit(SqlSession session) {
        if (session != null) {
            session.commit();
        }
    }

    @Override
    public void commitAndCloseSqlSession(SqlSession session) {
        if (session != null) {
            session.commit();
            session.close();
        }
    }

    @Override
    public <T> T getMapper(Class<? extends BasicFastToolkit3dMapper> t) {
        SqlSession session = newSqlSession();
        T mapper = null;
        try {
            mapper = (T)session.getMapper(t);
            if (mapper == null) {
                session.close();
            } else {
                sessions.put(mapper, session);
            }
        } catch (Throwable e) {
            session.close();
        }

        return mapper;
    }

    @Override
    public void closeSqlSession(BasicFastToolkit3dMapper mapper) {
        SqlSession session = sessions.get(mapper);
        if (session != null) {
            session.close();
            sessions.remove(mapper);
            mapper = null;
        }
    }

    @Override
    public void commitAndCloseSqlSession(BasicFastToolkit3dMapper mapper) {
        SqlSession session = sessions.get(mapper);
        if (session != null) {
            session.commit();
            session.close();
            sessions.remove(mapper);
            mapper = null;

        }
    }

    @Override
    public void commit(BasicFastToolkit3dMapper mapper) {
        SqlSession session = sessions.get(mapper);
        if (session != null) {
            session.commit();
        }
    }

    @Override
    public void rollback(BasicFastToolkit3dMapper mapper) {
        SqlSession session = sessions.get(mapper);
        if (session != null) {
            session.rollback();
        }
    }

}
