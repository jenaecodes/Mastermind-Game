package com.mastermind.cmd;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MastermindGame class.
 */
class MastermindGameTest {

    @Test
    void testGenerateSecretCode() {
        // Tests the generation of a secret code with the specified length.
        // Verifies that the generated secret code has the correct length and valid digits.
        int codeLength = 4;
        int[] secretCode = MastermindGame.generateSecretCode(codeLength);
        assertNotNull(secretCode);
        assertEquals(codeLength, secretCode.length);

        for (int digit : secretCode) {
            assertTrue(digit >= MastermindGame.MIN_RANDOM_NUMBER && digit <= MastermindGame.MAX_RANDOM_NUMBER);
        }
    }

    @Test
    void testReadUserGuess() {
        // Tests reading the user's guess from the console.
        // Verifies that the user's guess has the correct length and valid digits.
        int length = 3;
        String input = "123";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int[] userGuess = MastermindGame.readUserGuess(length, scanner);

        assertNotNull(userGuess);
        assertEquals(length, userGuess.length);

        for (int digit : userGuess) {
            assertTrue(digit >= 0 && digit <= MastermindGame.MAX_RANDOM_NUMBER);
        }
    }

    @Test
    void testContainsNumber() {
        // Tests checking if a number is present in an array.
        // Verifies that the method correctly identifies the presence or absence of a number.
        int[] array = {1, 2, 3, 4};
        int existingNumber = 2;
        int nonExistingNumber = 5;

        assertTrue(MastermindGame.containsNumber(array, existingNumber));
        assertFalse(MastermindGame.containsNumber(array, nonExistingNumber));
    }

    @Test
    void testConvertStringToIntArray() {
        // Tests converting a string to an array of integers.
        // Verifies that the converted array has the correct length and valid digits.
        String input = "123";
        int[] intArray = MastermindGame.convertStringToIntArray(input);

        assertNotNull(intArray);
        assertEquals(input.length(), intArray.length);

        for (int digit : intArray) {
            assertTrue(digit >= 0 && digit <= MastermindGame.MAX_RANDOM_NUMBER);
        }
    }

    @Test
    void testIntArrayToString() {
        // Tests converting an int array to a String.
        // Verifies that the converted string is as expected.
        int[] intArray = {1, 2, 3};
        String result = MastermindGame.intArrayToString(intArray);
        assertEquals("123", result);
    }

    // Additional Scenarios

    @Test
    void testGenerateSecretCodeWithMinLength() {
        // Tests generating a secret code with the minimum possible length.
        // Verifies that the generated secret code has the correct length.
        int codeLength = 1;
        int[] secretCode = MastermindGame.generateSecretCode(codeLength);
        assertNotNull(secretCode);
        assertEquals(codeLength, secretCode.length);
    }

    @Test
    void testGenerateSecretCodeWithMaxLength() {
        // Tests generating a secret code with the maximum possible length.
        // Verifies that the generated secret code has the correct length.
        int codeLength = 10;
        int[] secretCode = MastermindGame.generateSecretCode(codeLength);
        assertNotNull(secretCode);
        assertEquals(codeLength, secretCode.length);
    }

    @Test
    void testReadUserGuessWithInvalidInput() {
        // Tests reading the user's guess with invalid non-numeric input.
        // Verifies that the method handles invalid input and throws NoSuchElementException.
        int length = 3;
        String input = "abc";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Expecting that the method throws NoSuchElementException for invalid input.
        assertThrows(java.util.NoSuchElementException.class,
                () -> MastermindGame.readUserGuess(length, scanner),
                "Expected NoSuchElementException for invalid input");
    }

    @Test
    void testReadUserGuessWithIncorrectLength() {
        // Tests reading the user's guess with an input of incorrect length.
        // Verifies that the method handles incorrect length input and throws NoSuchElementException.
        int length = 3;
        String input = "1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Expecting that the method throws NoSuchElementException for incorrect length input.
        assertThrows(java.util.NoSuchElementException.class,
                () -> MastermindGame.readUserGuess(length, scanner),
                "Expected NoSuchElementException for incorrect length input");
    }
}