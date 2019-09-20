package com.train.service;

import com.train.model.Train;
import com.train.repository.TrainJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class JpaTrainDataServiceImpl implements DataService {

    @Autowired
    private TrainJpaRepository trainJpaRepository;

    @Override
    public Train getTrain(int TrainNumber) {
        return null;
    }

    @Override
    public void setRoute(String[] route, int trainNumber) {

    }

    @Override
    public void updateTrain(Train train) {

    }

    @Override
    public List<Train> getAllTrain() {

        return (List<Train>) trainJpaRepository.findAll();
    }

    @Override
    public List getTrainBetweenStation(String from, String to) {

        List trainlistFromstation = new ArrayList();

        for (Train t : trainJpaRepository.findAll()) {

            for (int i = 0; i < t.route.length; i++) {

                if (from.equals(t.route[t.route.length - 1])) {
                    break;
                }

                if (to.equals(t.route[0])) {
                    break;
                }

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

    @Override
    public void saveNewTrain(Train train) {
        trainJpaRepository.save(train);
    }

    @Override
    public List<Train> getAllTrainOnStation(String station) {
        return null;
    }

    @Override
    public String[] getRouteOfTrain(int number) {
        String route[] = null;

        for (Train train : trainJpaRepository.findAll()) {

            if (train.trainNumber == number) {

                route = train.route;

            }

        }
        return route;
    }


}
