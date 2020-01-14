package com.wayn.ssocore.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4564540297268973965L;

    private int userId;

    private String userName;

    private String password;
}
