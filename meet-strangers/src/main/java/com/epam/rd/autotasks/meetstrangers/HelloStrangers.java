package com.epam.rd.autotasks.meetstrangers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfStrangers = Integer.parseInt(reader.readLine());
            ArrayList<String> strangers = new ArrayList<>();
            if (numberOfStrangers < 0)
                System.out.println("Seriously? Why so negative?");
            if (numberOfStrangers == 0)
                System.out.println("Oh, it looks like there is no one here");
            else {
                for (int i = 0; i < numberOfStrangers; i++) {
                    strangers.add(reader.readLine());
                }
                strangers.forEach(x -> System.out.println("Hello, " + x ));
            }
            //Write a program, asks for a number - amount of strangers to meet.
            //Then reads stranger names line by line and prints line by line "Hello, ...".
        }
    }
}