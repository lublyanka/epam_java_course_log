package com.epam.rd.autotasks.meetautocode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        DateFormat dateFormat = new SimpleDateFormat("H:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = new Date(seconds*1000);
        String formattedTime = dateFormat.format(date);
        System.out.format(formattedTime);

    }
}
