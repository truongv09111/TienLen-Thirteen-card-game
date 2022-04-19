import java.util.ArrayList;
import java.util.Scanner;

public class ThreePlayerGame {
    //fields
    public static PlayingHand lastHandPlayed = new PlayingHand();
    private static int cardsPlayed = 0;
    private static boolean allComputerPassed = false;

    //methods
    public static void threePlayerGame(){
        Deck deck = new Deck();
        System.out.println("Deck has been shuffled.");
        System.out.println("Cards have been been dealt.");
        Player player = new Player();
        Computer computerOne = new Computer();
        Computer computerTwo = new Computer();
        Computer computerThree = new Computer();
        Dealer dealer = new Dealer();

        dealer.deal(player, deck);
        player.getHand().sortHand();
        dealer.deal(computerOne, deck);
        computerOne.getHand().sortHand();
        dealer.deal(computerTwo, deck);
        computerTwo.getHand().sortHand();
        dealer.deal(computerThree, deck);
        computerThree.getHand().sortHand();
        System.out.println("Cards left in deck: " + deck.getDeck().size());

        if(dealer.checkforThreeOfSpades(player)){
            player.setTurn(true);
            player.setLead(true);
            System.out.println("You have the lowest three. Your turn.");
        } else if(dealer.checkforThreeOfSpades(computerOne)){
            computerOne.setTurn(true);
            computerOne.setLead(true);
            System.out.println("Computer One has the lowest three. Computer One's turn.");
        } else if(dealer.checkforThreeOfSpades(computerTwo)){
            computerTwo.setTurn(true);
            computerTwo.setLead(true);
            System.out.println("Computer Two has the lowest three. Computer Two's turn.");
        } else if(dealer.checkforThreeOfSpades(computerThree)){
            computerThree.setTurn(true);
            computerThree.setLead(true);
            System.out.println("Computer Three has the lowest three. Computer Three's turn.");
        } else{
            if(dealer.findLowestCardInHand(player, computerOne, computerTwo, computerThree) == player){
                player.setTurn(true);
                player.setLead(true);
                System.out.println("You have the lowest card. Your turn.");
            } else if(dealer.findLowestCardInHand(player, computerOne, computerTwo, computerThree) == computerOne){
                computerOne.setTurn(true);
                computerOne.setLead(true);
                System.out.println("Computer One has the lowest card. Computer One's turn.");
            } else if(dealer.findLowestCardInHand(player, computerOne, computerTwo, computerThree) == computerTwo){
                computerTwo.setTurn(true);
                computerTwo.setLead(true);
                System.out.println("Computer Two has the lowest card. Computer Two's turn.");
            } else if(dealer.findLowestCardInHand(player, computerOne, computerTwo, computerThree) == computerThree){
                computerThree.setTurn(true);
                computerThree.setLead(true);
                System.out.println("Computer Three has the lowest card. Computer Three's turn.");
            }
        }

        if(player.isTurn() && player.isLead()){
            while (true) {
                player.promptPlayHandOverloadedTwo();
                boolean legalPlay = player.getPlayingHand().checkLegalFirstPlay(player);
                if (legalPlay) {
                    lastHandPlayed = player.getPlayingHand();
                    player.setHand(player.getTempHand());
                    break;
                } else {
                    System.out.println("That is not a legal play. Please try again.");
                    player.setPlayingHand(new PlayingHand());

                }
            }
        } else if (computerOne.isTurn() && computerOne.isLead()){
            computerOne.playFirstHand();
            lastHandPlayed = computerOne.getPlayingHand();
        } else if(computerTwo.isTurn() && computerTwo.isLead()){
            computerTwo.playFirstHand();
            lastHandPlayed = computerTwo.getPlayingHand();
        } else if(computerThree.isTurn() && computerThree.isLead()){
            computerThree.playFirstHand();
            lastHandPlayed = computerThree.getPlayingHand();
        }
        boolean playerLastHandPlayed = false;
        boolean computerOneLastHandPlayed = false;
        boolean computerTwoLastHandPlayed = false;
        boolean computerThreeLastHandPlayed = false;

        if (lastHandPlayed == player.getPlayingHand()) {
            playerLastHandPlayed = true;
            System.out.println("Your cards: ");
            player.getHand().showHand(); //output player's hand
            System.out.println("You played: ");
            lastHandPlayed.showHand(); //output what player played
            cardsPlayed += lastHandPlayed.getMyHand().size();
            System.out.println("Cards played: " + cardsPlayed); //output the number of cards that have already been played
            player.setTurn(false);
            computerOne.setTurn(true);
        } else if (lastHandPlayed == computerOne.getPlayingHand()) {
            computerOneLastHandPlayed = true;
            System.out.println("Computer One played: ");
            lastHandPlayed.showHand();
            cardsPlayed += lastHandPlayed.getMyHand().size();
            System.out.println("Cards played: " + cardsPlayed);
            computerOne.setTurn(false);
            computerTwo.setTurn(true);

        } else if (lastHandPlayed == computerTwo.getPlayingHand()) {
            computerTwoLastHandPlayed = true;
            System.out.println("Computer Two played: ");
            lastHandPlayed.showHand();
            cardsPlayed += lastHandPlayed.getMyHand().size();
            System.out.println("Cards played: " + cardsPlayed);
            computerTwo.setTurn(false);
            computerThree.setTurn(true);

        } else if (lastHandPlayed == computerThree.getPlayingHand()) {
            computerThreeLastHandPlayed = true;
            System.out.println("Computer Three played: ");
            lastHandPlayed.showHand();
            cardsPlayed += lastHandPlayed.getMyHand().size();
            System.out.println("Cards played: " + cardsPlayed);
            computerThree.setTurn(false);
            player.setTurn(true);

        }
        pressEnterToContinue();
        boolean playerFinished = false;
        boolean computerOneFinished = false;
        boolean computerTwoFinished = false;
        boolean computerThreeFinished = false;
        boolean nobodyLeads = false;
        ArrayList<Integer> orderedListOfWinners = new ArrayList<Integer>();

        while(true) {

            //check to see who finished
            if(!playerFinished){
                if(player.getHand().getMyHand().size() == 0){
                    playerFinished = true;
                    player.setPass(true);
                    player.setLead(false);
                    computerOne.setLead(false);
                    computerTwo.setLead(false);
                    computerThree.setLead(false);
                    orderedListOfWinners.add(1);
                    System.out.println("----------You have finished.----------");
                }
            }
            if(!computerOneFinished){
                if(computerOne.getHand().getMyHand().size() == 0) {
                    computerOneFinished = true;
                    computerOne.setPass(true);
                    player.setLead(false);
                    computerOne.setLead(false);
                    computerTwo.setLead(false);
                    computerThree.setLead(false);
                    orderedListOfWinners.add(2);
                    System.out.println("----------Computer One finished.----------");
                }
            }
            if(!computerTwoFinished){
                if(computerTwo.getHand().getMyHand().size() == 0){
                    computerTwoFinished = true;
                    computerTwo.setPass(true);
                    player.setLead(false);
                    computerOne.setLead(false);
                    computerTwo.setLead(false);
                    computerThree.setLead(false);
                    orderedListOfWinners.add(3);
                    System.out.println("----------Computer Two finished.----------");
                }
            }
            if(!computerThreeFinished){
                if(computerThree.getHand().getMyHand().size() == 0){
                    computerThreeFinished = true;
                    computerThree.setPass(true);
                    player.setLead(false);
                    computerOne.setLead(false);
                    computerTwo.setLead(false);
                    computerThree.setLead(false);
                    orderedListOfWinners.add(4);
                    System.out.println("----------Computer Three finished.----------");
                }
            }

            //check to see if the game is over
            if(orderedListOfWinners.size() == 3){
                System.out.println("Game has ended.");
                //first place
                if(orderedListOfWinners.get(0) == 1){
                    System.out.println("You won first place!");
                } else if(orderedListOfWinners.get(0) == 2){
                    System.out.println("Computer One won first place!");
                } else if(orderedListOfWinners.get(0) == 3){
                    System.out.println("Computer Two won first place!");
                } else if(orderedListOfWinners.get(0) == 4){
                    System.out.println("Computer Three won first place!");
                }
                //second place
                if(orderedListOfWinners.get(1) == 1){
                    System.out.println("You won second place!");
                } else if(orderedListOfWinners.get(1) == 2){
                    System.out.println("Computer One won second place!");
                } else if(orderedListOfWinners.get(1) == 3){
                    System.out.println("Computer Two won second place!");
                } else if(orderedListOfWinners.get(1) == 4){
                    System.out.println("Computer Three won second place!");
                }
                //third place
                if(orderedListOfWinners.get(2) == 1){
                    System.out.println("You won third place!");
                } else if(orderedListOfWinners.get(2) == 2){
                    System.out.println("Computer One won third place!");
                } else if(orderedListOfWinners.get(2) == 3){
                    System.out.println("Computer Two won third place!");
                } else if(orderedListOfWinners.get(2) == 4){
                    System.out.println("Computer Three won third place!");
                }
                //fourth place
                if(!orderedListOfWinners.contains(1)){
                    System.out.println("You won fourth place!");
                } else if(!orderedListOfWinners.contains(2)){
                    System.out.println("Computer One won fourth place!");
                } else if(!orderedListOfWinners.contains(3)){
                    System.out.println("Computer Two won fourth place!");
                } else if(!orderedListOfWinners.contains(4)){
                    System.out.println("Computer Three won fourth place!");
                }
                break;
            }

            if(nobodyLeads){
                int count = 0; //doesnt do anything
            } else if(computerOne.isPass() && computerTwo.isPass() && computerThree.isPass()){
                allComputerPassed = true;
            } else if(player.isPass() && computerTwo.isPass() && computerThree.isPass()){
                computerOne.setLead(true);
            } else if(player.isPass() && computerOne.isPass() && computerThree.isPass()){
                computerTwo.setLead(true);

            } else if(player.isPass() && computerOne.isPass() && computerTwo.isPass()){
                computerThree.setLead(true);
            }

            if (player.isLead() && !playerFinished) {
                if(player.isTurn()){
                    playerLastHandPlayed = false;
                    if(player.isGoAnything()){
                        System.out.println("You can play any hand you want.");
                        while (true) {
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            boolean legalPlay = player.getPlayingHand().checkLegalPlay();
                            if (legalPlay) {
                                lastHandPlayed = player.getPlayingHand();
                                player.setGoAnything(false);
                                player.setHand(player.getTempHand());
                                if(!computerOneFinished){
                                    computerOne.setPass(false);
                                }if(!computerTwoFinished){
                                    computerTwo.setPass(false);
                                }if(!computerThreeFinished) {
                                    computerThree.setPass(false);
                                }
                                if(computerOne.isPass() && computerTwo.isPass() && computerThree.isPass()){
                                    allComputerPassed = true;
                                } else{
                                    allComputerPassed = false;
                                }
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            } else {
                                System.out.println("That is not a valid play. Please try again.");

                            }

                        }


                    }else if (computerOne.isPass() && computerTwo.isPass() && computerThree.isPass()) {
                        player.setGoAnything(true);
                        System.out.println("You can play any hand you want.");
                        while (true) {
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            boolean legalPlay = player.getPlayingHand().checkLegalPlay();
                            if (legalPlay) {
                                lastHandPlayed = player.getPlayingHand();
                                player.setGoAnything(false);
                                player.setHand(player.getTempHand());
                                if(!computerOneFinished){
                                    computerOne.setPass(false);
                                }if(!computerTwoFinished){
                                    computerTwo.setPass(false);
                                }if(!computerThreeFinished) {
                                    computerThree.setPass(false);
                                }
                                if(computerOne.isPass() && computerTwo.isPass() && computerThree.isPass()){
                                    allComputerPassed = true;
                                } else{
                                    allComputerPassed = false;
                                }
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            } else {
                                System.out.println("That is not a valid play. Please try again.");

                            }

                        }
                    } else {
                        while (true) {
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if (player.isPass()) {
                                player.setTurn(false);
                                player.setLead(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)) {
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setTurn(false);
                                    if(!computerOneFinished) {
                                        computerOne.setTurn(true);
                                    } else if(!computerTwoFinished){
                                        computerTwo.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                    }
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    }

                } else if(computerOne.isTurn()){
                    if(!computerOne.isPass()) {

                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setPass(true);
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        }
                    } else{
                        computerOne.setTurn(false);
                        if(!computerTwoFinished) {
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished){
                            player.setTurn(true);
                        }
                    }

                } else if(computerTwo.isTurn()){
                    if(!computerTwo.isPass()) {

                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setPass(true);
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        }
                    } else{
                        computerTwo.setTurn(false);
                        if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished) {
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        }
                    }
                } else if(computerThree.isTurn()){
                    if(!computerThree.isPass()) {

                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setPass(true);
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        }
                    } else{
                        computerThree.setTurn(false);
                        if(!playerFinished){
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        }
                    }
                }

            } else if(computerOne.isLead() && !computerOneFinished){
                if(computerOne.isTurn()){
                    computerOneLastHandPlayed = false;
                    if(computerOne.isGoAnything()){
                        computerOne.setGoAnything(false);
                        if(!playerFinished) {
                            player.setPass(false);
                        }if(!computerTwoFinished){
                            computerTwo.setPass(false);
                        } if(!computerThreeFinished){
                            computerThree.setPass(false);
                        }
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards();
                        computerOne.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerOne.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerOne.getHand().removeCard(bestCards.get(i));
                        }
                        computerOne.getHand().sortHand();
                        lastHandPlayed = computerOne.getPlayingHand();
                        computerOne.setTurn(false);
                        if(!computerTwoFinished) {
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished){
                            player.setTurn(true);
                        }

                    }else if(player.isPass() && computerTwo.isPass() && computerThree.isPass()){

                        if(!playerFinished) {
                            player.setPass(false);
                        }if(!computerTwoFinished){
                            computerTwo.setPass(false);
                        } if(!computerThreeFinished){
                            computerThree.setPass(false);
                        }
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards();
                        computerOne.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerOne.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerOne.getHand().removeCard(bestCards.get(i));
                        }
                        computerOne.getHand().sortHand();
                        lastHandPlayed = computerOne.getPlayingHand();
                        computerOne.setTurn(false);
                        if(!computerTwoFinished) {
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished){
                            player.setTurn(true);
                        }
                    } else{
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if(bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for(int i=0; i<bestCards.size(); i++){
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setPass(true);
                            computerOne.setTurn(false);
                            computerOne.setLead(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }

                        }

                    }
                }else if(computerTwo.isTurn()){
                    if(!computerTwo.isPass()){

                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if(bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for(int i=0; i<bestCards.size(); i++){
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setPass(true);
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        }
                    } else {
                        computerTwo.setTurn(false);
                        if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished) {
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        }
                    }


                } else if(computerThree.isTurn()){
                    if(!computerThree.isPass()) {

                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setPass(true);
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        }
                    } else{
                        computerThree.setTurn(false);
                        if(!playerFinished){
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        }
                    }
                } else if(player.isTurn()){
                    if(!player.isPass()){

                        while(true){
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if(player.isPass()){
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)){
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setTurn(false);
                                    if(!computerOneFinished) {
                                        computerOne.setTurn(true);
                                    } else if(!computerTwoFinished){
                                        computerTwo.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                    }
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    } else{
                        player.setTurn(false);
                        if(!computerOneFinished) {
                            computerOne.setTurn(true);
                        }else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        }
                    }

                }
            } else if(computerTwo.isLead() && !computerTwoFinished){
                if(computerTwo.isTurn()){
                    computerTwoLastHandPlayed = false;
                    if(computerTwo.isGoAnything()){
                        computerTwo.setGoAnything(false);
                        if(!playerFinished) {
                            player.setPass(false);
                        }
                        if(!computerOneFinished){
                            computerOne.setPass(false);
                        }
                        if(!computerThreeFinished){
                            computerThree.setPass(false);
                        }
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards();
                        computerTwo.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerTwo.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerTwo.getHand().removeCard(bestCards.get(i));
                        }
                        computerTwo.getHand().sortHand();
                        lastHandPlayed = computerTwo.getPlayingHand();
                        computerTwo.setTurn(false);
                        if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished) {
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        }
                    } else if(player.isPass() && computerOne.isPass() && computerThree.isPass()){
                        if(!playerFinished) {
                            player.setPass(false);
                        }
                        if(!computerOneFinished){
                            computerOne.setPass(false);
                        }
                        if(!computerThreeFinished){
                            computerThree.setPass(false);
                        }
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards();
                        computerTwo.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerTwo.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerTwo.getHand().removeCard(bestCards.get(i));
                        }
                        computerTwo.getHand().sortHand();
                        lastHandPlayed = computerTwo.getPlayingHand();
                        computerTwo.setTurn(false);
                        if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished) {
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        }
                    } else{
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if(bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for(int i=0; i<bestCards.size(); i++){
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setPass(true);
                            computerTwo.setTurn(false);
                            computerTwo.setLead(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }

                        }
                    }

                } else if(computerThree.isTurn()){
                    if(!computerThree.isPass()) {

                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setPass(true);
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        }
                    } else{
                        computerThree.setTurn(false);
                        if(!playerFinished){
                            player.setTurn(true);
                        } else if(!computerOneFinished) {
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        }
                    }

                } else if(player.isTurn()){
                    if(!player.isPass()){

                        while(true){
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if(player.isPass()){
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)){
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setTurn(false);
                                    if(!computerOneFinished) {
                                        computerOne.setTurn(true);
                                    } else if(!computerTwoFinished){
                                        computerTwo.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                    }
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    } else{
                        player.setTurn(false);
                        if(!computerOneFinished) {
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        }
                    }

                } else if(computerOne.isTurn()){
                    if(!computerOne.isPass()) {

                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setPass(true);
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        }
                    } else{
                        computerOne.setTurn(false);
                        if(!computerTwoFinished) {
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished){
                            player.setTurn(true);
                        }
                    }

                }
            } else if(computerThree.isLead() && !computerThreeFinished){
                if(computerThree.isTurn()){
                    computerThreeLastHandPlayed = false;
                    if(computerThree.isGoAnything()){
                        computerThree.setGoAnything(false);
                        if(!playerFinished) {
                            player.setPass(false);
                        }
                        if(!computerOneFinished){
                            computerOne.setPass(false);
                        }
                        if(!computerTwoFinished){
                            computerTwo.setPass(false);
                        }
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards();
                        computerThree.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerThree.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerThree.getHand().removeCard(bestCards.get(i));
                        }
                        computerThree.getHand().sortHand();
                        lastHandPlayed = computerThree.getPlayingHand();
                        computerThree.setTurn(false);
                        if(!playerFinished){
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        }
                    } else if(player.isPass() && computerOne.isPass() && computerTwo.isPass()){
                        if(!playerFinished) {
                            player.setPass(false);
                        }
                        if(!computerOneFinished){
                            computerOne.setPass(false);
                        }
                        if(!computerTwoFinished){
                            computerTwo.setPass(false);
                        }
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards();
                        computerThree.setPlayingHand(new PlayingHand());
                        for(int i=0; i< bestCards.size(); i++){
                            computerThree.getPlayingHand().addCard(bestCards.get(i));
                        }
                        for(int i=0; i<bestCards.size(); i++){
                            computerThree.getHand().removeCard(bestCards.get(i));
                        }
                        computerThree.getHand().sortHand();
                        lastHandPlayed = computerThree.getPlayingHand();
                        computerThree.setTurn(false);
                        if(!playerFinished){
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        }
                    } else{
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if(bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for(int i=0; i<bestCards.size(); i++){
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setTurn(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setPass(true);
                            computerThree.setTurn(false);
                            computerThree.setLead(false);
                            if(!playerFinished){
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }

                        }
                    }

                } else if(player.isTurn()){
                    if(!player.isPass()){

                        while(true){
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if(player.isPass()){
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                }
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)){
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setTurn(false);
                                    if(!computerOneFinished) {
                                        computerOne.setTurn(true);
                                    } else if(!computerTwoFinished){
                                        computerTwo.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                    }
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    } else{
                        player.setTurn(false);
                        if(!computerOneFinished) {
                            computerOne.setTurn(true);
                        } else if(!computerTwoFinished){
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        }
                    }

                } else if(computerOne.isTurn()){
                    if(!computerOne.isPass()) {

                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setPass(true);
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        }
                    } else{
                        computerOne.setTurn(false);
                        if(!computerTwoFinished) {
                            computerTwo.setTurn(true);
                        } else if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished){
                            player.setTurn(true);
                        }
                    }

                } else if(computerTwo.isTurn()){
                    if(!computerTwo.isPass()){

                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if(bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for(int i=0; i<bestCards.size(); i++){
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setPass(true);
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        }
                    } else {
                        computerTwo.setTurn(false);
                        if(!computerThreeFinished){
                            computerThree.setTurn(true);
                        } else if(!playerFinished) {
                            player.setTurn(true);
                        } else if(!computerOneFinished){
                            computerOne.setTurn(true);
                        }
                    }


                }
            } else if (playerFinished && playerLastHandPlayed){
                while(true){
                    if (computerOne.isTurn()) {
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setTurn(false);
                            computerOne.setLead(true);
                            if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setTurn(false);
                            if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            } else if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            }
                        }


                    } else if(computerTwo.isTurn()){
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setTurn(false);
                            computerTwo.setLead(true);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished){
                                computerThree.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                                computerOne.setLead(true);
                                computerOne.setGoAnything(true);
                            }
                            break;

                        }
                    } else if(computerThree.isTurn()){
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setLead(true);
                            computerThree.setTurn(false);
                            if(!computerOneFinished) {
                                computerOne.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setTurn(false);
                            if(!computerOneFinished) {
                                computerOne.setTurn(true);
                                computerOne.setLead(true);
                                computerOne.setGoAnything(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                                computerTwo.setLead(true);
                                computerTwo.setGoAnything(true);
                            }
                            break;

                        }
                    }
                }
            } else if (computerOneFinished && computerOneLastHandPlayed){
                boolean breakOut = false;
                while(true){
                    if(breakOut){
                        break;
                    } else if(computerTwo.isLead()){
                        break;
                    }
                    if (computerTwo.isTurn()) {
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setLead(true);
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished) {
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setTurn(false);
                            if(!computerThreeFinished) {
                                computerThree.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                            }
                        }


                    } else if (computerThree.isTurn()) {
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setLead(true);
                            computerThree.setTurn(false);
                            if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setTurn(false);
                            if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerTwoFinished){
                                computerTwo.setTurn(true);
                                computerTwo.setLead(true);
                                computerTwo.setGoAnything(true);
                            }
                            break;
                        }


                    } else if(player.isTurn()){
                        while(true){
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if(player.isPass()){
                                player.setPass(false);
                                player.setTurn(false);
                                if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                    computerTwo.setLead(true);
                                    computerTwo.setGoAnything(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                    computerThree.setLead(true);
                                    computerThree.setGoAnything(true);
                                }
                                breakOut = true;
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)){
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setLead(true);
                                    player.setTurn(false);
                                    computerThree.setPass(true);
                                    if(!computerTwoFinished) {
                                        computerTwo.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                        computerThree.setPass(false);
                                    }
                                    breakOut = true;
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    }
                }
            } else if(computerTwoFinished && computerTwoLastHandPlayed) {
                boolean breakOut = false;
                while (true){
                    if(breakOut){
                        break;
                    }
                    if (computerThree.isTurn()) {
                        System.out.println("Computer Three's turn.");
                        ArrayList<Cards> bestCards = computerThree.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerThree.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerThree.getHand().removeCard(bestCards.get(i));
                            }
                            computerThree.getHand().sortHand();
                            lastHandPlayed = computerThree.getPlayingHand();
                            computerThree.setLead(true);
                            computerThree.setTurn(false);
                            if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Three passes.");
                            computerThree.setTurn(false);
                            if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                        }


                    } else if (player.isTurn()) {
                        while (true) {
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if (player.isPass()) {
                                player.setPass(false);
                                player.setTurn(false);
                                if(!computerOneFinished) {
                                    computerOne.setTurn(true);
                                } else if(!computerThreeFinished){
                                    computerThree.setTurn(true);
                                    computerThree.setLead(true);
                                    computerThree.setGoAnything(true);
                                }

                                breakOut = true;
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)) {
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setLead(true);
                                    player.setTurn(false);
                                    if(!computerOneFinished) {
                                        computerOne.setTurn(true);
                                    } else if(!computerThreeFinished){
                                        computerThree.setTurn(true);
                                    }
                                    breakOut = true;
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    } else if(computerOne.isTurn()){
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setLead(true);
                            computerOne.setTurn(false);
                            if(!computerThreeFinished) {
                                computerThree.setTurn(true);

                            } else if(!playerFinished){
                                player.setTurn(true);

                            }
                            break;

                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setTurn(false);
                            if(!computerThreeFinished) {
                                computerThree.setLead(true);
                                computerThree.setTurn(true);
                                computerThree.setGoAnything(true);
                            } else if(!playerFinished){
                                player.setLead(true);
                                player.setTurn(true);
                                player.setGoAnything(true);
                            }

                            break;

                        }

                    }
                }
            } else if(computerThreeFinished && computerThreeLastHandPlayed) {
                boolean breakOut = false;
                while (true){
                    if(breakOut){
                        break;
                    }
                    if (player.isTurn()) {
                        while (true) {
                            player.setPlayingHand(new PlayingHand());
                            player.promptPlayHandOverloadedTwo();
                            if (player.isPass()) {
                                player.setPass(false);
                                player.setTurn(false);
                                if(!computerOneFinished){
                                    computerOne.setTurn(true);
                                } else if(!computerTwoFinished){
                                    computerTwo.setTurn(true);
                                }
                                breakOut = true;
                                break;
                            }
                            boolean legalPlay = player.getPlayingHand().checkLegalPlays(lastHandPlayed);
                            if (legalPlay) {
                                if (player.getPlayingHand().checkWhichHandIsStronger(lastHandPlayed)){
                                    lastHandPlayed = player.getPlayingHand();
                                    player.setHand(player.getTempHand());
                                    player.setLead(true);
                                    player.setTurn(false);
                                    if(!computerOneFinished){
                                        computerOne.setTurn(true);
                                    } else if(!computerTwoFinished){
                                        computerTwo.setTurn(true);
                                    }
                                    breakOut = true;
                                    break;
                                } else {
                                    System.out.println("The hand you played cannot beat the current hand. Please try again.");
                                }
                            } else {
                                System.out.println("That is not a valid play. Please try again.");
                            }
                        }
                    } else if(computerOne.isTurn()){
                        System.out.println("Computer One's turn.");
                        ArrayList<Cards> bestCards = computerOne.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerOne.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerOne.getHand().removeCard(bestCards.get(i));
                            }
                            computerOne.getHand().sortHand();
                            lastHandPlayed = computerOne.getPlayingHand();
                            computerOne.setLead(true);
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);

                            }
                            break;

                        } else {
                            System.out.println("Computer One passes.");
                            computerOne.setTurn(false);
                            if(!computerTwoFinished) {
                                computerTwo.setTurn(true);
                            } else if(!playerFinished){
                                player.setTurn(true);
                                player.setLead(true);
                                player.setGoAnything(true);
                            }
                            break;

                        }

                    } else if(computerTwo.isTurn()) {
                        System.out.println("Computer Two's turn.");
                        ArrayList<Cards> bestCards = computerTwo.playBestCards(lastHandPlayed);
                        if (bestCards != null) {
                            computerTwo.setPlayingHand(new PlayingHand());
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getPlayingHand().addCard(bestCards.get(i));
                            }
                            for (int i = 0; i < bestCards.size(); i++) {
                                computerTwo.getHand().removeCard(bestCards.get(i));
                            }
                            computerTwo.getHand().sortHand();
                            lastHandPlayed = computerTwo.getPlayingHand();
                            computerTwo.setLead(true);
                            computerTwo.setTurn(false);
                            if(!playerFinished) {
                                player.setTurn(true);
                            } else if(!computerOneFinished){
                                computerOne.setTurn(true);
                            }
                            break;

                        } else {
                            System.out.println("Computer Two passes.");
                            computerTwo.setTurn(false);
                            if(!playerFinished) {
                                player.setLead(true);
                                player.setTurn(true);
                                player.setGoAnything(true);
                            } else if (!computerOneFinished){
                                computerOne.setLead(true);
                                computerOne.setTurn(true);
                                computerOne.setGoAnything(true);
                            }
                            break;
                        }
                    }
                }
            } else{

                //decide who gets to take the lead
                if(lastHandPlayed == player.getPlayingHand() && !playerFinished){
                    player.setLead(true);
                } else if(lastHandPlayed == computerOne.getPlayingHand() && !computerOneFinished){
                    computerOne.setLead(true);
                } else if(lastHandPlayed == computerTwo.getPlayingHand() && !computerTwoFinished){
                    computerTwo.setLead(true);
                } else if(lastHandPlayed == computerThree.getPlayingHand() && !computerThreeFinished){
                    computerThree.setLead(true);
                }
            }

            //check if anyone is leading
            if(!player.isLead() && !computerOne.isLead() && !computerTwo.isLead() && !computerThree.isLead()){
                nobodyLeads = true;

            } else {
                nobodyLeads = false;
            }



            //output the last hand that was played
            if(lastHandPlayed == player.getPlayingHand()){
                computerOneLastHandPlayed = false;
                computerTwoLastHandPlayed = false;
                computerThreeLastHandPlayed = false;
                if(!playerLastHandPlayed) {
                    pressEnterToContinue();
                    playerLastHandPlayed = true;
                    System.out.println("Your cards: ");
                    player.getHand().showHand();
                    System.out.println("You played: ");
                    lastHandPlayed.showHand();
                    cardsPlayed += lastHandPlayed.getMyHand().size();
                    System.out.println("Cards played: " + cardsPlayed);
                }


            } else if(lastHandPlayed == computerOne.getPlayingHand()){
                playerLastHandPlayed = false;
                computerTwoLastHandPlayed = false;
                computerThreeLastHandPlayed = false;
                if(!computerOneLastHandPlayed) {
                    pressEnterToContinue();
                    computerOneLastHandPlayed = true;
                    System.out.println("Computer One played: ");
                    lastHandPlayed.showHand();
                    cardsPlayed += lastHandPlayed.getMyHand().size();
                    System.out.println("Cards played: " + cardsPlayed);

                }

            } else if(lastHandPlayed == computerTwo.getPlayingHand()){
                playerLastHandPlayed = false;
                computerOneLastHandPlayed = false;
                computerThreeLastHandPlayed = false;
                if(!computerTwoLastHandPlayed) {
                    pressEnterToContinue();
                    computerTwoLastHandPlayed = true;
                    System.out.println("Computer Two played: ");
                    lastHandPlayed.showHand();
                    cardsPlayed += lastHandPlayed.getMyHand().size();
                    System.out.println("Cards played: " + cardsPlayed);

                }

            } else if(lastHandPlayed == computerThree.getPlayingHand()){
                playerLastHandPlayed = false;
                computerOneLastHandPlayed = false;
                computerTwoLastHandPlayed = false;
                if(!computerThreeLastHandPlayed) {
                    pressEnterToContinue();
                    computerThreeLastHandPlayed = true;
                    System.out.println("Computer Three played: ");
                    lastHandPlayed.showHand();
                    cardsPlayed += lastHandPlayed.getMyHand().size();
                    System.out.println("Cards played: " + cardsPlayed);
                }

            }

        }
    }
    public static boolean isAllComputerPassed(){
        return allComputerPassed;
    }
    private static void pressEnterToContinue() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press space and enter to continue...");
            String input = scanner.nextLine();
            if(input.equals(" ")){
                break;
            }

        }
    }
}
