package com.thankjava.toolkit3d.core.db.mysql.plugins;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.thankjava.toolkit.core.reflect.ReflectHelper;
import com.thankjava.toolkit3d.bean.db.PageEntity;
import com.thankjava.toolkit3d.bean.db.Sort;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class PageSQLInterceptor implements Interceptor {


    public Object intercept(Invocation invocation) throws Throwable {

        if (invocation.getTarget() instanceof RoutingStatementHandler) {

            Connection connection = (Connection) invocation.getArgs()[0];
            Object[] args = invocation.getArgs();
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();
            Object parameter = parameterHandler.getParameterObject();
            if (!(parameter instanceof PageEntity)) return invocation.proceed();

            BoundSql boundSql = statementHandler.getBoundSql();
            RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
            PreparedStatementHandler preparedStatementHandler = (PreparedStatementHandler) ReflectHelper.getFieldVal(routingStatementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldVal(preparedStatementHandler, "mappedStatement");

            // 设置分页总条数
            setPageTotalCount(connection, mappedStatement, boundSql);

            // 修改语句变成分页语句
            ReflectHelper.setFieldVal(boundSql, "sql", createPageSql(boundSql.getSql(), (PageEntity) boundSql.getParameterObject()));

        } else {

            Object[] args = invocation.getArgs();
            PageEntity pageEntity = null;

            for (Object arg : args) {
                if (arg instanceof PageEntity) {
                    pageEntity = (PageEntity) arg;
                }
            }

            if (pageEntity != null) {
                ArrayList result = (ArrayList) invocation.proceed();
                pageEntity.setList(result);
                return result;
            }

        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

    /**
     * 生成统计总条数SQL
     *
     * @param sql
     * @return
     */
    private String createCountSql(String sql) {
        StringBuilder sb = new StringBuilder("select count(0) from ( ");
        sb.append(sql).append(" ) as t");
        return sb.toString();
    }

    /**
     * 执行总条数查询并设置值
     *
     * @param connection
     * @param mappedStatement
     * @param boundSql
     */
    private void setPageTotalCount(Connection connection, MappedStatement mappedStatement, BoundSql boundSql) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(createCountSql(boundSql.getSql()));
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler.setParameters(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ((PageEntity) boundSql.getParameterObject()).setTotalCount(resultSet.getLong(1));
            }
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String createPageSql(String sql, PageEntity pageEntity) {
        if (sql.toLowerCase().contains(" limit ")) {
            return sql;
        }

        StringBuilder sb = new StringBuilder(sql);

        List<Sort> sorts = pageEntity.getSorts();
        if (sorts.size() > 0) {
            sb.append(" order by ");
            for (Sort sort : sorts) {
                sb.append(sort.getColumn()).append(" ").append(sort.getSortType()).append(" , ");
            }
            sb.deleteCharAt(sb.length() - 2);
        }

        int pageSize = pageEntity.getPageSize();
        int pageNumber = pageEntity.getPageNumber();

        sb.append(" limit ");
        sb.append((pageNumber - 1) * pageSize);
        sb.append(" , ").append(pageSize);

        return sb.toString();
    }

}

