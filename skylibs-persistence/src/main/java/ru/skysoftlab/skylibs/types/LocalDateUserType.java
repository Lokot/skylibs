package ru.skysoftlab.skylibs.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.EnhancedUserType;

public class LocalDateUserType implements EnhancedUserType, Serializable {

	private static final long serialVersionUID = 5692920885076824115L;
	private static final int[] SQL_TYPES = new int[] { Types.BIGINT };
	private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return LocalDate.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		LocalDate dtx = (LocalDate) x;
		LocalDate dty = (LocalDate) y;
		return dtx.equals(dty);
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object timestamp = StandardBasicTypes.LONG.nullSafeGet(resultSet, names, session, owner);
		if (timestamp == null) {
			return null;
		}
		Long epochDay = (Long) timestamp;
		return LocalDate.ofEpochDay(epochDay);
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.LONG.nullSafeSet(preparedStatement, null, index, session);
		} else {
			LocalDate ld = ((LocalDate) value);
			Long epochDay = ld.toEpochDay();
			StandardBasicTypes.LONG.nullSafeSet(preparedStatement, epochDay, index, session);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public String objectToSQLString(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toXMLString(Object object) {
		return ((LocalDate) object).format(formatter);
	}

	@Override
	public Object fromXMLString(String string) {
		return LocalDate.parse(string, formatter);
	}

}
