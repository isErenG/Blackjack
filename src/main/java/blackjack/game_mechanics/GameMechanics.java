package blackjack.game_mechanics;

import blackjack.Deck;
import blackjack.players.ArtificialPlayer;
import blackjack.players.Dealer;

import java.util.List;
import java.util.Scanner;

public class GameMechanics {

    ArtificialPlayer aiPlayer = new ArtificialPlayer();

    // Asks the player for their next move (hit or stand)
    public String getNextMove(Scanner scanner, int playerHandValue, int dealerHand) {
        if (aiPlayer.aiPlayer(true)) {
            return aiPlayer.chooseMove(playerHandValue, dealerHand);

        } else {
            System.out.println("Do you wish to hit or stand?");
            return scanner.nextLine().toLowerCase();
        }
    }

    // Asks the player to confirm if they want to quit the game
    public String quitGame(Scanner scanner) {
        if (aiPlayer.aiPlayer(true)) {
            return "yes";

        } else {
            System.out.println("\nDo you want to continue playing?\n[Options] Press enter or type no");
            return scanner.nextLine().toLowerCase();
        }
    }

    public String checkWinner(Deck dealerDeck, List<Integer> dealerHand, List<Integer> playerHand, Dealer dealer, Integer bet) {
        Integer dealerValue = dealerDeck.updateHands(dealerHand);
        Integer playerValue = dealerDeck.updateHands(playerHand);

        if (playerValue > dealerValue && playerValue < 21) {
            return "\nThe Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (playerValue == 21 && playerHand.size() == 2) {
            return "\nBlackjack! The Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (dealerValue > 21) {
            return "\nDealer busts! Player wins!\n$" + dealer.payOut(bet, "player");

        } else if (playerValue > 21) {
            dealer.payOut(bet, "dealer");
            return "\nThe Player busts! The Dealer wins!\n$" + dealer.payOut(bet, "dealer");

        } else {
            if (dealerValue.equals(playerValue)) {
                return "\nPush!\n$" + dealer.payOut(bet, "stalemate");

            } else {
                return "\nThe Dealer wins!\nPayout:$" + dealer.payOut(bet, "dealer");
            }
        }
    }
}
