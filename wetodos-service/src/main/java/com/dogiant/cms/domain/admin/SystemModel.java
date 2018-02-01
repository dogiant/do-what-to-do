package com.dogiant.cms.domain.admin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "system_model")
public class SystemModel implements Serializable {

	private static final long serialVersionUID = -6913430138639664876L;
	private String model;
	private String name;
	private Set<Privilege> privileges = new HashSet<Privilege>();

	@OneToMany(mappedBy = "systemModel", cascade = { CascadeType.REFRESH,
			CascadeType.PERSIST }, fetch = FetchType.EAGER)
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public void addPrivilege(Privilege privilege) {
		if (!this.privileges.contains(privilege)) {
			this.privileges.add(privilege);
			privilege.setSystemModel(this);
		}
	}

	@Id
	@Column(length = 36)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(length = 36, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SystemModel(String model, String name) {
		this.model = model;
		this.name = name;
	}

	public SystemModel() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		final SystemModel other = (SystemModel) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

}
