package com.train.service;

import com.train.model.Train;

import java.util.List;

public interface DataService {

    List<Train> getAllTrain();

    List<Train> getTrainBetweenStation(String from, String to);

    void saveNewTrain(Train train);

    List<Train> getAllTrainOnStation(String station);

    String[] getRouteOfTrain(int trainNumber);

    void updateTrain(Train train);

    Train getTrain(int TrainNumber);

    void setRoute(String route[], int trainNumber);
    //String[] getRoute(int trainNumber);
}
