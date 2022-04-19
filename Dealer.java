import java.util.ArrayList;

public class Dealer {
    //constructor method
    public Dealer() {
    }

    //instance methods
    public void deal(Player player, Deck deck){
        while(player.getHand().getMyHand().size() < 13) {
            player.getHand().addCard(deck.getDeck().get(0));
            deck.removeCard();
        }


    }
    public boolean checkforThreeOfSpades(Player player){
        Cards threeOfSpades = new Cards("3 of spades", 1);
        int positionOfCard = player.getHand().findCard(threeOfSpades.getName());
        if(positionOfCard >=0){
            return true;
        } else {
            return false;
        }
    }
    public Player checkWhoseTurn(Player player, Computer computerOne, Computer computerTwo){
        if (player.isTurn()){
            return player;
        } else if(computerOne.isTurn()){
            return computerOne;
        } else if(computerTwo.isTurn()){
            return computerTwo;
        } else {
            return null;
        }
    }
    public Player findLowestCardInHand(Player player, Computer computerOne){
        int playerLowestCardValue = player.getHand().getMyHand().get(0).getValue();
        int computerOneLowestCardValue = computerOne.getHand().getMyHand().get(0).getValue();

        if(playerLowestCardValue < computerOneLowestCardValue){
            return player;
        } else {
            return computerOne;
        }
    }
    public Player findLowestCardInHand(Player player, Computer computerOne, Computer computerTwo){
        int playerLowestCardValue = player.getHand().getMyHand().get(0).getValue();
        int computerOneLowestCardValue = computerOne.getHand().getMyHand().get(0).getValue();
        int computerTwoLowestCardValue = computerTwo.getHand().getMyHand().get(0).getValue();
        ArrayList<Integer> listOfLowestCardValue = new ArrayList<Integer>();
        listOfLowestCardValue.add(playerLowestCardValue);
        listOfLowestCardValue.add(computerOneLowestCardValue);
        listOfLowestCardValue.add(computerTwoLowestCardValue);

        for(int i=0; i<listOfLowestCardValue.size()-1; i++){
            for(int a=i+1; a<listOfLowestCardValue.size(); a++){
                if(listOfLowestCardValue.get(i) > listOfLowestCardValue.get(a)){
                    int tempNumber = listOfLowestCardValue.get(i);
                    listOfLowestCardValue.set(i, listOfLowestCardValue.get(a));
                    listOfLowestCardValue.set(a, tempNumber);
                }
            }
        }
        if(playerLowestCardValue == listOfLowestCardValue.get(0)){
            return player;
        } else if(computerOneLowestCardValue == listOfLowestCardValue.get(0)){
            return computerOne;
        } else if(computerTwoLowestCardValue == listOfLowestCardValue.get(0)){
            return computerTwo;
        } else{
            return null;
        }
    }
    public Player findLowestCardInHand(Player player, Computer computerOne, Computer computerTwo, Computer computerThree){
        int playerLowestCardValue = player.getHand().getMyHand().get(0).getValue();
        int computerOneLowestCardValue = computerOne.getHand().getMyHand().get(0).getValue();
        int computerTwoLowestCardValue = computerTwo.getHand().getMyHand().get(0).getValue();
        int computerThreeLowestCardValue = computerThree.getHand().getMyHand().get(0).getValue();
        ArrayList<Integer> listOfLowestCardValue = new ArrayList<Integer>();
        listOfLowestCardValue.add(playerLowestCardValue);
        listOfLowestCardValue.add(computerOneLowestCardValue);
        listOfLowestCardValue.add(computerTwoLowestCardValue);
        listOfLowestCardValue.add(computerThreeLowestCardValue);

        for(int i=0; i<listOfLowestCardValue.size()-1; i++){
            for(int a=i+1; a<listOfLowestCardValue.size(); a++){
                if(listOfLowestCardValue.get(i) > listOfLowestCardValue.get(a)){
                    int tempNumber = listOfLowestCardValue.get(i);
                    listOfLowestCardValue.set(i, listOfLowestCardValue.get(a));
                    listOfLowestCardValue.set(a, tempNumber);
                }
            }
        }
        if(playerLowestCardValue == listOfLowestCardValue.get(0)){
            return player;
        } else if(computerOneLowestCardValue == listOfLowestCardValue.get(0)){
            return computerOne;
        } else if(computerTwoLowestCardValue == listOfLowestCardValue.get(0)){
            return computerTwo;
        } else{
            return null;
        }
    }
}
