package com.train.Test;


import com.train.model.Train;

import java.io.*;
import java.util.stream.Stream;

public class LineNumberReaderExample {
    private static String oldString;

    public static void createFile() {

        try {
            FileWriter fileWriter = new FileWriter("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\TestFile", true);

            for (int i = 1; i <= 1000; i++) {
                fileWriter.write(i + "," + 10 + i + "," + 20 + i + "," + 50 + i + "\n");
            }


            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void updateTrain(int lineNumber) {
        try (Stream<String> stream = new BufferedReader(new FileReader("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\TestFile")).lines()) {

            stream.forEach(line -> {
                String[] split = line.split(",");
                if (line.startsWith(Integer.toString(lineNumber))) {
                    String newContent = line.replaceAll(split[1], "rajesh");

                    oldString = oldString + newContent + System.lineSeparator();
                } else {
                    oldString = oldString + line + System.lineSeparator();
                }
            });
            FileWriter writer = new FileWriter("E:\\workspace\\train\\src\\main\\resources\\fileDataSource\\TestFile");
            writer.write(oldString);
            writer.close();
            oldString = "";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        createFile();
       /* System.out.println("updating.....");
         final long start = System.currentTimeMillis();
       updateTrain(101);
        long end = System.currentTimeMillis();
        System.out.println("time taken: "+end);*/

    }
}