package blackjack;

import blackjack.players.Dealer;
import blackjack.players.Player;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {


    // Create a new deck of cards using the Deck class
    Deck dealerDeck = new Deck();

    // Get the list of cards from the dealer's deck
    List<Integer> deck = dealerDeck.getCards();

    // Initialize an empty list to represent the dealer's hand
    List<Integer> dealerHand = new ArrayList<>();

    // Initialize an empty list to represent the player's hand
    List<Integer> playerHand = new ArrayList<>();


    public void startGame() throws InterruptedException {
        // Initialize the dealer and player
        Dealer dealer = new Dealer();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);

        // Call the dealCards method from the dealer which returns the player and dealer hands
        List<List<Integer>> hands = dealer.dealCards(deck, dealerHand, playerHand, dealerDeck);
        List<Integer> dealerHand = hands.get(0);
        List<Integer> playerHand = hands.get(1);


        boolean wantsHit = true;

        Integer bet = player.wager(scanner);
        scanner.nextLine();

        System.out.println("The dealer has a " + dealerHand.get(0) + "\nWhile you have a " + playerHand + "\nDo you wish to hit or stand?");

        while (wantsHit) {
            // Immediately check if the player has not busted
            if (!(dealerDeck.updateHands(playerHand) > 21)) {
                if (scanner.nextLine().equalsIgnoreCase("hit")) {
                    System.out.println(player.hit(playerHand, deck, dealerDeck));
                    System.out.println("Do you wish to hit or stand?");
                } else {
                    // If the player does not choose hit, they leave the while loop
                    wantsHit = false;
                }
            } else {
                // If the player exceeds 21, we immediately check the winner to determine that the player busted
                System.out.println(checkWinner(dealerHand, playerHand, dealer, bet));
                return;
            }
        }

        boolean shouldDealerHit = true;

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
    }


    public String checkWinner(List<Integer> dealerHand, List<Integer> playerHand, Dealer dealer, Integer bet) {
        Integer dealerValue = dealerDeck.updateHands(dealerHand);
        Integer playerValue = dealerDeck.updateHands(playerHand);

        if (playerValue > dealerValue && playerValue < 21) {
            dealer.payOut(bet, "player");
            return "The Player wins!";

        } else if (dealerValue > 21) {
            dealer.payOut(bet, "player");
            return "Dealer busts! Player wins!";

        } else if (playerValue == 21) {
            dealer.payOut(bet, "player");
            return "Blackjack! Player wins!";

        } else if (playerValue > 21) {
            dealer.payOut(bet, "dealer");
            return "The Player busts! The Dealer wins!";

        } else {
            dealer.payOut(bet, "dealer");
            return "The Dealer wins!";
        }
    }
}