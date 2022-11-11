package com.metanonia.springmongosample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringMongoSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoSampleApplication.class, args);
    }

}
