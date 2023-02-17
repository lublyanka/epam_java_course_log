package com.epam.rd.autotasks.godutch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoDutch {
    final static int TIPS = 10;

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            int billSum = Integer.parseInt(reader.readLine());
            int numberOfFriend = Integer.parseInt(reader.readLine());

            if(billSum >=0 && numberOfFriend > 0)
            {
                double splitNumber = (billSum+billSum*0.1) / numberOfFriend;
                System.out.println((int)splitNumber);
            }
            else if(billSum < 0){
                System.out.println("Bill total amount cannot be negative");
            }
            else if (numberOfFriend <=0 )
                System.out.println("Number of friends cannot be negative or zero");
        }
        catch (IOException e) {

        }
    }
}
