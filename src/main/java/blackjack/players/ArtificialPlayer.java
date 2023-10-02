package blackjack.players;

import java.util.Arrays;
import java.util.List;

public class ArtificialPlayer {

    List<Integer> list = Arrays.asList(2, 3, 4, 5, 6);

    public String chooseMove(int playerHandValue, int dealerHand) {

        if (playerHandValue <= 11) {
            return "hit";

        } else if (playerHandValue >= 17) {
            return "stand";

        } else if (!list.contains(dealerHand)) {
            return "hit";

        } else {
            return "stand";
        }
    }
}
