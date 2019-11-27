package com.study.web.DTO;

import com.study.persistence.entity.Right;
import com.study.persistence.entity.Role;

import java.util.List;

public class RoleDTO {
    private int id;
    private String roleTitle;
    private List<Right> rights;

    public RoleDTO(Role entity){
        id = entity.getId();
        roleTitle = entity.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof RoleDTO)) {
            return false;
        }
        RoleDTO role = (RoleDTO) obj;

        return id == role.getId()
                && (roleTitle == null ? role.getRoleTitle() == null : roleTitle.equals(role.getRoleTitle()))
                && (rights == null ? role.getRights() == null : rights.equals(role.getRights()));
    }

    @Override
    public int hashCode() {
        int result = 10;
        result = 31 * result + id + (roleTitle == null ? 0 : roleTitle.hashCode()) + (rights == null ? 0 : rights.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Role ID " + id + ", roleTitle " + roleTitle;
    }
}
