package org.mu.cloudservice.enumtype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class NTTypeHandler extends BaseTypeHandler<NotificationType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			NotificationType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getIdent());
	}

	@Override
	public NotificationType getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return getEnum(rs.getString(columnName));
	}

	@Override
	public NotificationType getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return getEnum(rs.getString(columnIndex));
	}

	@Override
	public NotificationType getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return getEnum(cs.getString(columnIndex));
	}
	
	private NotificationType getEnum(String value) {
		return NotificationType.getEnumType(value);
	}
}
