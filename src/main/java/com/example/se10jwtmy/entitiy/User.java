package com.example.se10jwtmy.entitiy;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
//05
@Entity
@Table(name="systemuser")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@Setter
//@Getter
//@ToString(exclude ="password")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    private String email;
    private String password;
    private String name;
    private String CompanyName;
    private String role;

}
