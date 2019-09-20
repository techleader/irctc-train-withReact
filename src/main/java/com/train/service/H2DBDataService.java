package com.train.service;


import com.train.model.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class H2DBDataService implements DataService {

    List<Train> trainList = new ArrayList<>();
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private Connection connection;


    public H2DBDataService(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
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
//...................Get Train Between Two Stations.............................................

    @Override
    public List<Train>  getTrainBetweenStation(String from, String to) {
        List<Train> trainsBetweenTwoStation = new ArrayList<Train>();
        List<Integer> numberList = new ArrayList();
        String query = FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\queryFindTrainBetweenTwoStations.sql");
        System.out.println(query);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,from );
            preparedStatement.setString(2,to);

            ResultSet rs = preparedStatement.executeQuery();
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

//...................Insert New Train To DataBase.............................................
    @Override
    public void saveNewTrain(Train train) {


        String query = FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\insertNewTrainQuery.sql");
        try {
           preparedStatement =connection.prepareStatement(query);
           preparedStatement.setInt(1,train.trainNumber);
            preparedStatement.setString(2,train.trainName);
            preparedStatement.setString(3,train.startStation);
            preparedStatement.setString(4,train.endStation);

            preparedStatement.execute();
            System.out.println("Inserted records into the table...");
        } catch (Exception err) {
           err.printStackTrace();
        }
    }

    @Override
    public List<Train> getAllTrainOnStation(String station) {
        List<Train> trainList = new ArrayList<>();
        String query= FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\getAllTrainOnStation.sql");
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,station);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                trainList.add(getTrain(resultSet.getInt("trainNumber")));
                System.out.println(getTrain(resultSet.getInt("trainNumber")).toString());
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        return trainList;
    }

    @Override
    public String[] getRouteOfTrain(int trainNumber) {
        List<String> listOfStation = new ArrayList();

        String route[] = null;
        String query = FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\queryToGetRouteOfTrain.sql");
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,trainNumber );
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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

        String query = FileService.readFileToString("E:/workspace/train/src/main/resources\\sqlQueries\\UpdateQuery.sql");
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,train.trainNumber);
            preparedStatement.setString(2,train.trainName);
            preparedStatement.setString(3,train.startStation);
            preparedStatement.setString(4,train.endStation);
            preparedStatement.execute();

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
            String query ="select stationCode from route as r\n" +
                    "inner join station s on s.stationid =r.stationid\n" +
                    "where trainnumber="+trainNumber;

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
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
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/file:/E:/workspace/H2DB/train-db","admin","");
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        Train train = new Train();
        train.trainNumber=14278;
        train.trainName="rath";
        train.startStation="bdts";
        train.endStation="jp";
        new H2DBDataService(connection).saveNewTrain(train);
     //  List<Train> list = new H2DBDataService(connection).getAllTrainOnStation("BK");

//        String[] list = new H2DBDataService(connection).getRouteOfTrain(8521);
//        for (String s : list) {
//            System.out.println(s);
//        }

    }
}
