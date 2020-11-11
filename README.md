![Build Status](https://travis-ci.com/mrbrianevans/automated-java-card-game.svg?branch=master)

![Tests stats](https://img.shields.io/badge/tests-tests%20failed%3A%200%2C%20passed%3A%2016-success)

![Tests stats](https://img.shields.io/badge/code%20coverage-88%25%20of%20lines-brightgreen)
# Card game
Command line card game played by computer

Developed by 041595 & 050744


## Game functionality
The user can specify the number of players, n.
Each player picks up and discards a card on their turn. 
Players are numbered from 1 to n. 
A player will keep cards that equal their player number. 
If a player gets 4 of the same card, they win the game. 
Players output their moves to text file in the folder `gameOutput`

## Run the game
To run the game, run the main method of the `CardGame` class. You will be prompted to enter the number of players. If you want to use the test pack of cards, enter 22 for number of players. When asked for the filename of the pack of cards, enter `testPack.txt`. The game should ensue, and a winner established. Players will output their turns to seperate files under the `gameOutput` directory, along with the final state of each card deck.

### Windows command line instructions
1. In command prompt, navigate to the root folder of the project (automated-java-card-game)
2. Run the command `javac -cp src;lib/junit-4.12 src/CardGame.java`
3. Run the command `java -cp src;lib/junit-4.12 src/CardGame.java`
4. Type the number of players and ENTER (if you want to use the test pack of cards, type 22)
5. Type the filename of the pack of cards (testPack.txt will work)

## Run the test suite
To run the full test suite, compile the `src` folder and then run the `CardGameTestSuite` class. This will automatically run the individual test files for each class.

### Windows command line instructions
1. The tests must be run from the `src` directory. To switch to it from the root folder, use:
```cmd
cd src
```
2. Compile the source code to bytecode
```cmd
javac -cp .;../lib/junit-4.12.jar CardGameTestSuite.java
```
3. Run the tests with JUnit
```cmd
java -cp .;../lib/junit-4.12.jar;../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore CardGameTestSuite
```
---
The expected output is:
```
JUnit version 4.12
....Player 1 has won
............Player 42 has won

Time: 0.071

OK (16 tests)
```
