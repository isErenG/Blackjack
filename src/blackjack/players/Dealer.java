package blackjack.players;

import blackjack.Deck;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    Deck dealerDeck = new Deck();
    List<Integer> deck = dealerDeck.getCards();
    List<Integer> dealerHand = new ArrayList<>();
    List<Integer> playerHand = new ArrayList<>();


    public List<List<Integer>> dealCards() {

        Integer[] number = {0,2};

        for (Integer i : number) {
            dealerHand.add(deck.get(i));
            deck.remove(deck.get(i));
        }

        playerHand.add(deck.get(0));
        deck.remove(deck.get(0));


        List<List<Integer>> hands = new ArrayList<>();

        hands.add(dealerHand); // Add the dealer's hand list
        hands.add(playerHand); // Add the player's hand list

        return(hands);
    }

    public List<Integer> hit() {
        playerHand.add(deck.get(0));
        return null;
    }

}
