package com.train.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Train {

    @Id
    public int trainNumber;
    public String trainName;
    public String startStation;
    public String endStation;
    public String route[];


    public Train() {

    }

    public Train(int trainNumber, String trainName, String startStation, String endStation, String[] route) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.startStation = startStation;
        this.endStation = endStation;
        this.route = route;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String[] getRoute() {
        return route;
    }

    public void setRoute(String[] route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", trainName='" + trainName + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", route=" + Arrays.toString(route) +
                '}';
    }
}
