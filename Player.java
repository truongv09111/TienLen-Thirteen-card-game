import java.util.Scanner;

public class Player {
    //fields
    private Hand hand;
    private PlayingHand playingHand;
    private Hand tempHand;
    private boolean lead;
    private boolean turn;
    private boolean pass;
    private boolean goAnything;


    //constructor method
    public Player(){
        hand = new Hand();
        lead = false;
        turn = false;
        pass = false;
        goAnything = false;
        playingHand = new PlayingHand();
        tempHand = new Hand();

    }

    //getter methods
    public Hand getHand() {
        return hand;
    }

    public boolean isTurn() {
        return turn;
    }

    public boolean isLead() {
        return lead;
    }

    public boolean isGoAnything() {
        return goAnything;
    }

    public PlayingHand getPlayingHand() {
        return playingHand;
    }

    public Hand getTempHand() {
        return tempHand;
    }

    public boolean isPass() {
        return pass;
    }

    //setter methods
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setLead(boolean lead) {
        this.lead = lead;
    }

    public void setPlayingHand(PlayingHand playingHand) {
        this.playingHand = playingHand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setTempHand(Hand tempHand) {
        this.tempHand = tempHand;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public void setGoAnything(boolean goAnything) {
        this.goAnything = goAnything;
    }

    //instance methods
    public void promptPlayHandOverloadedTwo() {
        Scanner scanner = new Scanner(System.in);
        tempHand = new Hand();
        for(int i=0; i<hand.getMyHand().size(); i++){
            tempHand.getMyHand().add(hand.getMyHand().get(i));
        }

        while (true){
            System.out.println("Which cards do you want to play? (choose a number from your hand)");
            tempHand.showHand();
            System.out.println("press f and then enter to finish your selection");
            System.out.println("press p and then enter to pass");

            if(playingHand.getMyHand().size() !=0 ){
                System.out.println("Your playing hand: ");
                getPlayingHand().sortHand();
                getPlayingHand().showHand();
            }

            boolean hasInt = scanner.hasNextInt();
            if (hasInt) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice){
                    case 1:

                        if(tempHand.getMyHand().size() >= 1) {
                            playedHand(tempHand.getMyHand().get(0));
                            tempHand.getMyHand().remove(0);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }



                    case 2:
                        if(tempHand.getMyHand().size() >= 2) {
                            playedHand(tempHand.getMyHand().get(1));
                            tempHand.getMyHand().remove(1);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 3:
                        if(tempHand.getMyHand().size() >= 3) {
                            playedHand(tempHand.getMyHand().get(2));
                            tempHand.getMyHand().remove(2);
                            break;
                        }else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 4:
                        if(tempHand.getMyHand().size() >= 4) {
                            playedHand(tempHand.getMyHand().get(3));
                            tempHand.getMyHand().remove(3);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 5:
                        if(tempHand.getMyHand().size() >= 5) {
                            playedHand(tempHand.getMyHand().get(4));
                            tempHand.getMyHand().remove(4);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 6:
                        if(tempHand.getMyHand().size() >= 6) {
                            playedHand(tempHand.getMyHand().get(5));
                            tempHand.getMyHand().remove(5);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 7:
                        if(tempHand.getMyHand().size() >= 7) {
                            playedHand(tempHand.getMyHand().get(6));
                            tempHand.getMyHand().remove(6);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 8:
                        if(tempHand.getMyHand().size() >= 8) {
                            playedHand(tempHand.getMyHand().get(7));
                            tempHand.getMyHand().remove(7);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 9:
                        if(tempHand.getMyHand().size() >= 9) {
                            playedHand(tempHand.getMyHand().get(8));
                            tempHand.getMyHand().remove(8);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 10:
                        if(tempHand.getMyHand().size() >= 10) {
                            playedHand(tempHand.getMyHand().get(9));
                            tempHand.getMyHand().remove(9);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 11:
                        if(tempHand.getMyHand().size() >= 11) {
                            playedHand(tempHand.getMyHand().get(10));
                            tempHand.getMyHand().remove(10);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 12:
                        if(tempHand.getMyHand().size() >= 12) {
                            playedHand(tempHand.getMyHand().get(11));
                            tempHand.getMyHand().remove(11);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    case 13:
                        if(tempHand.getMyHand().size() == 13) {
                            playedHand(tempHand.getMyHand().get(12));
                            tempHand.getMyHand().remove(12);
                            break;
                        } else {
                            System.out.println("Your hand does not contain this card.");
                            break;
                        }
                    default:
                        System.out.println("Invalid input. Please choose from 1-13");

                }

            } else {
                String input = scanner.nextLine();
                if(input.equals("f")){
                    if(playingHand.getMyHand().size() == 0){
                        System.out.println("You did not play any cards.");
                    } else{
                        playingHand.sortHand();
                        break;

                    }


                } else if(input.equals("p")){
                    if(playingHand.getMyHand().size() == 0 && !goAnything) {
                        pass = true;
                        break;
                    } else {
                        System.out.println("You cannot pass because you either have already played some cards or it is your lead.");
                    }
                } else{
                    System.out.println("Please choose a number from your hand.");
                }




            }
        }

    }

    public void playedHand(Cards playedCard){
        if(!playingHand.getMyHand().contains(playedCard)){
            playingHand.addCard(playedCard);
            System.out.println(playedCard.getName() + " added to playing hand.");
        } else{
            System.out.println(playedCard.getName() + " has already been added to playing hand.");
        }
        

    }


}
