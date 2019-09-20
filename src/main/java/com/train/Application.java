package com.train;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {

        //System.setProperty("spring.profiles.active","inMemoryDataService");
        //System.setProperty("spring.profiles.active","fileDataService");
       // System.setProperty("spring.profiles.active", "MsAccessDataService");
        System.setProperty("spring.profiles.active", "H2DBDataService");
        //System.setProperty("spring.profiles.active","JpaDataService");
        SpringApplication.run(Application.class, args);

    }


}


