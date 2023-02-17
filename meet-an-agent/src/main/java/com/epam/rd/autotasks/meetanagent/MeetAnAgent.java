package com.epam.rd.autotasks.meetanagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeetAnAgent {
    final static int PASSWORD = 133976; //You can change pass, but don't change the type

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            int number = Integer.parseInt(reader.readLine());
            System.out.println(number == PASSWORD ? "Hello, Agent" : "Access denied");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
