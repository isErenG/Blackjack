# Blackjack

Practice coding in Java for KdG

### Game

The program is a simple game of blackjack. The rules are simple, you hit or stand and the dealer deals you according to your choice.

There is also an AI who will play automatically the best moves and tell you the statistic after running for x amount of games.

### To-Do
```
Add database connection to see wins and losses (PostgreSQL)
Rework some of the code to make it more readable and more object oriented
Add more comments

Add GUI (later)
```

### How the game works
1. The player is asked how much they want to deposit.
2. The player is asked how much they want to wager.
3. The game starts and reveals the player's and dealer's cards.
4. The player has the option to hit or stand.
5. After the player is ready to stand, the dealer plays and draws till 17.
6. Winner is calculated depending on the higher value hand or if a bust occured.
7. Payout is offered depending on the win or loss and the balance is updated.


### Screenshot
<img src="game.png" height="800" />
