package com.train.service;

import com.train.model.Train;

import java.io.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileDataServiceImp implements DataService {

    List<Train> trainList = new ArrayList();
    private Train t;
    String oldString = "";

    @Override
    public Train getTrain(int trainNumber) {
        try (Stream<String> stream = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train")).lines()) {
            stream.forEach(line -> {
                if (line.startsWith(Integer.toString(trainNumber))) {
                    String[] split = line.split(",");
                    t = new Train();
                    t.trainNumber = Integer.parseInt(split[0]);
                    t.trainName = split[1];
                    t.startStation = split[2];
                    t.endStation = split[3];
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void setRoute(String[] route, int trainNumber) {
        try {
            FileWriter fileWriter = new FileWriter("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\route", true);
            FileReader fileReader = new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\route");

            if (fileReader.read() != -1) {

                fileWriter.write("\n");
            }
            fileWriter.write(Integer.toString(trainNumber));
            for (int i = 0; i < route.length; i++) {

                fileWriter.write("," + route[i]);

            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateTrain(Train updatetrain) {
        Train t = getTrain(updatetrain.trainNumber);
        try (Stream<String> stream = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train")).lines()) {

            stream.forEach(line -> {
                if (line.startsWith(Integer.toString(t.trainNumber))) {
                    String newContent = line.replaceAll(t.trainName + "," + t.startStation + "," + t.endStation,
                            updatetrain.trainName + "," + updatetrain.startStation + "," + updatetrain.endStation);
                    System.out.println(newContent);
                    oldString = oldString + newContent + System.lineSeparator();
                } else {
                    oldString = oldString + line + System.lineSeparator();
                }
            });
            FileWriter writer = new FileWriter("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train");
            writer.write(oldString);
            writer.close();
            oldString = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Train> getAllTrain() {

        List<Train> list = new ArrayList<>();

        try (Stream<String> stream = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train")).lines()) {
            stream.forEach(line -> {
                String[] split = line.split(",");
                Train train = new Train();
                train.trainNumber = Integer.parseInt(split[0]);
                train.trainName = split[1];
                train.startStation = split[2];
                train.endStation = split[3];
                list.add(train);

            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Train> getTrainBetweenStation(String from, String to) {
        List<Train> list = getAllTrain();
        List<Train> deaprtureFromOriginToDestination = new ArrayList<>();

        try (Stream<String> stream = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\route")).lines()) {
            stream.forEach(line -> {
                String[] split = line.split(",");


                for (int i = 1; i < split.length; i++) {

                    if (from.equals(split[i])) {

                        for (int j = i + 1; j < split.length; j++) {

                            if (to.equals(split[j])) {

                                try (Stream<String> stream1 = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train")).lines()) {
                                    stream1.forEach(TrainLine -> {
                                        String[] splitedTrains = TrainLine.split(",");
                                        if (split[0].equals(splitedTrains[0])) {
                                            Train train = new Train();
                                            train.trainNumber = Integer.parseInt(splitedTrains[0]);
                                            train.trainName = splitedTrains[1];
                                            train.startStation = splitedTrains[2];
                                            train.endStation = splitedTrains[3];
                                            deaprtureFromOriginToDestination.add(train);
                                        }
                                    });
                                    break;
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }

                }


            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return deaprtureFromOriginToDestination;
    }

    @Override
    public void saveNewTrain(Train train) {
        try {
            FileWriter fileWriter = new FileWriter("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\train", true);

            fileWriter.write(train.trainNumber + "," + train.trainName + "," + train.startStation + "," + train.endStation + "\n");

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        trainList.add(train);

    }

    @Override
    public List<Train> getAllTrainOnStation(String station) {
        return null;
    }


    @Override
    public String[] getRouteOfTrain(int trainNumber) {

        String route[] = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\route"));
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] split = line.split(",");
                if (split[0].equals(Integer.toString(trainNumber))) {

                    route = split;
                }
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public static void main(String[] args) {
        List<Train> list = new FileDataServiceImp().getTrainBetweenStation("HMH", "JD");

        System.out.println(list);
    }
}
