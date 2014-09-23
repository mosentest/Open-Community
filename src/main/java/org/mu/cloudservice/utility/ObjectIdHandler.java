package org.mu.cloudservice.utility;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.bson.types.ObjectId;

public class ObjectIdHandler extends BaseTypeHandler<ObjectId> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			ObjectId parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.toHexString());
	}

	@Override
	public ObjectId getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return new ObjectId(rs.getString(columnName));
	}

	@Override
	public ObjectId getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return new ObjectId(rs.getString(columnIndex));
	}

	@Override
	public ObjectId getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return new ObjectId(cs.getString(columnIndex));
	}
}
