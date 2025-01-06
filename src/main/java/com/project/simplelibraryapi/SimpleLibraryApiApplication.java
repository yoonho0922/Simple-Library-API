package com.project.simplelibraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SimpleLibraryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLibraryApiApplication.class, args);
    }

}
