package com.websiteshop.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class ChangePasswordDto {
    public String username;
    public String password;
    public String confirmPassword;
    public String currentPassword;

}
