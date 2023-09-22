package blackjack;

import blackjack.players.Dealer;

public class Game {

    public void startGame(){
        Dealer dealer = new Dealer();

        System.out.println(dealer.dealCards());
        System.out.println(dealer.hit());
        System.out.println(dealer);

    }
}
