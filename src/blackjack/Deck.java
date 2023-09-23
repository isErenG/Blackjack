package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    public List<Integer> getCards() {
        Integer[] deck = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};
        List<Integer> dealerShoe = new ArrayList<>();

        //Casino game of BJ has a shoe of 6 decks.
        for (int i = 0; i < 6; i++) {
            for (int x = 0; x < 4; x++)
                dealerShoe.addAll(Arrays.asList(deck));
        }

        Collections.shuffle(dealerShoe);
        return (dealerShoe);
    }
}



