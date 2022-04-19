import java.util.ArrayList;

public class Hand {
    //fields
    private ArrayList<Cards> myHand;

    //constructor method
    public Hand() {
        myHand = new ArrayList<Cards>();
    }

    //getter methods
    public ArrayList<Cards> getMyHand() {
        return myHand;
    }



    //instance methods
    public void addCard(Cards card) {
        if (!myHand.contains(card) && myHand.size() < 13) {
            myHand.add(card);
        }
    }
    public void removeCard(Cards card){
        int position = findCard(card.getName());
        myHand.remove(position);

    }
    public void showHand(){
        for(int i=0; i< myHand.size(); i++){
            System.out.println(i+1 + ". " +myHand.get(i).getName());
        }
    }
    public int findCard(String cardName){
        for(int i=0; i< myHand.size(); i++){
            if(myHand.get(i).getName().equals(cardName)){
                return myHand.indexOf(myHand.get(i));
            }
        }
        return -1;
    }

    public void sortHand(){
        for(int i=0; i<myHand.size(); i++){
            for(int x=i+1; x<myHand.size(); x++){
                if(myHand.get(i).getValue() > myHand.get(x).getValue()){
                    Cards tempCard = myHand.get(i);
                    myHand.set(i, myHand.get(x));
                    myHand.set(x, tempCard);
                }
            }

        }
    }



}
