package com.train.service;

import com.train.model.Train;

import java.util.ArrayList;
import java.util.List;


public class InMemoryTrainsDataService implements DataService {

    List<Train> trainList = new ArrayList();


    public InMemoryTrainsDataService() {

        String r[] = {"HMH", "SU", "BK", "NA", "ME", "JU"};
        String r1[] = {"HMH", "SU", "Odha", "LU", "BK"};
        String r2[] = {"BK", "NA", "ME", "GO", "JU"};
        String r3[] = {"JP", "FU", "AJM", "BHL", "CT", "MV", "UDI"};

        Train t1 = new Train(2541, "Kalka Express", "HMH", "JU", r);
        Train t2 = new Train(8954, "Passenger", "HMH", "AMD", r1);
        Train t3 = new Train(5004, "Bikaner Express", "BK", "JU", r2);
        Train t4 = new Train(9874, "Udaipur Express", "JP", "UDI", r3);

        trainList.add(t1);
        trainList.add(t2);
        trainList.add(t3);
        trainList.add(t4);
    }

    public void saveNewTrain(Train train) {

        trainList.add(train);
    }

    @Override
    public String[] getRouteOfTrain(int Number) {

        String route[] = null;
        for (Train train : trainList) {

            if (train.trainNumber == Number) {

                route = train.route;

            }

        }
        return route;
    }

    @Override
    public void updateTrain(Train train) {

    }

    @Override
    public Train getTrain(int TrainNumber) {
        return null;
    }

    @Override
    public void setRoute(String[] route, int trainNumber) {

    }


    @Override
    public List<Train> getAllTrain() {


        return trainList;
    }

    //........ Get Trains between Origin station to Destination station.................................
    @Override
    public List getTrainBetweenStation(String from, String to) {


        List trainlistFromstation = new ArrayList();

        for (Train t : trainList) {


            for (int i = 0; i < t.route.length; i++) {

              /*  if(from.equals(t.route[t.route.length-1])){
                    break;
                }

                if(to.equals(t.route[0])){
                    break;
                }*/

                if (from.equals(t.route[i])) {

                    for (int j = i + 1; j < t.route.length; j++) {

                        if (to.equals(t.route[j])) {

                            trainlistFromstation.add(t);
                            break;
                        }
                    }
                }
            }
        }

        return trainlistFromstation;

    }


    //...............Get All Train On Perticular Station.............................
    @Override
    public List getAllTrainOnStation(String station) {


        List trainlistOnStation = new ArrayList();

        for (Train t : trainList) {

            for (int i = 0; i < t.route.length; i++) {

                if (t.route[i].equals(station)) {

                    trainlistOnStation.add(t.trainName);
                }
            }
        }
        return trainlistOnStation;
    }

}
