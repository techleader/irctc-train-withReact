package com.train.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainsTestCode {
    String trainName;


    public void setRoute(String route[], int trainNumber) {

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
    public String toString() {
        return "TrainsTestCode{" +
                "trainNumber='" + trainName + '\'' +
                // ", Route=" + Arrays.toString(route) +
                '}';
    }

    public static void main(String[] args) {
        // new TrainsTestCode().setRoute();

    }
}
