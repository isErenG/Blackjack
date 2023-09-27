package blackjack;

import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.game_mechanics.GameMechanics;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    // Get the game mechanics from the GameMechanics.class
    GameMechanics gameMechanics = new GameMechanics();

    // Create a new deck of cards using the Deck class
    Deck dealerDeck = new Deck();

    // Get the list of cards from the dealer's deck and create empty lists for each player
    List<Integer> deck = dealerDeck.getCards();
    List<Integer> dealerHand = new ArrayList<>();
    List<Integer> playerHand = new ArrayList<>();

    // Starts the game
    public void startGame() throws InterruptedException {
        // Initialize the dealer and player
        Dealer dealer = new Dealer();
        Player player = new Player();

        boolean continuePlaying = true;

        while (continuePlaying) {

            boolean wantsHit = true;
            boolean shouldDealerHit = true;

            // Deals the cards to the player and dealer
            List<List<Integer>> hands = dealer.dealCards(deck, dealerHand, playerHand, dealerDeck);
            List<Integer> dealerHand = hands.get(0);
            List<Integer> playerHand = hands.get(1);

            // Prompts the player to place a bet
            Integer bet = player.wager(scanner);
            scanner.nextLine();

            // Displays the player's and dealer's hands
            System.out.println("\nThe dealer has a " + dealerHand.get(0) + "\nWhile you have a " + playerHand);

            // While the player wants to hit and hasn't busted, continue hitting
            while (wantsHit) {
                // Calculate the player's hand value
                int playerHandValue = dealerDeck.updateHands(playerHand);

                // Check if the player has busted
                if (playerHandValue > 21) {
                    System.out.println("The player has busted.");
                    shouldDealerHit = false;
                    wantsHit = false;
                } else if (gameMechanics.getNextMove(scanner).equalsIgnoreCase("hit")) {
                    // Player wants to hit
                    System.out.println("\n" + player.hit(playerHand, deck, dealerDeck));
                } else {
                    // Player does not choose hit, so leave the loop
                    wantsHit = false;
                }
            }

            // Reveal the dealer's second card
            System.out.println("\nThe dealer reveals a " + dealerHand.get(1) + "\n");

            // While the dealer needs to hit, continue hitting
            while (shouldDealerHit) {
                TimeUnit.SECONDS.sleep(2);
                if (dealerDeck.updateHands(dealerHand) >= 17) {
                    // Check the winner and display the result
                    System.out.println(gameMechanics.checkWinner(dealerDeck, dealerHand, playerHand, dealer, bet));
                    shouldDealerHit = false;

                } else {
                    // The dealer needs to hit
                    System.out.println("The dealer's hand is: " + dealer.hit(dealerHand, deck, dealerDeck));
                }
            }

            // Ask the player if they want to continue playing
            System.out.println("\nDo you want to continue playing?\n[Options] Press enter or type no");
            if (gameMechanics.quitGame(scanner).equalsIgnoreCase("No")) {
                continuePlaying = false;
            } else {
                // Clear the player and dealer hands
                playerHand.clear();
                dealerHand.clear();
            }
        }
    }
}