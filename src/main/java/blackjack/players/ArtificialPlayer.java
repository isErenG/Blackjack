package blackjack.players;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArtificialPlayer {

    List<Integer> list = Arrays.asList(2, 3, 4, 5, 6);

    public Integer getRunningCount(Scanner scanner) {
        System.out.println("How many times should the game run?");
        return scanner.nextInt() - 1;
    }

    public boolean isPlayerAI(Scanner scanner) {
        System.out.println("Do you want the AI to play it and give statistics?");
        return scanner.nextBoolean();
    }

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
