package db.mysql.mapper;

import com.thankjava.toolkit3d.bean.db.PageEntity;
import com.thankjava.toolkit3d.core.db.mysql.BasicFastToolkit3dMapper;
import db.mysql.po.Test;

import java.util.List;

/**
 * @Desc: TODO
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-05
 **/
public interface BasicMapper<T> extends BasicFastToolkit3dMapper {

    List<T> select(T t);

    void save(T t);

    List<T> selectByPage(PageEntity<T> page);
}
