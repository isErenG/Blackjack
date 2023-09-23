package blackjack;

import blackjack.players.Dealer;
import blackjack.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    Deck dealerDeck = new Deck();
    List<Integer> deck = dealerDeck.getCards();
    List<Integer> dealerHand = new ArrayList<>();
    List<Integer> playerHand = new ArrayList<>();


    public void startGame() {
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
                if (!(scanner.nextLine().equalsIgnoreCase("hit"))) {
                    wantsHit = false;
                }
                System.out.println(player.hit(playerHand, deck));
                System.out.println("Do you wish to hit or stand?");

            } else {
                System.out.println("Busted!");
                return;
            }

        }

        boolean shouldDealerHit = true;
        while (shouldDealerHit) {
            if (updateHands(dealerHand) >= 17) {
                System.out.println(checkWinner(dealerHand, playerHand));
                shouldDealerHit = false;

            } else {
                System.out.println(dealer.hit(dealerHand, deck));
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
            return "Player";

        } else {
            return "Dealer";
        }

    }
}
