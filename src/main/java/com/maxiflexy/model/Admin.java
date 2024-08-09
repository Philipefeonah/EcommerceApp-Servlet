package com.maxiflexy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin {

    private Long adminID;
    private String fullName;
    private String email;
    private String password;
    private String adminToken;


    public Admin(String fullName, String email, String password, String adminToken) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.adminToken = adminToken;
    }

    public Admin() {
    }


}
