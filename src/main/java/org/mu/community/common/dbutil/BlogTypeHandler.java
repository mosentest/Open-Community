package org.mu.community.common.dbutil;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.mu.community.blog.entity.BlogType;
import org.mu.community.common.enumtype.NotificationClass;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogTypeHandler extends BaseTypeHandler<BlogType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BlogType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getIdent());
	}

	@Override
	public BlogType getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return getEnum(rs.getString(columnName));
	}

	@Override
	public BlogType getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return getEnum(rs.getString(columnIndex));
	}

	@Override
	public BlogType getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return getEnum(cs.getString(columnIndex));
	}
	
	private BlogType getEnum(String value) {
		return BlogType.getEnumType(value);
	}
}
