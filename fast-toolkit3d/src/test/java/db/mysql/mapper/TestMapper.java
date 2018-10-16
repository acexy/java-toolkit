package db.mysql.mapper;

import java.util.List;

import com.thankjava.toolkit3d.bean.db.PageEntity;
import db.mysql.po.Test;

public interface TestMapper {

    public List<Test> select(Test test);

    public void save(Test test);

    public List<Test> selectByPage(PageEntity<Test> testPageEntity);
}
