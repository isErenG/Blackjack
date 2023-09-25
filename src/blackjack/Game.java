package blackjack;

import blackjack.players.Dealer;
import blackjack.players.Player;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    // Create a new deck of cards using the Deck class
    Deck dealerDeck = new Deck();

    // Get the list of cards from the dealer's deck
    List<Integer> deck = dealerDeck.getCards();

    // Initialize an empty list to represent the dealer's hand
    List<Integer> dealerHand = new ArrayList<>();

    // Initialize an empty list to represent the player's hand
    List<Integer> playerHand = new ArrayList<>();

    private String getNextMove() {
        return scanner.nextLine().toLowerCase();
    }

    public void startGame() throws InterruptedException {
        // Initialize the dealer and player
        Dealer dealer = new Dealer();
        Player player = new Player();

        boolean continuePlaying = true;

        while (continuePlaying) {

            boolean wantsHit = true;
            boolean shouldDealerHit = true;

            // Call the dealCards method from the dealer which returns the player and dealer hands
            List<List<Integer>> hands = dealer.dealCards(deck, dealerHand, playerHand, dealerDeck);
            List<Integer> dealerHand = hands.get(0);
            List<Integer> playerHand = hands.get(1);

            Integer bet = player.wager(scanner);
            scanner.nextLine();

            System.out.println("\nThe dealer has a " + dealerHand.get(0) + "\nWhile you have a " + playerHand + "\nDo you wish to hit or stand?");

            while (wantsHit) {
                // Calculate the player's hand value
                int playerHandValue = dealerDeck.updateHands(playerHand);

                // Check if the player has busted
                if (playerHandValue > 21) {
                    System.out.println("The player has busted.");
                    shouldDealerHit = false;
                    wantsHit = false;
                } else if (getNextMove().equalsIgnoreCase("hit")) {
                    // Player wants to hit
                    System.out.println("\n" + player.hit(playerHand, deck, dealerDeck));
                    System.out.println("Do you wish to hit or stand?");
                } else {
                    // Player does not choose hit, so leave the loop
                    wantsHit = false;
                }
            }


            System.out.println("The dealer reveals a " + dealerHand.get(1));
            while (shouldDealerHit) {
                TimeUnit.SECONDS.sleep(2);
                if (dealerDeck.updateHands(dealerHand) >= 17) {
                    System.out.println(checkWinner(dealerHand, playerHand, dealer, bet));
                    shouldDealerHit = false;

                } else {
                    System.out.println("The dealer's hand is: " + dealer.hit(dealerHand, deck, dealerDeck));
                }
            }

            System.out.println("\nDo you want to continue playing?\n[Options] yes or no");
            if (getNextMove().equalsIgnoreCase("No")) {
                continuePlaying = false;
            } else {
                playerHand.clear();
                dealerHand.clear();
            }
        }
    }

    public String checkWinner(List<Integer> dealerHand, List<Integer> playerHand, Dealer dealer, Integer bet) {
        Integer dealerValue = dealerDeck.updateHands(dealerHand);
        Integer playerValue = dealerDeck.updateHands(playerHand);

        if (playerValue > dealerValue && playerValue < 21) {
            return "The Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (playerValue == 21 && playerHand.size() == 2) {
            return "Blackjack! The Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (dealerValue > 21) {
            return "Dealer busts! Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (playerValue > 21) {
            dealer.payOut(bet, "dealer");
            return "The Player busts! The Dealer wins!\n$" + dealer.payOut(bet, "dealer");

        } else {
            if (dealerValue.equals(playerValue)) {
                return "Push!\n$" + dealer.payOut(bet, "stalemate");

            } else {
                return "The Dealer wins!\n$" + dealer.payOut(bet, "dealer");
            }
        }
    }

}