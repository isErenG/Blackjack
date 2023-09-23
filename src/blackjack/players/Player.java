package blackjack.players;

import java.util.List;

public class Player {

    void wager(Dealer dealer) {


    }


    public List<Integer> hit(List<Integer> playerHand, List<Integer> deck) {
        playerHand.add(deck.get(0));
        deck.remove(deck.get(0));
        return playerHand;
    }
}

