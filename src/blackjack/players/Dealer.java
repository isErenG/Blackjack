package blackjack.players;

import blackjack.Deck;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public List<List<Integer>> dealCards(List<Integer> deck, List<Integer> dealerHand, List<Integer> playerHand) {

        // Dealer deals a total of 4 cards in a 1v1
        for (int i = 0; i < 4; i++) {

            Integer card = deck.get(i);

            if (card==1){
                card = 11;
            }

            // Dealer hands out cards in correct order by giving odd cards to himself and even cards to the player
            if (i % 2 == 0) {
                dealerHand.add(card);
            } else {
                playerHand.add(card);
            }
        }
        // Remove the first 4 cards from the shoe
        for (int i = 0; i < 4; i++) {
            deck.remove(deck.get(0));
        }

        // Make a new list to add our hands and return it
        List<List<Integer>> hands = new ArrayList<>();
        hands.add(dealerHand); // Add the dealer's hand list
        hands.add(playerHand); // Add the player's hand list

        return (hands);
    }

    public List<Integer> hit(List<Integer> dealerHand, List<Integer> deck, Deck dealerDeck) {
        dealerHand.add(deck.get(0));

        dealerDeck.calculateAces(dealerHand);

        deck.remove(deck.get(0));
        return dealerHand;
    }

}
