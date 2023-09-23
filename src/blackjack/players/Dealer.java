package blackjack.players;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public List<List<Integer>> dealCards(List<Integer> deck, List<Integer> dealerHand, List<Integer> playerHand) {

        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                dealerHand.add(deck.get(i));
            } else {
                playerHand.add(deck.get(i));
            }
        }

        for (int i = 0; i < 4; i++) {
            deck.remove(deck.get(0));
        }

        List<List<Integer>> hands = new ArrayList<>();

        hands.add(dealerHand); // Add the dealer's hand list
        hands.add(playerHand); // Add the player's hand list


        return (hands);
    }

    public List<Integer> hit(List<Integer> dealerHand, List<Integer> deck) {
        dealerHand.add(deck.get(0));
        deck.remove(deck.get(0));
        return dealerHand;
    }

}
