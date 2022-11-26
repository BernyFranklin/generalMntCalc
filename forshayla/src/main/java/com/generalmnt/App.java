package com.generalmnt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final double weightConstant = 9.99;
    private static final double heightConstant = 6.25;
    private static final int ageConstantMale = 5;
    private static final int ageConstantFemale = 161;
    public static void main( String[] args )
    {
        menu();
    }

    private static void menu() {
        // Create scanner object
        Scanner scan = new Scanner(System.in);
        // Create Vars
        char sex = ' ';
        double height = 0.0;
        double weight = 0.0;
        double activity = 0.0;
        int age = 0;

        // Gather info
        System.out.printf("\nPlease enter the following information\n");
        bigLine();
        while (sex != 'M' && sex != 'F') {
            // Temp var for string
            String temp = "";
            // Prompt
            System.out.printf("Sex [M] or [F]: "); 
            // Scan for input, convert to upper, take first char
            temp = scan.nextLine();
            temp = temp.toUpperCase();
            sex = temp.charAt(0);
            // If not M or F continue loop
            if (sex != 'M' && sex != 'F') {
                System.out.printf("\nSex must be [M] or [F], please try again\n");
            }
            Boolean valid = false;
            while (!valid){
                try {
                    System.out.printf("\nAge [Whole numbers only]: ");
                    age = scan.nextInt();
                } catch(InputMismatchException e) {
                    valid = false;
                    System.out.printf("\nInput must be whole number, please try again");
                }
            }
        }
        

    }

    private static void bigLine(){
        System.out.printf("----------------------------------------\n");
    }
}
