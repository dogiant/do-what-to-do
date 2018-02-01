package com.dogiant.cms.domain.admin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

	private static final long serialVersionUID = -6650372611123229467L;
	private PrivilegePK id;
	private String name;
	private Set<Role> roles = new HashSet<Role>();
	private SystemModel systemModel;

	public Privilege() {
	}

	public Privilege(String model, String privilegeValue) {
		this.id = new PrivilegePK(model, privilegeValue);
	}

	public Privilege(String model, String privilegeValue, String name) {
		this.id = new PrivilegePK(model, privilegeValue);
		this.name = name;
	}

	@EmbeddedId
	public PrivilegePK getId() {
		return id;
	}

	public void setId(PrivilegePK id) {
		this.id = id;
	}

	@ManyToMany(mappedBy = "privileges", cascade = CascadeType.REFRESH)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(length = 36, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "model", insertable = false, updatable = false)
	public SystemModel getSystemModel() {
		return systemModel;
	}

	public void setSystemModel(SystemModel systemModel) {
		this.systemModel = systemModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Privilege other = (Privilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
