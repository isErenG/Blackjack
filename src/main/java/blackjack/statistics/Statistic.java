package blackjack.statistics;

import java.util.ArrayList;
import java.util.List;

public class Statistic {

    List<String> winners = new ArrayList<>();

    public void addWinner(String winner) {
        winners.add(winner);
    }

    public void displayStatistics() {
        
        int dealer = 0;
        int player = 0;
        int push = 0;
        
        for (String i : winners){
            if (i.equals("dealer")){
                dealer+=1;
            } else if (i.equals("player")) {
                player+=1;
            } else {
                push+=1;
            }
        }

        System.out.println("Player: " + player);
        System.out.println("Dealer: " + dealer);
        System.out.println("Push: " + push);

    }
}
