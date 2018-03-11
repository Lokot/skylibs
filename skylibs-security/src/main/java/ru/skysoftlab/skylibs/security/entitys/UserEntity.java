package ru.skysoftlab.skylibs.security.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ru.skysoftlab.skylibs.common.EditableEntity;
import ru.skysoftlab.skylibs.security.Roles;

@Entity
@NamedQueries({ @NamedQuery(name = "User.byName", query = "SELECT e FROM UserEntity e WHERE e.name=:name") })
public class UserEntity implements EditableEntity<Long> {

	private static final long serialVersionUID = -8557078263415603719L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String passHash;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Roles role;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}
