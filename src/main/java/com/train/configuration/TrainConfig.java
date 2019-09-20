package com.train.configuration;


import com.train.Application;
import com.train.model.Train;
import com.train.repository.TrainJpaRepository;
import com.train.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class TrainConfig {

   // private static final Logger log = LoggerFactory.getLogger(Application.class);


    @Bean
    @Profile("H2DBDataService")
    public DataService dataServiceH2DB() throws SQLException {
        return new H2DBDataService(h2DBConnection());

    }

    @Bean
    public Connection h2DBConnection() {
        try {
            Class.forName("org.h2.Driver");
           Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/file:/E:/workspace/H2DB/train-db","admin","");
            return connection;
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }

    @Bean
    @Profile("MsAccessDataService")
    public DataService dataServiceMSAccess() throws SQLException {
        return new MsAccessDataService(msAccessConnection());

    }

    @Bean
    public Connection msAccessConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\workspace\\irctcTabels.accdb");
            return connection;
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }


    @Bean
    @Profile("JpaDataService")
    public DataService dataServiceJpa() {
        return new JpaTrainDataServiceImpl();
    }

    @Bean
    @Profile("inMemoryDataService")
    public DataService dataServiceInMemory() {
        return new InMemoryTrainsDataService();
    }

    @Bean
    @Profile("fileDataService")
    public DataService dataServiceFile() {
        return new FileDataServiceImp();
    }

    @Bean
    public CommandLineRunner storeTrains(TrainJpaRepository repository) {
        return (args) -> {

            String r[] = {"HMH", "SU", "BK", "ME", "GO", "JD", "AB", "ADI"};
            String r1[] = {"BK", "NA", "ME", "GO", "JD", "AB", "ADI"};
            String r2[] = {"BD", "dabvali", "odha", "HMH", "SU", "BK", "NA", "ME", "JD"};

            repository.save(new Train(2541, "Kalka Express", "HMH", "ADI", r));
            repository.save(new Train(8456, "jammu tavi", "BK", "ADI", r1));
            repository.save(new Train(9541, "Bathinda Express", "BD", "JD", r2));



        };
    }


}