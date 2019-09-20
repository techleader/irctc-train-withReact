package com.train.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class FileService {

    public static String readFileToString(String fileName){
        StringBuffer buffer = new StringBuffer();
        try (Stream<String> stream = new BufferedReader(new FileReader(fileName)).lines()) {
            stream.forEach(line -> {
                buffer.append(line + " ");
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
