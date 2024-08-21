package com.example.se10jwtmy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
//02
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String CompanyName;
    private String role;
}
