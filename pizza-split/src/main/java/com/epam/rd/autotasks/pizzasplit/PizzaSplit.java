package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        Scanner scanner = new Scanner(System.in);
        int people = scanner.nextInt();
        int slices = scanner.nextInt();
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder
        int pizzaCount = 1;
        while ((slices*pizzaCount) % people !=0){
            pizzaCount++;
        }

        System.out.println(pizzaCount);

    }
}
