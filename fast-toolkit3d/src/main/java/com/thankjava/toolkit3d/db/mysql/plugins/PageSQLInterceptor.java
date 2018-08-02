package com.thankjava.toolkit3d.db.mysql.plugins;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.PropertyException;

import com.thankjava.toolkit.reflect.ReflectHelper;
import com.thankjava.toolkit3d.vo.db.PageEntity;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts({
//        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class PageSQLInterceptor implements Interceptor {


    private String createCountSql(String sql) {
        StringBuilder sb = new StringBuilder("select count(0) from ( ");
        sb.append(sql).append(" ) t");
        return sb.toString();
    }

    public Object intercept(Invocation invocation) throws Throwable {


        if (invocation.getTarget() instanceof RoutingStatementHandler) {

            Connection connection = (Connection) invocation.getArgs()[0];
            Object[] args = invocation.getArgs();
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();

            Object parameter = parameterHandler.getParameterObject();
            if (!(parameter instanceof PageEntity)) return invocation.proceed();

            String originalSql = statementHandler.getBoundSql().getSql();
            System.out.println(createCountSql(originalSql));
            System.out.println(parameter);
            return invocation.proceed();
        }


        CachingExecutor delegate = (CachingExecutor) invocation.getTarget();
        Object[] args = invocation.getArgs();

        for (Object arg : args) {
            System.out.println(arg);
        }
        MappedStatement mappedStatement = null;
        RowBounds rowBounds = null;
        PageEntity pageEntity = null;
        for (Object arg : args) {
            if (arg instanceof MappedStatement) {
                mappedStatement = (MappedStatement) arg;
            } else if (arg instanceof RowBounds) {
                rowBounds = (RowBounds) arg;
            } else if (arg instanceof PageEntity) pageEntity = (PageEntity) arg;
        }
        if (pageEntity == null) return invocation.proceed();
        SqlSource sqlSource = mappedStatement.getSqlSource();


        System.out.println(sqlSource.getBoundSql(pageEntity));
        BoundSql boundSql = mappedStatement.getBoundSql(pageEntity);

        String sql = boundSql.getSql();
        System.out.println(sql);

//        RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) metaStatementHandler.getOriginalObject();

        // 通过反射获取到当前RoutingStatementHandler对象的delegate属性
//        StatementHandler delegateNew = (StatementHandler) ReflectHelper.getFieldVal(routingStatementHandler, "delegate");

        // 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
//        MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldVal(delegateNew, "mappedStatement");

//        BoundSql boundSql = delegate.();
//        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//        System.out.println(boundSql.getParameterObject());
//        if (SqlCommandType.SELECT == sqlCommandType) {
//
//            SqlSource sqlSource = mappedStatement.getSqlSource();
//            if (DynamicSqlSource.class.isAssignableFrom(sqlSource.getClass())) {
//
//                SqlNode sqlNode = (SqlNode) ReflectHelper.getFieldVal(sqlSource, "rootSqlNode");
//
////                BoundSql boundSqlConvert = getBoundSql(mappedStatement.getConfiguration(), boundSql.getParameterObject(), sqlNode);
////                ReflectHelper.setFieldVal(boundSql, "sql", boundSqlConvert.getSql());
//            }
//        }

//        // 拦截需要分页的SQL
//        if (mappedStatement.getId().matches(pageSqlId_RegEx)) {
//
//            // 获取到当前StatementHandler的 boundSql
//            boundSql = delegate.getBoundSql();
//
//            // 拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
//            // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
//            Object parameterObject = boundSql.getParameterObject();
//
//            if (parameterObject == null) {
//                throw new NullPointerException("parameterObject is null！");
//            } else {
//
//                // 拦截到的prepare方法参数是一个Connection对象
//                Connection connection = (Connection) invocation.getArgs()[0];
//
//                // 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
//                String sql = boundSql.getSql();
//
//                // 记录统计
//                String countSql = this.getCountSql(sql);
//
//                countSql = countSql.replaceAll("\t", " ");
//                countSql = countSql.replaceAll("\n", " ");
//                LogCvt.debug("统计 sql:" + countSql);
//
//                // 通过connection建立一个countSql对应的PreparedStatement对象。
//                PreparedStatement countStmt = connection.prepareStatement(countSql);
//
//                DefaultParameterHandler parameterHandler = new DefaultParameterHandler(
//                        mappedStatement, parameterObject, boundSql);
//                parameterHandler.setParameters(countStmt);
//
//                // 之后就是执行获取总记录数的Sql语句和获取结果了
//                ResultSet rs = countStmt.executeQuery();
//                int count = 0;
//                if (rs.next()) {
//                    count = rs.getInt(1);
//                }
//                rs.close();
//                countStmt.close();
//
//                Page page = null;
//                if (parameterObject instanceof Page) { // 参数就是Page实体
//                    page = (Page) parameterObject;
//
//                    //给当前的参数page对象设置总记录数
//                    page.setTotalCount(count);
//                } else if (parameterObject instanceof Map) {
//                    for (Entry entry : (Set<Entry>) ((Map) parameterObject)
//                            .entrySet()) {
//                        if (entry.getValue() instanceof Page) {
//                            page = (Page) entry.getValue();
//                            break;
//                        }
//                    }
//                    page.setTotalCount(count);
//                } else {
//                    // 参数为某个实体，该实体拥有Page属性
//                    Field pageField = ReflectUtil.getField(parameterObject, "page");
//
//                    if (pageField != null) {
//                        page = (Page) ReflectUtil.getFieldValue(parameterObject, "page");
//                        if (page == null) {
//                            page = new Page();
//                        }
//                        page.setTotalCount(count);
//
//                        // 通过反射，对实体对象设置分页对象
//                        ReflectUtil.setFieldValue(parameterObject, "page", page);
//                    } else {
//                        throw new NoSuchFieldException(parameterObject
//                                .getClass().getName() + "不存在 page 属性！");
//                    }
//                }
//
//                // 获取分页Sql语句
//                String pageSql = this.generatePageSql(page, sql);
//
//                pageSql = pageSql.replaceAll("\t", " ");
//                pageSql = pageSql.replaceAll("\n", " ");
//                LogCvt.debug("分页 sql:" + pageSql);
//
//                // 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
//                ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
//            }
//        }
//		}

        return invocation.proceed();
    }

    private long count() {
        return -1;
    }

    /**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置拦截器时设定的属性
     */
    public void setProperties(Properties properties) {
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
//		int index = sql.toLowerCase().indexOf(" from");
//		return "select count(0) " + sql.substring(index);
        Matcher matcher = Pattern.compile("\\s+(?i)from\\s+").matcher(sql);

        while (matcher.find()) {
            int index = matcher.start();
            if (index > -1) {
                String temp = sql.substring(index);
                if (temp.toUpperCase().contains("ORDER BY")) {
                    temp = temp.substring(0, temp.toUpperCase().indexOf("ORDER BY"));
                }
                return "select count(0) " + temp;
            }
        }
        return null;
    }


//    public static BoundSql getBoundSql(Configuration configuration, Object parameterObject, SqlNode sqlNode) {
//        DynamicContext context = new DynamicContext(configuration, parameterObject);
//        //DynamicContext context = new DynamicContext(mappedStatement.getConfiguration(), boundSql.getParameterObject());
//        //mappedStatement.getSqlSource().
//
//        sqlNode.apply(context);
//        String countextSql = context.getSql();
////		System.out.println("context.getSql():"+countextSql);
//
//        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
//        Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
//        String sql = modifyLikeSql(countextSql, parameterObject);
//        SqlSource sqlSource = sqlSourceParser.parse(sql, parameterType, context.getBindings());
//
//
//        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
//        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
//            boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
//        }
//
//        return boundSql;
//    }


//    public static String modifyLikeSql(String sql, Object parameterObject) {
////		if(Map.class.isAssignableFrom(parameterObject.getClass())) {
//////		if(parameterObject instanceof HashMap){
////		}else{
////			return sql;
////		}
//        if (!sql.toLowerCase().contains("like"))
//            return sql;
//        //sql=" and OPER_REMARK LIKE '%' || #{operRemark} || '%'  \n " +"and OPER_U_NAME LIKE #{operUName} || '%' ";
//        //原始表达式：\s\w+\sLIKE\s('%'\s\|{2})?\s*(#\{\w+\})\s*(\|{2}\s*'%')
//        // CONCAT('%',#{outletName},'%' )
//        String reg = null;
////		reg="\\s\\w+\\sLIKE\\s*('%'\\s*\\|{2}\\s*)?(#\\{\\w+\\})(\\s*\\|{2}\\s*'%')?";//"order\\s+by\\s+.+"
////		reg="\\s\\w+\\sLIKE\\s*('%'\\s*\\|{2}\\s*)?(#\\{\\w+\\})(\\s*\\|{2}\\s*'%')?(CONCAT\\(('%'\\s*\\,\\s*)?(\\s*#\\{\\w+\\})(\\s*\\,\\s*'%')\\))?";//"order\\s+by\\s+.+"
//
////		reg="\\s\\w+\\sLIKE\\s*(CONCAT\\((\\s*'%'\\s*\\,\\s*)?(\\s*(\\w*\\(*)(#|\\$)\\{(.+)\\}(\\s*\\)*)\\s*)(\\s*\\,\\s*'%'\\s*)\\))?";//"order\\s+by\\s+.+"
//        reg = "\\s\\w+\\.?\\w*\\sLIKE\\s*(CONCAT\\((\\s*'%'\\s*\\,\\s*)?(\\s*(\\w*\\(*)(#|\\$)\\{(.+)\\}(\\s*\\)*)\\s*)(\\s*\\,\\s*'%'\\s*)\\))?";//"order\\s+by\\s+.+"
////		reg=".*LIKE\\s*(CONCAT\\(('%'\\s*\\,\\s*)?(\\s*#\\{\\w+\\})(\\s*\\,\\s*'%')\\))?";//"order\\s+by\\s+.+"
//        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(sql);
////		System.out.println("sql====>" + sql);
//
//        List<String> replaceEscape = new ArrayList<String>();
//        List<String> replaceFiled = new ArrayList<String>();
//
//        while (matcher.find()) {
////			System.out.println("matcher.group()====>" + matcher.group());
//            replaceEscape.add(matcher.group());
//            int n = matcher.groupCount();
//            for (int i = 0; i <= n; i++) {
//                String output = matcher.group(i);
////                System.out.println("i=====>"+i+"\t output====>" + output);
////                if(2==i&&output!=null)
//                if (3 == i && output != null) {
//                    replaceFiled.add(output.trim());
//                }
//            }
//        }
//
//        //sql = matcher.replaceAll(reg+" 1111");
//
//        for (String s : replaceEscape) {
//            sql = sql.replace(s, s + " ESCAPE '/' ");
//        }
//        //修改参数
////		Map<String,Object> paramMab = null;
////		if(Map.class.isAssignableFrom(parameterObject.getClass())) {
////			paramMab=(Map)parameterObject;
////		}
//        MetaObject parameterObjectHandler = MetaObject.forObject(parameterObject, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
//
//        for (String s : replaceFiled) {
//            //sql=sql.replace(s, " ? ");
//            // #{operUName} -->operUName
//            String key = s.replace("#{", "").replace("}", "");
//
//            Object val = null;
//            val = parameterObjectHandler.getValue(key);
////			if(null != paramMab && !paramMab.isEmpty()) {
////				val = paramMab.get(key);
////			} else {
////				val = ReflectUtil.getFieldValue(parameterObject, key);
////			}
//            if (val != null && val instanceof String && (val.toString().contains("%") || val.toString().contains("_") || val.toString().contains("/"))) {
//                val = val.toString().replaceAll("/", "//").replaceAll("%", "/%").replaceAll("_", "/_");
//                parameterObjectHandler.setValue(key, val);
////				if(null != paramMab && !paramMab.isEmpty()) {
////					paramMab.put(key.toString(), val);
////				} else {
////					ReflectUtil.setFieldValue(parameterObject, key.toString(), val);
////				}
//            }
//        }
////		System.out.println("修改之后的sql====>" + sql);
//        return sql;
//    }

}

