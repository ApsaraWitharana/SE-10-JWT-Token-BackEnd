package com.example.se10jwtmy.service;

import com.example.se10jwtmy.dto.UserDTO;

//07
public interface UserService {
    int saveUser (UserDTO userDTO);
    UserDTO searchUser(String userName);
}
