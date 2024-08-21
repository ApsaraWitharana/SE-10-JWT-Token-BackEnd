package com.example.se10jwtmy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
//02
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private long uid;
    private String email;
    private String password;
    private String name;
    private String CompanyName;
    private String role;
}
