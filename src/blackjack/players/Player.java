package blackjack.players;

import blackjack.Deck;

import java.util.List;

public class Player {

    void wager(Dealer dealer) {
        //Wager bet

    }

    public List<Integer> hit(List<Integer> playerHand, List<Integer> deck, Deck dealerDeck) {
        playerHand.add(deck.get(0));

        dealerDeck.calculateAces(playerHand);

        deck.remove(deck.get(0));
        return playerHand;
    }
}

