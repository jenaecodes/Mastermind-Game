package com.mastermind.cmd;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MastermindGame {

    private static final int MAX_ATTEMPTS = 10;
    static final int MIN_RANDOM_NUMBER = 0;
    static final int MAX_RANDOM_NUMBER = 7;
    public static final String RANDOM_NUMBER_API_URL = "https://www.random.org/integers/?num=%s&min=0&max=7&col=1&base=10&format=plain&rnd=new";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mastermind!");
        System.out.println("Select difficulty: (E)asy, (M)edium, (H)ard");
        char difficultyChoice = scanner.next().charAt(0);

        // Get code length based on difficulty choice
        int codeLength = getCodeLengthFromDifficulty(difficultyChoice);
        if (codeLength == -1) {
            System.out.println("Invalid difficulty choice. Exiting...");
            return;
        }

        int[] secretCode = generateSecretCode(codeLength);

        System.out.println("Try to guess the " + codeLength + "-digit code. You have " + MAX_ATTEMPTS + " attempts.");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Attempt " + attempt + ": Enter your guess (without spaces): ");
            int[] userGuess = readUserGuess(codeLength, scanner);

            if (Arrays.equals(userGuess, secretCode)) {
                System.out.println("Congratulations! You guessed the correct code.");
                return;
            }
            displayFeedback(userGuess, secretCode);
        }

        System.out.println("Sorry, you've run out of attempts. The correct code was: " + intArrayToString(secretCode));
    }

    private static int getCodeLengthFromDifficulty(char difficultyChoice) {
        return switch (Character.toUpperCase(difficultyChoice)) {
            case 'E' -> 3;
            case 'M' -> 4;
            case 'H' -> 5;
            default -> -1; // Indicates an invalid difficulty choice
        };
    }

    /**
     * Generates a secret code using the Random.org API.
     *
     * @param length The length of the secret code.
     * @return An array representing the secret code.
     */
    static int[] generateSecretCode(int length) {
        try {
            // Set up the connection to Random.org API
            URL url = new URL(String.format(RANDOM_NUMBER_API_URL, length, MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                // Read random numbers and convert to an array
                List<Integer> code = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    code.add(scanner.nextInt());
                }
                return convertStringToIntArray(String.join("", code.stream().map(Object::toString).toArray(String[]::new)));
            }
        } catch (IOException e) {
            // Handle API connection error
            System.err.println("Error generating secret code. Exiting...");
            System.exit(1);
            return new int[0];
        }
    }

    /**
     * Reads the user's guess from the console.
     *
     * @param length  The length of the user's guess.
     * @param scanner The scanner to read input.
     * @return An array representing the user's guess.
     */
    static int[] readUserGuess(int length, Scanner scanner) {
        int[] guess = new int[length];
        String input = scanner.next();

        // Validate if the input is a positive number
        if (!isValidNumericInput(input)) {
            System.out.println("Invalid input. Please enter a positive number.");
            return readUserGuess(length, scanner);
        }

        // Validate the length of the user's guess
        if (input.length() != length) {
            System.out.println("Invalid input length. Please enter a " + length + "-digit guess.");
            return readUserGuess(length, scanner);
        }

        // Convert user input to an integer array
        for (int i = 0; i < length; i++) {
            guess[i] = Character.getNumericValue(input.charAt(i));
        }

        return guess;
    }

    // Validates if the input is a positive number
    static boolean isValidNumericInput(String input) {
        try {
            return Integer.parseInt(input) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Displays feedback to the user based on their guess and the secret code.
     *
     * @param guess      The user's guess.
     * @param secretCode The secret code.
     */
    static void displayFeedback(int[] guess, int[] secretCode) {
        int correctPosition = 0;
        int correctNumber = 0;
        List<Integer> correctNumberList = new ArrayList<>();

        // Check correct positions and correct numbers
        for (int i = 0; i < secretCode.length; i++) {
            // Checking the correct number of positions
            if (guess[i] == secretCode[i]) {
                correctPosition++;
            }
            // Checking the number of correct numbers
            if (containsNumber(secretCode, guess[i]) && !correctNumberList.contains(guess[i])) {
                correctNumber++;
                correctNumberList.add(guess[i]);
            }
        }

        // Display feedback to the user
        System.out.println(correctNumber + " correct number(s), " + correctPosition + " correct position(s)");
    }

    /**
     * Checks if a number is present in an array.
     *
     * @param array  The array to check.
     * @param number The number to look for.
     * @return True if the number is present, false otherwise.
     */
    // Checks if a number is present in an array.
    static boolean containsNumber(int[] array, int number) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a string to an array of integers.
     *
     * @param input The input string.
     * @return An array of integers.
     */
    static int[] convertStringToIntArray(String input) {
        int[] intArray = new int[input.length()];

        // Convert each character to an integer
        for (int i = 0; i < input.length(); i++) {
            char charDigit = input.charAt(i);
            int digit = Character.getNumericValue(charDigit);
            intArray[i] = digit;
        }
        return intArray;
    }

    /**
     * Converts an array of integers to a string by concatenating its elements.
     *
     * @param intArray The array of integers to be converted.
     * @return A string representation of the integer array.
     */
    static String intArrayToString(int[] intArray) {
        StringBuilder stringBuilder = new StringBuilder();

        // Concatenate each integer to the StringBuilder
        for (int num : intArray) {
            stringBuilder.append(num);
        }

        return stringBuilder.toString();
    }
}