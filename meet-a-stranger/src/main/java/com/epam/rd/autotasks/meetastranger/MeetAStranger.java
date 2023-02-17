package com.epam.rd.autotasks.meetastranger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeetAStranger {
    public static void main(String[] args) {
        //Write a program, which read a String from System.in and print "Hello, <input string>"

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String inputString = reader.readLine();
            System.out.println("Hello, " + inputString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
