package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Scanner;

public class Average {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        int summ=0;
        int count=0;
        int avg=0;
        int stopNumber = 0;
        // Use Scanner methods to read input
        while (scanner.hasNext()) {
            String currentString = scanner.next();
            String[] numbersetString = currentString.split(" ");
            for (String s : numbersetString) {
                int currnumber = Integer.parseInt(s);
                if (currnumber != stopNumber) {
                    summ = Integer.sum(summ, currnumber);
                    count++;
                } else
                    break;
            }
        }
        scanner.close();

        avg = summ/count;
        System.out.println(avg);

        /*

      numbers.sort(Comparator.naturalOrder());

        return numbers.get(numbers.size()-1);*/
    }

}