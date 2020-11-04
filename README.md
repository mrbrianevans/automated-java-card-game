![Build status](https://img.shields.io/badge/build-passing-brightgreen)
![Tests stats](https://img.shields.io/badge/tests-tests%20failed%3A%200%2C%20passed%3A%2014-success)
# Card game
Command line card game played by computer

## Contributing
- Clone the repository to your local machine `git remote add origin https://github.com/mrbrianevans/automated-java-card-game.git`
- Make edits and commit them locally `git commit -m "description of changes"`
- Update your local repository with `git pull origin master`
- Push your local repository to the origin hosted on GitHub `git push origin master`


## Bug reporting
Any bugs you find in the code, create an issue in the issues tab of the GitHub repository page.

## Game functionality
The user can specify the number of players, n.
Each player picks up and discards a card on their turn. 
Players are numbered from 1 to n. 
A player will keep cards that equal their player number. 
If a player gets 4 of the same card, they win the game. 
Players output their moves to text file in the folder `gameOutput`
