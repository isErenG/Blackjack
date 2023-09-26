package blackjack.game_mechanics;

import blackjack.Deck;
import blackjack.players.Dealer;

import java.util.List;

public class GameMechanics {
    public String checkWinner(Deck dealerDeck, List<Integer> dealerHand, List<Integer> playerHand, Dealer dealer, Integer bet) {
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
