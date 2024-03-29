# Jenny's Mastermind Game
Welcome to my Mastermind game, a Java console application where you can challenge yourself to guess a secret combination of four numbers generated by the computer. You have 10 attempts to crack the code! The computer randomly selects a pattern of four different numbers from the set 0-7, and after each guess, the computer provides feedback without revealing specific numbers.
I have implemented configurable difficulty levels that adjust the amount of numbers used.

________

## Getting Started
1. Clone the repository to your local machine.
2. Download IntelliJ Community Edition and the latest Java Development Kit.
3. Open the project in IntelliJ IDEA.
4. Locate `MastermindGame.java`
5. Run the play button on `line 11` to start the game.

## Play via .jar File
1. Open the terminal in the project's location.
2. Execute `mvn clean install`
3. Change directory to target: cd target.
4. Execute `java -jar mastermind-game-cmd.jar`
5. Start playing the game.

## How to Play

1. Upon running the application in the command line, select a difficulty level by typing "E", "M", or "H":

   - Easy (E): 3-digit code
   - Medium (M): 4-digit code
   - Hard (H): 5-digit code

2. The computer generates a secret code, and you have 10 attempts to guess it.
3. Enter your guess at each prompt (without spaces), and the computer will provide feedback after each attempt.
4. The feedback includes information about correct numbers and their positions.
5. Continue guessing until you correctly guess the code or run out of attempts.

## Screenshot

![Jennys_Mastermind](https://github.com/jenaecodes/Mastermind-Game/assets/89617621/47609aa0-39bc-46a6-ab33-f13350a10e4a)

________
## Computer's Feedback
The computer provides feedback in the following formats:

- Correct Number: The player guessed a correct number.
- Correct Number and Position: The player guessed a correct number and its correct location.
- Incorrect (zero's): The player's guess was incorrect / none of the numbers are correct.

## Random Number Generator API
The secret combination is randomly selected using the [Random Number Generator API](https://www.random.org/integers)
.

The API parameters for generating the secret code are:

- num: 3, 4, or 5 (number of integers requested)
- min: 0 (smallest value returned)
- max: 7 (largest value returned)
- col: 1 (number of columns used to display the returned values)
- base: 10 (use base 10 system)
- format: plain (returns response in plain text)
- rnd: new (generate a new set of random numbers)

________

## Unit Tests
The project includes unit tests in the MastermindGameTest class. These tests cover various functionalities, including generating a secret code, reading the user's guess, checking if a number is present in an array, converting a string to an array of integers, and converting an array of integers to a string.

Additional unit tests cover edge cases and scenarios, such as generating a secret code with the minimum and maximum possible lengths and reading the user's guess with invalid input or an incorrect length.

## Running Unit Tests
1. Open the terminal in the project's location.
2. Execute `mvn test`
3. View the test results.

________

## My Thought Process
In this project, I crafted a simple command line application that allows players to engage in a challenging game of Mastermind against the computer. The initial intention was to expand the project into a UI web application; however, the decision was made to prioritize backend programming, resulting in a focused and robust command line experience. For the assignment's requirements, we were to leverage the Random.org API for code generation, which created an element of surprise and randomness to the secret code. The feedback system is designed to guide the player towards the correct solution. I desired to expand upon the requirement of a 4-number selection and give the player the option to choose a difficulty level. I also kept in mind the potential for a user to input a negative number or another character, so I incorporated error handling in those cases. 

## Code Structure
-**Main Class**: `MastermindGame.java` contains the main logic for the game, including user input, code generation, and feedback display.

-**API Integration**: The `generateSecretCode` method uses the Random.org API to fetch random numbers for the secret code.

-**User Input**: The `readUserGuess` method handles user input, ensuring it meets the game's requirements.

-**Feedback Display**: The `displayFeedback` method provides feedback based on the user's guess and the secret code.

-**Utility Methods**: Several utility methods are used for tasks such as validating numeric input, converting between data types, and checking the presence of a number in an array.

## Extensions Implemented
My implementation includes a creative extension in the form of dynamic difficulty levels (Easy, Medium, Hard). The user can choose the level of challenge they prefer, with the code length adjusting accordingly. The use of the Random.org API adds an element of unpredictability to the game, making each play-through unique.

### Error Handling:

**Input of Negative Numbers**
   - To address potential issues with negative numbers, a specific validation mechanism was implemented. Users attempting to input negative numbers are met with a clear error message, prompting them to provide a valid, non-negative input. This ensures that the game maintains its integrity and prevents unintended errors related to negative values.

**Input of Non-Integers**
   - Handling non-integer inputs was another crucial aspect of error prevention. The system now actively checks for non-numeric entries and prompts users to input valid integers. This not only prevents runtime errors but also guides users towards correct input practices, contributing to a smoother and more user-friendly gameplay experience.
