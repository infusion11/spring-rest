package com.example.springrest.helperclass;

import org.springframework.stereotype.Component;

@Component
public class UserAndRole {
    private String user;
    private String roleName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
