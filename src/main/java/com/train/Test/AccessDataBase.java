package com.train.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class
AccessDatabase {
    public static void main(String[] args) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\pc\\Documents\\mydata.accdb");
            Statement stment = conn.createStatement();
            String sql = "CREATE TABLE  test" +
                    "(id INTEGER not NULL, " +
                    " TrainNumber INTEGER, " +
                    " TrainName VARCHAR(255), " +
                    " StartStation VARCHAR(255), " +
                    " EndStation VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

          /*  String sql = "INSERT INTO test " +
                    "VALUES (3, 8965, 'jammu tavi', 'HMH', 'JD')";*/

            stment.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}