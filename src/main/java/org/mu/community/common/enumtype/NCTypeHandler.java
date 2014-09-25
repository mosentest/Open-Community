package org.mu.community.common.enumtype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class NCTypeHandler extends BaseTypeHandler<NotificationClass> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			NotificationClass parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getIdent());
	}

	@Override
	public NotificationClass getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return getEnum(rs.getString(columnName));
	}

	@Override
	public NotificationClass getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return getEnum(rs.getString(columnIndex));
	}

	@Override
	public NotificationClass getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return getEnum(cs.getString(columnIndex));
	}
	
	private NotificationClass getEnum(String value) {
		return NotificationClass.getEnumType(value);
	}
}
