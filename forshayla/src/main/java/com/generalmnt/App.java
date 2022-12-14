package com.generalmnt;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This application gathers info from a patient and calculates their 
 * daily metabolic needs.
 */
public class App 
{
    private static final double WEIGHT_CONST = 9.99;
    private static final double HEIGHT_CONST = 6.25;
    private static final double AGE_CONST = 4.92;
    private static final int MALE_CONST = 5;
    private static final int FEMALE_CONST = 161;
    private static final int FLUID_INIT = 1500;
    private static final int FLUID_CONST = 20;
    public static void main( String[] args )
    {
        // Create scanner object
        Scanner scan = new Scanner(System.in);
        // Create general info Vars
        char sex = ' ';
        double height = 0.0;
        double weight = 0.0;
        double activity = 0.0;
        double proteinFactor = 0.0;
        int age = 0;
        // Gather info
        System.out.printf("\nPlease enter the following information\n");
        bigLine();
        sex = getSex(scan);
        age = getAge(scan);
        height = getHeight(scan);
        weight = getWeight(scan);
        activity = getActivity(scan);
        proteinFactor = getProteinFactor(scan);
        bigLine();
        // Calculate Resting Metabolic rate, Estimated Energy Needs, Protein Needs, Fluid needs
        double rmr = 0.0;
        double een = 0.0;
        double epn = 0.0;
        double efn = 0.0;
        rmr = calculateRMR(sex, weight, height, age);
        een = calculateEEN(rmr, activity);
        epn = calculateEPN(weight, proteinFactor);
        efn = calculateEFN(weight);
        // Print initial info
        printInitialInfo(sex, age, height, weight, activity);
        // Print calculated info
        printNutritionalNeeds(rmr, een, epn, efn);
        // Close scanner
        scan.close();
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
        double minActivity = 1.1;
        double maxActivity = 2.5;
        boolean valid = true;
        // Print criteria for activity
        System.out.printf("\nActivty rates are as follows");
        printActivity();
        // Collect input
        while (activity < minActivity || !valid){
            // Reset every loop
            valid = true;
            try{
                System.out.printf("\nPlease enter your activity rate: ");
                activity = scan.nextDouble();

                if (activity < minActivity || activity > maxActivity){
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
    // This gets input for the protein factor
    private static double getProteinFactor(Scanner scan){
        double pf = 0.0;
        double minPF = 0.8;
        double maxPF = 2.0;
        boolean valid = true;

        while(pf < minPF || !valid){
            valid = true;
            try {
                System.out.printf("\nPlease enter Protein Factor: ");
                pf = scan.nextDouble();

                if (pf < minPF || pf > maxPF){
                    // Set flag
                    valid = false;
                    // Alert user
                    System.out.printf("\nProtein factor must be between %s and %s, please try again", minPF, maxPF);
                }
            } catch (InputMismatchException e){
                // Set flag
                valid = false;
                // Reinitialize 
                pf = 0.0;
                // Clear buffer
                scan.next();
                // Alert user
                System.out.printf("\nProtein factor must be between %s and %s, please try again", minPF, maxPF);
            }
        }
        return pf;
    }
    // This prints activity range
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
    // This function calculates Estimated Energy Needs
    private static double calculateEEN(double rmr, double activity){
        double een = 0.0;
        een = rmr * activity;
        return een;
    }
    // This function calculates Estimated protein needs
    private static double calculateEPN(double weight, double proteinFactor){
        double epn = 0.0;
        epn = weight * proteinFactor;
        return epn;
    }
    // This function calculates Estimated Fluid Needs
    private static double calculateEFN(double weight){
        double efn = 0.0;
        weight -= FLUID_CONST;
        efn += FLUID_INIT;
        efn += (weight * FLUID_CONST);
        return efn;
    }
    // Print out all initial info
    private static void printInitialInfo(char sex, int age, double height, double weight, double activity){
        String gender = "";
        switch(sex){
            case 'M':
                gender = "Male";
                break;
            case 'F':
                gender = "Female";
                break;
            default:
                break;
        }
        System.out.printf("\nNutritional Assessment Info\tYour Answer\n");
        bigLine();
        System.out.printf("\nGender:\t\t\t\t%s", gender);
        System.out.printf("\nAge:\t\t\t\t%d", age);
        System.out.printf("\nHeight:\t\t\t\t%.2f cm", height);
        System.out.printf("\nWeight:\t\t\t\t%.2f kg", weight);
        System.out.printf("\nActivity Level:\t\t\t%.2f\n", activity);
        bigLine();
    }
    // Prints nutritional needs
    private static void printNutritionalNeeds(double rmr, double een, double epn, double efn){
        System.out.printf("\nNutritional Factors\n");
        bigLine();
        System.out.printf("\nResting Metabolic Rate:\t\t%.2f kcal", rmr);
        System.out.printf("\nEstimated Energy Needs:\t\t%.2f kcal", een);
        System.out.printf("\nEstimated Protein Needs:\t%.2f\tg", epn);
        System.out.printf("\nEstimated Fluid Needs:\t\t%.2f mL\n", efn);
        bigLine();
    }
    // this prints a big ass line
    private static void bigLine(){
        System.out.println("---------------------------------------------");
    }
}
