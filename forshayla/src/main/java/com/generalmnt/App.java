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
        sex = getSex(scan);

        
    }

    private static void gatherInfo() {
            // Flag for valid input
            Boolean valid = true;
            while (age <= 0 || !valid){
                // Reset to true every loop
                valid = true;
                // Check for input mismatch
                try {
                    System.out.printf("\nAge [Whole numbers only]: ");
                    age = scan.nextInt();
                    // Check if age is greater than zero
                    if (age <=0){
                        valid = false;
                        System.out.printf("\nAge must be greater than 0, please try again");
                    }
                } catch(InputMismatchException e) {
                    valid = false;
                    age = 0;
                    scan.next();
                    System.out.printf("\nInput must be whole number, please try again");
                }
            }
        }

    private static char getSex(Scanner scan) {
        char sex = ' ';
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
        }
            return sex;
    }

    private static void bigLine(){
        System.out.printf("----------------------------------------\n");
    }
}
