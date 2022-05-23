package com.tz.jdbc.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通过传ResultSet对象对每一条记录通过泛型映射成对应的对象，使用的是接口的匿名内部类 类描述：
 * @author 南天
 *
 * @Date 2017年4月18日
 */
public interface RowMapper<T> {

	public T mappingRow(ResultSet rs, int rownum) throws SQLException;
}
