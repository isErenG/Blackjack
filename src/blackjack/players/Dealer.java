package blackjack.players;

import blackjack.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer {
    public List<List<Integer>> dealCards(List<Integer> deck, List<Integer> dealerHand, List<Integer> playerHand, Deck dealerDeck) {

        // Dealer deals a total of 4 cards in a 1v1
        for (int i = 0; i < 4; i++) {

            Integer card = deck.get(i);

            if (card == 1) {
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

        dealerDeck.calculateAces(dealerHand);
        dealerDeck.calculateAces(playerHand);


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

    public Integer payOut(Integer wager, String winner){
        if (Objects.equals(winner, "player")) {
            return wager*2;
        } else {
            return 0;
        }
    }

}
