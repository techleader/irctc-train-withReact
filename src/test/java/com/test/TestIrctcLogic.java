package com.test;

import com.train.model.Train;
import com.train.service.MsAccessDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TestIrctcLogic {

    Connection connection = null;

    @Before
    public void createConnection() {
        try {
            connection = connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\workspace\\irctcTabels.accdb");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testRouteOfTrain() {
        String a[] = {"Bikaner", "Merta", "Gotan", "Jodhpur"};
        try {
            Assert.assertEquals(a, new MsAccessDataService(connection).getRouteOfTrain(5642));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTrainBetweenTwoStation(){
       Train t = new Train();
       t.trainNumber=8965;
       t.trainName="jammu tavi";
       t.startStation="HMH";
       t.endStation="JD";
        List<Train> list = new ArrayList<>();
        list.add(t);

        try{
            Assert.assertEquals(list,new MsAccessDataService(connection).getTrainBetweenStation("HMH","JD"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}