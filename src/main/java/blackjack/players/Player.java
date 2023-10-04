package blackjack.players;

import blackjack.game_mechanics.Deck;

import java.util.List;
import java.util.Scanner;

public class Player {

    Integer bankRoll = 0;

    public void deposit(Scanner scanner) {
        System.out.println("How much do you wish to deposit?");
        bankRoll = scanner.nextInt();
    }

    public Integer wager(Scanner scanner, boolean aiPlayer) {
        if (aiPlayer) {
            return 0;
        } else {
            System.out.println("How much do you wish to wager?");

            Integer bet = scanner.nextInt();
            bankRoll = bankRoll - bet;

            return bet;
        }
    }

    public void updateBalance(Integer payOut) {
        bankRoll+=payOut;
    }

    public List<Integer> hit(List<Integer> playerHand, List<Integer> deck, Deck dealerDeck) {
        playerHand.add(deck.get(0));

        dealerDeck.calculateAces(playerHand);

        deck.remove(deck.get(0));
        return playerHand;
    }
}

