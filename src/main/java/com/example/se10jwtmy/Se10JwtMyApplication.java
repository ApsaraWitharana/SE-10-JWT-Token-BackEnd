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

    //entity value dto dagnn one hinda mohok idan mokktda dara tranfer krnn object dek map krgnn
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
