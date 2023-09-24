package blackjack;

import blackjack.players.Dealer;
import blackjack.players.Player;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    Deck dealerDeck = new Deck();
    List<Integer> deck = dealerDeck.getCards();
    List<Integer> dealerHand = new ArrayList<>();
    List<Integer> playerHand = new ArrayList<>();


    public void startGame() throws InterruptedException {
        Dealer dealer = new Dealer();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);

        List<List<Integer>> hands = dealer.dealCards(deck, dealerHand, playerHand);

        List<Integer> dealerHand = hands.get(0);
        List<Integer> playerHand = hands.get(1);


        System.out.println("The dealer has a " + dealerHand.get(0) + "\nWhile you have a " + playerHand + "\nDo you wish to hit or stand?");


        boolean wantsHit = true;

        while (wantsHit) {
            if (!(updateHands(playerHand) > 21)) {
                if (scanner.nextLine().equalsIgnoreCase("hit")) {
                    System.out.println(player.hit(playerHand, deck));
                    System.out.println("Do you wish to hit or stand?");
                } else {
                    wantsHit = false;
                }
            } else {
                System.out.println("Busted! You lose!");
                return;
            }
        }

        boolean shouldDealerHit = true;

        System.out.println("The dealer reveals a " + dealerHand.get(1));
        while (shouldDealerHit) {
            TimeUnit.SECONDS.sleep(2);
            if (updateHands(dealerHand) >= 17) {
                System.out.println(checkWinner(dealerHand, playerHand));
                shouldDealerHit = false;

            } else {
                System.out.println("The dealer's hand is: " + dealer.hit(dealerHand, deck));
            }
        }
    }

    public Integer updateHands(List<Integer> hands) {
        Integer handValue = 0;
        for (Integer i : hands) {
            handValue += i;
        }
        return handValue;
    }

    public String checkWinner(List<Integer> dealerHand, List<Integer> playerHand) {
        Integer dealerValue = updateHands(dealerHand);
        Integer playerValue = updateHands(playerHand);

        if (playerValue > dealerValue) {
            return "The Player wins!";
        } else if (dealerValue > 21) {
            return "Dealer busts! Player wins!";
        } else {
            return "The Dealer wins!";
        }
    }
}