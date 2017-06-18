package ru.skysoftlab.skylibs.types;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;
import org.joda.time.Duration;

public class DurationUserType implements UserType, Serializable {

	private static final long serialVersionUID = 8008841901166096613L;

	private static final int[] SQL_TYPES = new int[] { Types.BIGINT };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return Duration.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		Duration dtx = (Duration) x;
		Duration dty = (Duration) y;
		return dtx.equals(dty);
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object duration = StandardBasicTypes.LONG.nullSafeGet(resultSet, names, session, owner);
		if (duration == null) {
			return null;
		}
		return new Duration((Long) duration);
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.LONG.nullSafeSet(preparedStatement, null, index, session);
		} else {
			Duration duration = ((Duration) value);
			StandardBasicTypes.LONG.nullSafeSet(preparedStatement, duration.getMillis(), index, session);
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

//	@Override
//	public String objectToSQLString(Object object) {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public String toXMLString(Object object) {
//		return object.toString();
//	}
//
//	@Override
//	public Object fromXMLString(String string) {
//		return new LocalDateTime(string);
//	}

}
