package com.hoserdude.toboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Our app's entrypoint, because it is at the root of the package
 * hierarchy we don't have to tell Spring where to ComponentScan.
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
