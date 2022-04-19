import java.util.ArrayList;

public class PlayingHand extends Hand{
    //instance fields
    private boolean singleCard;
    private boolean pair;
    private boolean triple;
    private boolean fourOfKind;
    private boolean sequence;
    private boolean doubleSequence;


    private boolean invalidPlay;

    //constructor method
    public PlayingHand(){
        super();
        singleCard = false;
        pair = false;
        triple = false;
        fourOfKind = false;
        sequence = false;
        doubleSequence = false;
        invalidPlay = false;



    }

    //getter methods
    public boolean isSingleCard() {
        return singleCard;
    }

    public boolean isPair() {
        return pair;
    }

    public boolean isTriple() {
        return triple;
    }

    public boolean isFourOfKind() {
        return fourOfKind;
    }

    public boolean isSequence() {
        return sequence;
    }

    public boolean isDoubleSequence() {
        return doubleSequence;
    }

    public boolean isInvalidPlay() {
        return invalidPlay;
    }

    //setter methods
    public void setSingleCard(boolean singleCard) {
        this.singleCard = singleCard;
    }

    public void setPair(boolean pair) {
        this.pair = pair;
    }

    public void setTriple(boolean triple) {
        this.triple = triple;
    }

    public void setFourOfKind(boolean fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    public void setSequence(boolean sequence) {
        this.sequence = sequence;
    }

    public void setDoubleSequence(boolean doubleSequence) {
        this.doubleSequence = doubleSequence;
    }

    public void setInvalidPlay(boolean invalidPlay) {
        this.invalidPlay = invalidPlay;
    }

    //instance methods
    public void checkTypeOfPlay(){
        singleCard = false;
        pair = false;
        triple = false;
        fourOfKind = false;
        sequence = false;
        doubleSequence = false;

        if(getMyHand().size() == 1){
            singleCard = true;


        } else if(getMyHand().size() == 2 && getMyHand().get(0).getName().charAt(0) == getMyHand().get(1).getName().charAt(0)){
            pair = true;



        } else if(getMyHand().size() == 3 && getMyHand().get(0).getName().charAt(0) == getMyHand().get(1).getName().charAt(0) && getMyHand().get(1).getName().charAt(0) == getMyHand().get(2).getName().charAt(0)){
            triple = true;


        } else if(getMyHand().size() == 4 && getMyHand().get(0).getName().charAt(0) == getMyHand().get(1).getName().charAt(0) && getMyHand().get(1).getName().charAt(0) == getMyHand().get(2).getName().charAt(0) && getMyHand().get(2).getName().charAt(0) == getMyHand().get(3).getName().charAt(0)){
            fourOfKind = true;


        } else{
            //check for sequence
            ArrayList<Integer> sequenceList = new ArrayList<Integer>();
            int numDigit = 0;
            for(int i=0; i< getMyHand().size(); i++){
                char digit = getMyHand().get(i).getName().charAt(0);
                String digitString = String.valueOf(digit);

                if(digitString.equals("J")){
                    numDigit = 11;
                    sequenceList.add(numDigit);
                } else if(digitString.equals("Q")){
                    numDigit = 12;
                    sequenceList.add(numDigit);
                } else if(digitString.equals("K")){
                    numDigit = 13;
                    sequenceList.add(numDigit);
                } else if(digitString.equals("A")){
                    numDigit = 14;
                    sequenceList.add(numDigit);
                } else if(digitString.equals("2")){
                    numDigit = 16;
                    sequenceList.add(numDigit);
                } else if(digitString.equals("1")){
                    numDigit = 10;
                    sequenceList.add(numDigit);
                } else{
                    numDigit = Integer.parseInt(digitString);
                    sequenceList.add(numDigit);
                }

            }


            for(int i=0; i<sequenceList.size()-1; i++){

                if(sequenceList.size() > 2 && sequenceList.get(i) + 1 == sequenceList.get(i + 1)){
                    sequence = true;
                } else{
                    sequence = false;
                    break;
                }
            }

            //check for doubleSequence
            sequenceList = new ArrayList<Integer>();
                for(int i=0; i < getMyHand().size(); i++) {
                    char digit = getMyHand().get(i).getName().charAt(0);
                    String digitString = String.valueOf(digit);

                    if (digitString.equals("J")) {
                        numDigit = 11;
                        sequenceList.add(numDigit);
                    } else if (digitString.equals("Q")) {
                        numDigit = 12;
                        sequenceList.add(numDigit);
                    } else if (digitString.equals("K")) {
                        numDigit = 13;
                        sequenceList.add(numDigit);
                    } else if (digitString.equals("A")) {
                        numDigit = 14;
                        sequenceList.add(numDigit);
                    } else if (digitString.equals("2")) {
                        numDigit = 16;
                        sequenceList.add(numDigit);
                    } else if (digitString.equals("1")) {
                        numDigit = 10;
                        sequenceList.add(numDigit);
                    } else {
                        numDigit = Integer.parseInt(digitString);
                        sequenceList.add(numDigit);
                    }
                }

                for(int i=0; i< sequenceList.size() - 2; i++){
                    int listSize = sequenceList.size();
                    boolean even = false;
                    if(listSize % 2 == 0){
                        even = true;
                    }
                    if(sequenceList.size() > 5 && even == true){
                        if(sequenceList.get(i) + 1 == sequenceList.get(i + 2)){
                            doubleSequence = true;
                        } else {
                            doubleSequence = false;
                            break;
                        }
                    } else {
                        doubleSequence = false;
                        break;
                    }
                }
        }
    }
    public boolean checkLegalPlays(PlayingHand lastHandPlayed){
        checkTypeOfPlay();
        lastHandPlayed.checkTypeOfPlay();
        if(this.singleCard && lastHandPlayed.singleCard){
            return true;
        } else if(this.pair && lastHandPlayed.pair){
            return true;
        } else if (this.triple && lastHandPlayed.triple){
            return true;
        } else if(this.fourOfKind && lastHandPlayed.fourOfKind){
            return true;
        } else if(this.fourOfKind && lastHandPlayed.singleCard && lastHandPlayed.getMyHand().get(0).getValue() == 49 || lastHandPlayed.getMyHand().get(0).getValue() == 50 || lastHandPlayed.getMyHand().get(0).getValue() == 51 || lastHandPlayed.getMyHand().get(0).getValue() == 52){
            return true;
        } else if(this.sequence && lastHandPlayed.sequence && getMyHand().size() == lastHandPlayed.getMyHand().size()){
            return true;
        } else if (this.doubleSequence && lastHandPlayed.doubleSequence && getMyHand().size() == lastHandPlayed.getMyHand().size()){
            return true;
        } else if (this.doubleSequence && lastHandPlayed.singleCard && lastHandPlayed.getMyHand().get(0).getValue() == 49 || lastHandPlayed.getMyHand().get(0).getValue() == 50 || lastHandPlayed.getMyHand().get(0).getValue() == 51 || lastHandPlayed.getMyHand().get(0).getValue() == 52) {
            return true;
        } else if(this.invalidPlay){
            return false;
        } else {
            return false;
        }


    }
    public boolean checkLegalPlay(){
        checkTypeOfPlay();
        if(singleCard){
            return true;
        } else if(pair){
            return true;
        } else if(triple){
            return true;
        } else if (fourOfKind){
            return true;
        } else if (sequence){
            return true;
        } else if (doubleSequence){
            return true;
        } else {
            return false;
        }
    }
    public boolean checkLegalFirstPlay(Player player){
        checkTypeOfPlay();
        if(this.singleCard){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else {
                return false;
            }
        } else if(this.pair){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else{
                return false;
            }
        } else if(this.triple){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else {
                return false;
            }
        } else if(this.fourOfKind){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else {
                return false;
            }
        } else if(this.sequence){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else {
                return false;
            }
        } else if(this.doubleSequence){
            if(getMyHand().get(0) == player.getHand().getMyHand().get(0)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }


    }
    public boolean checkWhichHandIsStronger(PlayingHand lastHandPlayed){ //only call this method after checkLegalPlay() returns true
        if(this.singleCard){
            int playerSingleCardValue = getMyHand().get(0).getValue();
            int computerOneSingleCardValue = lastHandPlayed.getMyHand().get(0).getValue();
            if(playerSingleCardValue > computerOneSingleCardValue){
                return true;
            } else {
                return false;
            }
        } else if(this.pair){
            int playerHighestCardValue;
            int computerOneHighestCardValue;

            playerHighestCardValue = getMyHand().get(1).getValue();
            computerOneHighestCardValue = lastHandPlayed.getMyHand().get(1).getValue();

            if(playerHighestCardValue > computerOneHighestCardValue){
                return true;
            } else{
                return false;
            }

        } else if(this.triple){
            int playerHighestCardValue;
            int computerOneHighestCardValue;

            playerHighestCardValue = getMyHand().get(2).getValue();
            computerOneHighestCardValue = lastHandPlayed.getMyHand().get(2).getValue();

            if(playerHighestCardValue > computerOneHighestCardValue){
                return true;
            } else{
                return false;
            }
        } else if(this.sequence){

            int playerLastCardValue;
            int computerOneLastCardValue;

            playerLastCardValue = getMyHand().get(getMyHand().size() - 1).getValue();
            computerOneLastCardValue = lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size() - 1).getValue();

            if(playerLastCardValue > computerOneLastCardValue){
                return true;
            } else{
                return false;
            }
        } else if(this.doubleSequence){
            lastHandPlayed.checkTypeOfPlay();
            if(lastHandPlayed.singleCard && lastHandPlayed.getMyHand().get(0).getValue() == 49 || lastHandPlayed.getMyHand().get(0).getValue() == 50 || lastHandPlayed.getMyHand().get(0).getValue() == 51 || lastHandPlayed.getMyHand().get(0).getValue() == 52){
                return true;
            }
            int playerLastCardValue;
            int computerOneLastCardValue;

            playerLastCardValue = getMyHand().get(getMyHand().size() - 1).getValue();
            computerOneLastCardValue = lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size() - 1).getValue();

            if(playerLastCardValue > computerOneLastCardValue){
                return true;
            } else{
                return false;
            }
        } else if(this.fourOfKind){
            lastHandPlayed.checkTypeOfPlay();
            if(lastHandPlayed.singleCard && lastHandPlayed.getMyHand().get(0).getValue() == 49 || lastHandPlayed.getMyHand().get(0).getValue() == 50 || lastHandPlayed.getMyHand().get(0).getValue() == 51 || lastHandPlayed.getMyHand().get(0).getValue() == 52){
                return true;
            }
            if(getMyHand().get(0).getValue() > lastHandPlayed.getMyHand().get(0).getValue()){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }


}
