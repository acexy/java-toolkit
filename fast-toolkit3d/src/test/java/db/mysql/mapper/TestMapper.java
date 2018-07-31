package db.mysql.mapper;

import java.util.List;

import db.mysql.po.Test;

public interface TestMapper {

	public List<Test> select(Test test);
	public void save(Test test);
}
