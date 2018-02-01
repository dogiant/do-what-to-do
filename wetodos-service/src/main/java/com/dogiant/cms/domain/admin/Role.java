package com.dogiant.cms.domain.admin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role entity.
 * 
 * @author dogiant
 */
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = -2874206074838225371L;
	private Integer roleId;
	private String roleName;

	private Set<Privilege> privileges = new HashSet<Privilege>();
	private Set<AdminUser> adminUsers = new HashSet<AdminUser>();

	public Role() {
		super();
	}

	public Role(Integer roleId) {
		super();
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = {
			@JoinColumn(name = "model", referencedColumnName = "model"),
			@JoinColumn(name = "privilege_value", referencedColumnName = "privilege_value") })
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.REFRESH)
	public Set<AdminUser> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(Set<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}

	@Column(name = "role_name", nullable = false, length = 60)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Transient
	public boolean hasPrivilege(String model, String privilegeValue) {
		Privilege privilege = new Privilege(model, privilegeValue);
		if (this.getPrivileges().contains(privilege)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

}