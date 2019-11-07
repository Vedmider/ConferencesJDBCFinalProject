package com.study.persistence.DTO;

import com.study.persistence.entity.Right;

import java.util.List;

public class RoleDTO {
    private int id;
    private String roleTitle;
    private List<Right> rights;

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
}
