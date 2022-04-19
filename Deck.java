import java.util.ArrayList;

public class Deck {
    //instance fields
    private ArrayList<Cards> deck;
    private int cardsLeft;

    private Cards threeOfSpades = new Cards("3 of spades", 1);
    private Cards threeOfCloves = new Cards("3 of cloves", 2);
    private Cards threeOfDiamonds = new Cards("3 of diamonds", 3);
    private Cards threeOfHearts = new Cards("3 of hearts", 4);

    private  Cards fourOfSpades = new Cards("4 of spades", 5);
    private  Cards fourOfCloves = new Cards("4 of cloves", 6);
    private  Cards fourOfDiamonds = new Cards("4 of diamonds", 7);
    private  Cards fourOfHearts = new Cards("4 of hearts", 8);

    private  Cards fiveOfSpades = new Cards("5 of spades", 9);
    private  Cards fiveOfCloves = new Cards("5 of cloves", 10);
    private  Cards fiveOfDiamonds = new Cards("5 of diamonds", 11);
    private  Cards fiveOfHearts = new Cards("5 of hearts", 12);

    private  Cards sixOfSpades = new Cards("6 of spades", 13);
    private  Cards sixOfCloves = new Cards("6 of cloves", 14);
    private  Cards sixOfDiamonds = new Cards("6 of diamonds", 15);
    private  Cards sixOfHearts = new Cards("6 of hearts", 16);

    private  Cards sevenOfSpades = new Cards("7 of spades", 17);
    private  Cards sevenOfCloves = new Cards("7 of cloves", 18);
    private  Cards sevenOfDiamonds = new Cards("7 of diamonds", 19);
    private  Cards sevenOfHearts = new Cards("7 of hearts", 20);

    private  Cards eightOfSpades = new Cards("8 of spades", 21);
    private  Cards eightOfCloves = new Cards("8 of cloves", 22);
    private  Cards eightOfDiamonds = new Cards("8 of diamonds", 23);
    private  Cards eightOfHearts = new Cards("8 of hearts", 24);

    private  Cards nineOfSpades = new Cards("9 of spades", 25);
    private  Cards nineOfCloves = new Cards("9 of cloves", 26);
    private  Cards nineOfDiamonds = new Cards("9 of diamonds", 27);
    private  Cards nineOfHearts = new Cards("9 of hearts", 28);

    private  Cards tenOfSpades = new Cards("10 of spades", 29);
    private  Cards tenOfCloves = new Cards("10 of cloves", 30);
    private  Cards tenOfDiamonds = new Cards("10 of diamonds", 31);
    private  Cards tenOfHearts = new Cards("10 of hearts", 32);

    private  Cards jackOfSpades = new Cards("Jack of spades", 33);
    private  Cards jackOfCloves = new Cards("Jack of cloves", 34);
    private  Cards jackOfDiamonds = new Cards("Jack of diamonds", 35);
    private  Cards jackOfHearts = new Cards("Jack of hearts", 36);

    private  Cards queenOfSpades = new Cards("Queen of spades", 37);
    private  Cards queenOfCloves = new Cards("Queen of cloves", 38);
    private  Cards queenOfDiamonds = new Cards("Queen of diamonds", 39);
    private  Cards queenOfHearts = new Cards("Queen of hearts", 40);

    private  Cards kingOfSpades = new Cards("King of spades", 41);
    private  Cards kingOfCloves = new Cards("King of cloves", 42);
    private  Cards kingOfDiamonds = new Cards("King of diamonds", 43);
    private  Cards kingOfHearts = new Cards("King of hearts", 44);

    private Cards aceOfSpades = new Cards("Ace of spades",45);
    private Cards aceOfCloves = new Cards("Ace of cloves", 46);
    private  Cards aceOfDiamonds = new Cards("Ace of diamonds", 47);
    private  Cards aceOfHearts = new Cards("Ace of hearts", 48);

    private  Cards twoOfSpades = new Cards("2 of spades",49);
    private  Cards twoOfCloves = new Cards("2 of cloves", 50);
    private  Cards twoOfDiamonds = new Cards("2 of diamonds", 51);
    private  Cards twoOfHearts = new Cards("2 of hearts", 52);



    //constructor method
    public Deck() {
        cardsLeft = 52;
        shuffle();
    }
    
    //getter methods
    public int getCardsLeft() {
        return cardsLeft;
    }
    public ArrayList<Cards> getDeck() {
        return deck;
    }

    //setter methods
    public void setCardsLeft(int cardsLeft) {
        this.cardsLeft -= cardsLeft;
    }

    
    //instance methods
    public void shuffle(){
        deck = new ArrayList<Cards>(); //point to a new ArrayList
        ArrayList<Integer> doNotChooseTheseNumbers = new ArrayList<Integer>(); //this ArrayList of Integers makes sure that the same card isn't added more than once to the deck

        int max = 52;
        int min = 1;

        while(deck.size() != 52) {

            int randomNumber = (int) (Math.random() * max) + min;
            if(!doNotChooseTheseNumbers.contains(randomNumber)) {

                switch (randomNumber) {
                    case 1:
                        deck.add(threeOfSpades);
                        break;
                    case 2:
                        deck.add(threeOfCloves);
                        break;
                    case 3:
                      deck.add(threeOfDiamonds);
                        break;
                    case 4:
                        deck.add(threeOfHearts);
                        break;
                    case 5:
                        deck.add(fourOfSpades);
                        break;
                    case 6:
                        deck.add(fourOfCloves);
                        break;
                    case 7:
                       deck.add(fourOfDiamonds);
                        break;
                    case 8:
                        deck.add(fourOfHearts);
                        break;
                    case 9:
                        deck.add(fiveOfSpades);
                        break;
                    case 10:
                        deck.add(fiveOfCloves);
                        break;
                    case 11:
                        deck.add(fiveOfDiamonds);
                        break;
                    case 12:
                        deck.add(fiveOfHearts);
                        break;
                    case 13:
                       deck.add(sixOfSpades);
                        break;
                    case 14:
                        deck.add(sixOfCloves);
                        break;
                    case 15:
                        deck.add(sixOfDiamonds);
                        break;
                    case 16:
                        deck.add(sixOfHearts);
                        break;
                    case 17:
                       deck.add(sevenOfSpades);
                        break;
                    case 18:
                        deck.add(sevenOfCloves);
                        break;
                    case 19:
                        deck.add(sevenOfDiamonds);
                        break;
                    case 20:
                        deck.add(sevenOfHearts);
                        break;
                    case 21:
                        deck.add(eightOfSpades);
                        break;
                    case 22:
                        deck.add(eightOfCloves);
                        break;
                    case 23:
                        deck.add(eightOfDiamonds);
                        break;
                    case 24:
                        deck.add(eightOfHearts);
                        break;
                    case 25:
                        deck.add(nineOfSpades);
                        break;
                    case 26:
                        deck.add(nineOfCloves);
                        break;
                    case 27:
                        deck.add(nineOfDiamonds);
                        break;
                    case 28:
                        deck.add(nineOfHearts);
                        break;
                    case 29:
                        deck.add(tenOfSpades);
                        break;
                    case 30:
                        deck.add(tenOfCloves);
                        break;
                    case 31:
                        deck.add(tenOfDiamonds);
                        break;
                    case 32:
                        deck.add(tenOfHearts);
                        break;
                    case 33:
                        deck.add(jackOfSpades);
                        break;
                    case 34:
                        deck.add(jackOfCloves);
                        break;
                    case 35:
                        deck.add(jackOfDiamonds);
                        break;
                    case 36:
                        deck.add(jackOfHearts);
                        break;
                    case 37:
                        deck.add(queenOfSpades);
                        break;
                    case 38:
                        deck.add(queenOfCloves);
                        break;
                    case 39:
                        deck.add(queenOfDiamonds);
                        break;
                    case 40:
                        deck.add(queenOfHearts);
                        break;
                    case 41:
                        deck.add(kingOfSpades);
                        break;
                    case 42:
                        deck.add(kingOfCloves);
                        break;
                    case 43:
                        deck.add(kingOfDiamonds);
                        break;
                    case 44:
                        deck.add(kingOfHearts);
                        break;
                    case 45:
                        deck.add(aceOfSpades);
                        break;
                    case 46:
                        deck.add(aceOfCloves);
                        break;
                    case 47:
                        deck.add(aceOfDiamonds);
                        break;
                    case 48:
                        deck.add(aceOfHearts);
                        break;
                    case 49:
                        deck.add(twoOfSpades);
                        break;
                    case 50:
                        deck.add(twoOfCloves);
                        break;
                    case 51:
                        deck.add(twoOfDiamonds);
                        break;
                    case 52:
                        deck.add(twoOfHearts);
                        break;
                }
                doNotChooseTheseNumbers.add(randomNumber);
            }
        }
    }
    public void printCards(){
        for(int i=0; i<52; i++){
            System.out.println("Card: " + deck.get(i).getName());
        }
    }
    public void removeCard(){
        if(deck.size() != 0){
            deck.remove(0);
        }
    }
    public void sortDeck(){
        for(int i=0; i<52; i++){
            for(int x=i+1; x<52; x++){
                if(deck.get(i).getValue() > deck.get(x).getValue()){
                    Cards tempCard = deck.get(i);
                    deck.set(i, deck.get(x));
                    deck.set(x, tempCard);
                }
            }

        }
    }




    
}
