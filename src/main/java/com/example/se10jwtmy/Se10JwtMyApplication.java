package com.example.se10jwtmy;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Se10JwtMyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Se10JwtMyApplication.class, args);
    }

    //
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
