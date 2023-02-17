package com.epam.rd.autotasks.sequence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FindMaxInSeq {


    public static int max() {
        int stopNumber=0;
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (reader.ready()){
                String currentString = reader.readLine();
                String[] numbersetString = currentString.split(" ");
                for (String s : numbersetString) {
                    int currnumber = Integer.parseInt(s);
                    if (currnumber != stopNumber)
                        numbers.add(currnumber);
                    else
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        numbers.sort(Comparator.naturalOrder());

        return numbers.get(numbers.size()-1);
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");



        System.out.println(max());
    }
}
