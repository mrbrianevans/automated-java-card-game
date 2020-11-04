![Build status](https://img.shields.io/badge/build-passing-brightgreen)
![Tests stats](https://img.shields.io/badge/tests-tests%20failed%3A%200%2C%20passed%3A%2014-success)
# Card game
Command line card game played by computer

## Game functionality
The user can specify the number of players, n.
Each player picks up and discards a card on their turn. 
Players are numbered from 1 to n. 
A player will keep cards that equal their player number. 
If a player gets 4 of the same card, they win the game. 
Players output their moves to text file in the folder `gameOutput`

## Run the game
To run the game, run the main method of the `CardGame` class. You will be prompted to enter the number of players. If you want to use the test pack of cards, enter 22 for number of players. When asked for the filename of the pack of cards, enter `testPack.txt`. The game should ensue, and a winner established. Players will output their turns to seperate files under the `gameOutput` directory, along with the final state of each card deck. 