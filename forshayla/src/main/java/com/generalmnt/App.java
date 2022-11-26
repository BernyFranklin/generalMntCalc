package com.generalmnt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final double WEIGHT_CONST = 9.99;
    private static final double HEIGHT_CONST = 6.25;
    private static final double AGE_CONST = 4.92;
    private static final int MALE_CONST = 5;
    private static final int FEMALE_CONST = 161;
    public static void main( String[] args )
    {
        // Create scanner object
        Scanner scan = new Scanner(System.in);
        // Create general info Vars
        char sex = ' ';
        double height = 0.0;
        double weight = 0.0;
        double activity = 0.0;
        int age = 0;
        // Gather info
        System.out.printf("\nPlease enter the following information\n");
        bigLine();
        sex = getSex(scan);
        age = getAge(scan);
        height = getHeight(scan);
        weight = getWeight(scan);
        activity = getActivity(scan);
        bigLine();
        
    }
    // This function sets sex to 'M' or 'F'
    private static char getSex(Scanner scan){
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
    // This function sets age to an int > 0
    private static int getAge(Scanner scan){
        // Initialize
        int age = 0;
        // Flag for valid input
        Boolean valid = true;
        // Loop until valid
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
                // Set flag
                valid = false;
                // Reinitialize age
                age = 0;
                // Clear buffer
                scan.next();
                // Tell user
                System.out.printf("\nInput must be whole number, please try again");
            }
        }
        return age;
    }
    // This gathers heigh info in cm
    private static double getHeight(Scanner scan){
        // Initialize
        double height = 0.0;
        // Flag for valid input
        Boolean valid = true;
        // Loop until valid
        while (height <= 0.0 || !valid) {
            // Reset to true every loop
            valid = true;
            // Check for input mismatch
            try{
                System.out.printf("\nHeight in centimeters: ");
                height = scan.nextDouble();
                // Check if height is greater than zero
                if (height <= 0.0) {
                    valid = false;
                    System.out.printf("\nHeight must be greater than zero, please try again");
                }
            } catch(InputMismatchException e) {
                // Set flag
                valid = false;
                // Reinitialize
                height = 0.0;
                // Clear buffer
                scan.next();
                // Tell user
                System.out.printf("\nHeight must be a number in centimeters, please try again");
            }
        }
        return height;
    }
    // This function returns weight in kg
    private static double getWeight(Scanner scan){
        // Initialize
        double weight = 0.0;
        // Flag for valid input
        Boolean valid = true;
        // Loop until valid
        while (weight <= 0.0 || !valid) {
            // Reset to true every loop
            valid = true;
            // Check for input mismatch
            try{
                System.out.printf("\nWeight in kilograms: ");
                weight = scan.nextDouble();
                // Check if height is greater than zero
                if (weight <= 0.0) {
                    valid = false;
                    System.out.printf("\nWeight must be greater than zero, please try again");
                }
            } catch(InputMismatchException e) {
                // Set flag
                valid = false;
                // Reinitialize
                weight = 0.0;
                // Clear buffer
                scan.next();
                // Tell user
                System.out.printf("\nWeight must be a number in kilograms, please try again");
            }
        }
        return weight;
    }
    // This explains and gathers activity info
    private static double getActivity(Scanner scan){
        double activity = 0.0;
        boolean valid = true;
        // Print criteria for activity
        System.out.printf("\nActivty rates are as follows");
        printActivity();
        // Collect input
        while (activity < 1.1 || !valid){
            // Reset every loop
            valid = true;
            try{
                System.out.printf("\nPlease enter your activity rate: ");
                activity = scan.nextDouble();

                if (activity < 1.1 || activity > 2.5){
                    // Set flag
                    valid = false;
                    // Alert user
                    System.out.printf("\nActivity must be a number between 1.1 and 2.5, please try again");
                }
            } catch(InputMismatchException e) {
                // Set flag
                valid = false;
                // Reinitialize activity
                activity = 0.0;
                // Clear buffer
                scan.next();
                // Alert user
                System.out.printf("\nActivity must be a number between 1.1 and 2.5, please try again");

            }
        }
        return activity;   
    }
    
    private static void printActivity(){
        System.out.printf("\nSedentary:\t1.1 - 1.4");
        System.out.printf("\nLow Activity:\t1.4 - 1.6");
        System.out.printf("\nActive:\t\t1.6 - 1.9");
        System.out.printf("\nVery Active:\t1.9 - 2.5");
    }
    // This function calculates the resting metabolic rate
    private static double calculateRMR(char sex, double weight, double height, int age){
        double rmr = 0.0;
        switch (sex){
            case 'M':
                rmr = (WEIGHT_CONST * weight) + (HEIGHT_CONST * height) - (AGE_CONST * age) + MALE_CONST;
                break;
            case 'F':
                rmr = (WEIGHT_CONST * weight) + (HEIGHT_CONST * height) - (AGE_CONST * age) - FEMALE_CONST;
                break;
            default:
                break;
        }
        return rmr;
    }
    // this prints a big ass line
    private static void bigLine(){
        System.out.printf("----------------------------------------\n");
    }
}
