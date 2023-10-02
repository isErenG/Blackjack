package blackjack.game_mechanics;

import blackjack.players.ArtificialPlayer;
import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.statistics.Statistic;

import java.util.List;
import java.util.Scanner;

public class GameMechanics {

    ArtificialPlayer artificialPlayer = new ArtificialPlayer();
    Statistic statistics = new Statistic();

    int runningCount = 0;

    // Asks the player for their next move (hit or stand)
    public String getNextMove(Scanner scanner, int playerHandValue, int dealerHand, boolean aiPlayer) {
        if (aiPlayer) {
            return artificialPlayer.chooseMove(playerHandValue, dealerHand);

        } else {
            System.out.println("Do you wish to hit or stand?");
            return scanner.nextLine().toLowerCase();
        }
    }

    // Asks the player to confirm if they want to quit the game
    public String quitGame(Scanner scanner, boolean aiPlayer, int number) {
        if (aiPlayer) {
            if (runningCount >= number) {
                statistics.displayStatistics();
                return "no";
            } else {
                runningCount += 1;
                return "yes";
            }

        } else {
            System.out.println("\nDo you want to continue playing?\n[Options] Press enter or type no");
            return scanner.nextLine().toLowerCase();
        }
    }

    public String checkWinner(Deck dealerDeck, List<Integer> dealerHand, List<Integer> playerHand, Dealer dealer, Integer bet, Player player) {
        Integer dealerValue = dealerDeck.updateHands(dealerHand);
        Integer playerValue = dealerDeck.updateHands(playerHand);

        if (playerValue > dealerValue && playerValue <= 21) {
            statistics.addWinner("player");
            return "\nThe Player wins!\nPayout:$" + dealer.payOut(bet, "player", player);

        } else if (playerValue == 21 && playerHand.size() == 2) {
            statistics.addWinner("player");
            return "\nBlackjack! The Player wins!\nPayout:$" + dealer.payOut(bet, "player", player);

        } else if (dealerValue > 21) {
            statistics.addWinner("player");
            return "\nDealer busts! Player wins!\nPayout:$" + dealer.payOut(bet, "player", player);

        } else if (playerValue > 21) {
            statistics.addWinner("dealer");
            return "\nThe Player busts! The Dealer wins!\nPayout:$" + dealer.payOut(bet, "dealer", player);

        } else {
            if (dealerValue.equals(playerValue)) {
                statistics.addWinner("push");
                return "\nPush!\nPayout:$" + dealer.payOut(bet, "stalemate", player);

            } else {
                statistics.addWinner("dealer");
                return "\nThe Dealer wins!\nPayout:$" + dealer.payOut(bet, "dealer", player);
            }
        }
    }
}
