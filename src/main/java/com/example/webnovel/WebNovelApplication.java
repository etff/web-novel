package com.example.webnovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class WebNovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebNovelApplication.class, args);
    }

}
