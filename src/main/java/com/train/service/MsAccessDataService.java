package com.train.service;


import com.train.model.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MsAccessDataService implements DataService {

    List<Train> trainList = new ArrayList<>();
    Statement statement;
    PreparedStatement preparedStatement;

    private Connection connection;


    public MsAccessDataService(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();        ;
    }

    @Override
    public List<Train> getAllTrain() {

        List<Train> listOfTrain = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Trains");

            while (rs.next()) {

                Train t = new Train();
                t.trainNumber = rs.getInt("TrainNumber");
                t.trainName = rs.getString("TrainName");
                t.startStation = rs.getString("StartStation");
                t.endStation = rs.getString("EndStation");
                listOfTrain.add(t);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listOfTrain;
    }

    @Override
    public List<Train>  getTrainBetweenStation(String from, String to) {

        List<Train> trainsBetweenTwoStation = new ArrayList<Train>();
        List<Integer> numberList = new ArrayList();
        String query = "select trainNumber from route\n" +
                "where stationid in\n" +
                "(\n" +
                "select stationid from station\n" +
                "where stationcode in ('" + from + "','" + to + "')\n" +
                ")\n";

        // String query = FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\queryFindTrainBetweenTwoStations.sql");
        try {
            /*preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,from );
            preparedStatement.setString(2,to);*/

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                numberList.add(rs.getInt("TrainNumber"));
                System.out.println(rs.getInt("TrainNumber"));
            }
            List<Integer> trainNumbers = findTrainsThatContainsBothStation(numberList);

            List<Integer> listoftrainNumber = findTrain(from, to, trainNumbers);


            for (int n : listoftrainNumber) {
                System.out.println("trainnumber" + n);
                trainsBetweenTwoStation.add(getTrain(n));
            }


        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return trainsBetweenTwoStation;
    }


    @Override
    public void saveNewTrain(Train train) {

        try {
            String sql = "INSERT INTO Trains(TrainNumber,TrainName,StartStation,EndStation) " + "VALUES ('" + train.trainNumber + "','" + train.trainName + "','" + train.startStation + "','" + train.endStation + "')";
            statement.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    @Override
    public List<Train> getAllTrainOnStation(String station) {
        return null;
    }

    @Override
    public String[] getRouteOfTrain(int trainNumber) {

        List<String> listOfStation = new ArrayList();
        ResultSet resultSet;
        String route[] = null;
        try {
            String query = "select stationName from Station\n" +
                    "where stationid in\n" +
                    "(\n" +
                    "select stationId from route\n" +
                    "where trainNumber=\n" + trainNumber +
                    ")\n";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // System.out.println(resultSet.getString("Stationname"));
                listOfStation.add(resultSet.getString("Stationname"));

            }
            route = new String[listOfStation.size()];
            for (int i = 0; i < route.length; i++) {
                route[i] = listOfStation.get(i);

            }

        } catch (Exception e) {
            System.err.println("Exception aa gai \n" + e.toString());
        }
        return route;
    }

    @Override
    public void updateTrain(Train train) {
        try {
            statement.execute("UPDATE Trains" + " SET TrainNumber=" + train.trainNumber + ",TrainName='" + train.trainName + "',StartStation='" + train.startStation + "',EndStation='" + train.endStation + "' WHERE TrainNumber=" + train.trainNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Train getTrain(int trainNumber) {
        Train train = null;
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Trains WHERE TrainNumber=" + trainNumber);

            while (rs.next()) {
                train = new Train();
                train.trainNumber = rs.getInt("TrainNumber");
                train.trainName = rs.getString("TrainName");
                train.startStation = rs.getString("StartStation");
                train.endStation = rs.getString("EndStation");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return train;
    }

    @Override
    public void setRoute(String[] route, int trainNumber) {

    }

    public String[] getRouteCode(int trainNumber) {

        List<String> listOfStation = new ArrayList();
        ResultSet resultSet;
        String route[] = null;
        try {
            String query = "select stationcode from Station\n" +
                    "where stationid in\n" +
                    "(\n" +
                    "select stationId from route\n" +
                    "where trainNumber=\n" + trainNumber +
                    ")\n";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // System.out.println(resultSet.getString("Stationname"));
                listOfStation.add(resultSet.getString("Stationcode"));

            }
            route = new String[listOfStation.size()];
            for (int i = 0; i < route.length; i++) {

                route[i] = listOfStation.get(i);

            }

        } catch (Exception e) {
            System.err.println("get Routecode \n" + e.toString());
        }
        return route;

    }

    public List<Integer> findTrain(String from, String to, List<Integer> listofTrainNumber) {

        List trainlistFromstation = new ArrayList();

        for (int n = 0; n < listofTrainNumber.size(); n++) {

            String route[] = getRouteCode(listofTrainNumber.get(n));


            for (int i = 0; i < route.length; i++) {

                if (from.equals(route[route.length - 1])) {
                    break;
                }

                if (to.equals(route[0])) {
                    break;
                }
                if (from.equals(route[i])) {

                    for (int j = i + 1; j < route.length; j++) {

                        if (to.equals(route[j])) {

                            trainlistFromstation.add(listofTrainNumber.get(n));

                            break;
                        }
                    }
                }
            }
        }
        System.out.println("all train from this route");
        for (int i = 0; i < trainlistFromstation.size(); i++) {
            System.out.println(trainlistFromstation.get(i));
        }
        return trainlistFromstation;
    }

    public List<Integer> findTrainsThatContainsBothStation(List<Integer> trainNumberList) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> trainNumbers = new ArrayList<>();
        for (int i = 0; i < trainNumberList.size(); i++) {
            Integer key = trainNumberList.get(i);
            if(map.containsKey(key)) {
                trainNumbers.add(key);
            } else {
                map.put(key,1);
            }
        }
        return trainNumbers;
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://E:\\workspace\\irctcTabels.accdb");

        } catch (Exception e) {
            System.err.println(e.toString());
        }

        String route[] = new MsAccessDataService(connection).getRouteOfTrain(8966);
//        for (String s:route) {
//            System.out.println(s);
//        }
        List<Train> list = new MsAccessDataService(connection).getTrainBetweenStation("HMH", "BK");
        for (Train train : list) {
            System.out.println(train);
        }
    }
}
