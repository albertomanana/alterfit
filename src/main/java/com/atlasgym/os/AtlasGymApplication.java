package com.atlasgym.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AtlasGymApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtlasGymApplication.class, args);
    }
}
