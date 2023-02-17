package com.epam.rd.autotasks.snail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Snail
{
    public static void main(String[] args)
    {
        //Consider a snail travels up a tree a feet each day.
        int a;
        // Then snail slides down b feet each night.
        int b;
        //Height of the tree is h feet.
        int h;
        //days needed
        int days=0;

        //Write a program that reads a,b and h (line by lyne in this order) and prints
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            a = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());
            h = Integer.parseInt(reader.readLine());

            if (b >= a && a < h) {
                System.out.println("Impossible");
            }
            else {
                int i=0;
                while (i < h) {
                    if (i >0)
                        i-=b;
                    i+=a;
                    days ++;
                }
                System.out.println(days);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
