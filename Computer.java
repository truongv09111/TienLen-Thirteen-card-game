import java.util.ArrayList;

public class Computer extends Player{


    //constructor method
    public Computer() {
        super();
    }
    //instance methods
    public void playFirstHand() {
        boolean playSingleCard = false;
        boolean playPair = false;
        boolean playTriple = false;
        boolean playFourOfAKind = false;
        boolean playSequence = false;
        boolean playDoubleSequence = false;
        //check to see what can be played
        if(isSingleCardPartOfSequence(getHand().getMyHand().get(0)) && !isSingleCardPartOfDoubleSequence(getHand().getMyHand().get(0))){
            playSequence = true;
        } else if (getHand().getMyHand().get(0).getName().charAt(0) == getHand().getMyHand().get(1).getName().charAt(0) && getHand().getMyHand().get(1).getName().charAt(0) == getHand().getMyHand().get(2).getName().charAt(0)) {
            playTriple = true;
        } else if (getHand().getMyHand().get(0).getName().charAt(0) == getHand().getMyHand().get(1).getName().charAt(0) && !isSingleCardPartOfDoubleSequence(getHand().getMyHand().get(0))) { //check for pair
            playPair = true;

        } else if (getHand().getMyHand().get(0).getName().charAt(0) == getHand().getMyHand().get(1).getName().charAt(0) && getHand().getMyHand().get(1).getName().charAt(0) == getHand().getMyHand().get(2).getName().charAt(0) && getHand().getMyHand().get(2).getName().charAt(0) == getHand().getMyHand().get(3).getName().charAt(0)) {
            playFourOfAKind = true;
        } else if(isSingleCardPartOfDoubleSequence(getHand().getMyHand().get(0))){
            playDoubleSequence = true;
        }

            if (playSequence) {
                ArrayList<Integer> sequenceList = hasSequence();
                ArrayList<Integer> finalSequenceList = new ArrayList<Integer>();
                for(int i=0; i<sequenceList.size(); i++){
                    if(i != sequenceList.size() - 1) {
                        if (sequenceList.get(i) + 1 == sequenceList.get(i + 1)) {
                            finalSequenceList.add(sequenceList.get(i));
                        } else {
                            finalSequenceList.add(sequenceList.get(i));
                            break;
                        }
                    } else {
                        finalSequenceList.add(sequenceList.get(i));
                    }
                }
                ArrayList<Cards> finalSequenceCards = integerToCards(finalSequenceList);
                for(int i=0; i<finalSequenceCards.size(); i++){
                    getPlayingHand().getMyHand().add(finalSequenceCards.get(i));
                }
                for(int i=0; i<finalSequenceCards.size(); i++){
                    getHand().removeCard(finalSequenceCards.get(i));
                }
                getHand().sortHand();

            } else if (playTriple) {
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(0));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(1));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(2));

                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);

                getHand().sortHand();
                System.out.println("Type of play: Triple");

            } else if (playPair) {
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(0));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(1));

                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);

                getHand().sortHand();
                System.out.println("Type of play: Pair");

            } else if (playDoubleSequence) {
                ArrayList<Integer> doubleSequenceList = hasDoubleSequence();
                ArrayList<Integer> finalDoubleSequenceList = new ArrayList<Integer>();
                for(int i=0; i< doubleSequenceList.size(); i++){
                    if(i != doubleSequenceList.size() - 2){
                        if(doubleSequenceList.get(i) + 1 == doubleSequenceList.get(i+2)){
                            finalDoubleSequenceList.add(doubleSequenceList.get(i));
                            finalDoubleSequenceList.add(doubleSequenceList.get(i+1));
                            i++;
                        } else {
                            finalDoubleSequenceList.add(doubleSequenceList.get(i));
                            finalDoubleSequenceList.add(doubleSequenceList.get(i+1));
                            break;
                        }
                    } else {
                        finalDoubleSequenceList.add(doubleSequenceList.get(i));
                        finalDoubleSequenceList.add(doubleSequenceList.get(i+1));
                        break;

                    }
                }
                ArrayList<Cards> finalDoubleSequenceCards = integerToCards(finalDoubleSequenceList);
                for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                    getPlayingHand().getMyHand().add(finalDoubleSequenceCards.get(i));
                }
                for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                    getHand().removeCard(finalDoubleSequenceCards.get(i));
                }

            } else if (playFourOfAKind) {
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(0));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(1));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(2));
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(3));

                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);
                getHand().getMyHand().remove(0);

                getHand().sortHand();
                System.out.println("Type of play: Four of a kind");

            } else{
                getPlayingHand().getMyHand().add(getHand().getMyHand().get(0));
                getHand().getMyHand().remove(0);
                getHand().sortHand();
                System.out.println("Type of play: Single card");

            }



    }
    public void playHand(PlayingHand lastHandPlayed){
        lastHandPlayed.checkTypeOfPlay();
        if(lastHandPlayed.isSingleCard()) {
            Cards playThisCard = findOptimalSingleCardToPlay(lastHandPlayed);
            playedHand(playThisCard);
        } else if(lastHandPlayed.isPair()){
            ArrayList<Integer> listOfPairs = hasPair();
            if(listOfPairs != null){
                ArrayList<Cards> playThisPair = findOptimalPairToPlay(lastHandPlayed);

            }
        }

    }
    public Cards findOptimalSingleCardToPlay(PlayingHand lastHandPlayed){
        lastHandPlayed.checkTypeOfPlay();
        if(lastHandPlayed.isSingleCard()){
            ArrayList<Cards> computerCardsThatCanBeatSingleCard = new ArrayList<Cards>();
            ArrayList<Integer> trashCardList = new ArrayList<Integer>();
            Computer tempComputer = new Computer();
            for(int i=0; i<getHand().getMyHand().size(); i++){
                tempComputer.getHand().getMyHand().add(getHand().getMyHand().get(i));
            }
            int numberOfTrashCards = tempComputer.checkHowManyTrashCards();
            for(int i=0; i< tempComputer.getHand().getMyHand().size(); i++){
                if(tempComputer.getHand().getMyHand().get(i).getValue() > lastHandPlayed.getMyHand().get(0).getValue()){
                    computerCardsThatCanBeatSingleCard.add(tempComputer.getHand().getMyHand().get(i));
                }
            }
            if(computerCardsThatCanBeatSingleCard.size() == 0){
                return null;
            }

            for(int i=0; i<computerCardsThatCanBeatSingleCard.size(); i++){
                tempComputer.getHand().removeCard(computerCardsThatCanBeatSingleCard.get(i));
                int trashCardNumber = tempComputer.checkHowManyTrashCards();
                trashCardList.add(trashCardNumber);
                tempComputer.getHand().addCard(computerCardsThatCanBeatSingleCard.get(i));
                tempComputer.getHand().sortHand();

            }
            for(int x=0; x<trashCardList.size(); x++) {
                for (int i = x + 1; i < trashCardList.size(); i++) {
                    if (trashCardList.get(x) > trashCardList.get(i)){
                        int tempNumber = trashCardList.get(x);
                        trashCardList.set(x, trashCardList.get(i));
                        trashCardList.set(i, tempNumber);

                        Cards tempCard = computerCardsThatCanBeatSingleCard.get(x);
                        computerCardsThatCanBeatSingleCard.set(x, computerCardsThatCanBeatSingleCard.get(i));
                        computerCardsThatCanBeatSingleCard.set(i, tempCard);
                    } else if(trashCardList.get(x) == trashCardList.get(i)){
                        int tempNumber = trashCardList.get(i);
                        trashCardList.remove(i);
                        trashCardList.add(x+1, tempNumber);

                        Cards tempCard = computerCardsThatCanBeatSingleCard.get(i);
                        computerCardsThatCanBeatSingleCard.remove(i);
                        computerCardsThatCanBeatSingleCard.add(x+1, tempCard);
                    }
                }
            }

            return computerCardsThatCanBeatSingleCard.get(0);


        }
        return null;
    }
    public ArrayList<Cards> findOptimalPairToPlay(PlayingHand lastHandPlayed){
        Computer tempComputer = new Computer();
        ArrayList<Cards> potentialPairs = new ArrayList<Cards>();
        ArrayList<Integer> trashCardList = new ArrayList<Integer>();
        for(int i=0; i<getHand().getMyHand().size(); i++){
            tempComputer.getHand().addCard(getHand().getMyHand().get(i));
        }
        lastHandPlayed.checkTypeOfPlay();
        if(lastHandPlayed.isPair()){
            ArrayList<Integer> listOfPairs = hasPair();
            if(listOfPairs != null){
                ArrayList<Cards> listOfPairsCards = integerToCards(listOfPairs);
                for(int i=1; i< listOfPairsCards.size(); i++){
                    if(listOfPairsCards.get(i).getValue() > lastHandPlayed.getMyHand().get(1).getValue()){
                        potentialPairs.add(listOfPairsCards.get(i-1));
                        potentialPairs.add(listOfPairsCards.get(i));
                    }
                    i++;
                }
                if(potentialPairs.size() !=0) {
                    for (int i = 1; i < potentialPairs.size(); i++) {
                        tempComputer.getHand().removeCard(potentialPairs.get(i));
                        tempComputer.getHand().removeCard(potentialPairs.get(i - 1));
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardList.add(trashCardNumber);
                        tempComputer.getHand().getMyHand().add(potentialPairs.get(i));
                        tempComputer.getHand().getMyHand().add(potentialPairs.get(i - 1));
                        tempComputer.getHand().sortHand();
                        i++;
                    }

                    ArrayList<Integer> sortedTrashCardList = new ArrayList<Integer>();
                    for (int i = 0; i < trashCardList.size(); i++) {
                        sortedTrashCardList.add(trashCardList.get(i));
                    }

                    for (int x = 0; x < sortedTrashCardList.size(); x++) {
                        for (int i = x + 1; i < sortedTrashCardList.size(); i++) {
                            if (sortedTrashCardList.get(x) > sortedTrashCardList.get(i)) {
                                Integer tempNumber = sortedTrashCardList.get(x);
                                sortedTrashCardList.set(x, sortedTrashCardList.get(i));
                                sortedTrashCardList.set(i, tempNumber);
                            }
                        }
                    }
                    int positionOfPair = 0;
                    for (int i = 0; i < sortedTrashCardList.size(); i++) {
                        if (trashCardList.get(i) == sortedTrashCardList.get(0)) {
                            positionOfPair = i;
                            break;
                        }
                    }
                    ArrayList<Cards> potentialSingles = new ArrayList<Cards>();
                    for (int i = 1; i < potentialPairs.size(); i++) {
                        potentialSingles.add(potentialPairs.get(i));
                        i++;
                    }
                    ArrayList<Cards> bestPair = new ArrayList<Cards>();

                    for (int i = 0; i < potentialPairs.size(); i++) {
                        if (potentialPairs.get(i).getName().charAt(0) == potentialSingles.get(positionOfPair).getName().charAt(0)) {
                            if (bestPair.size() < 2) {
                                bestPair.add(potentialPairs.get(i));
                            }
                        }
                    }
                    return bestPair;
                } else {
                    return null;
                }

            } else{
                return null;
            }
        } else {
            return null;
        }

    }
    public ArrayList<Cards> findOptimalTripleToPlay(PlayingHand lastHandPlayed){
        ArrayList<Integer> listOfTriples = hasTriple();
        if(listOfTriples != null) {
            Computer tempComputer = new Computer();
            ArrayList<Integer> trashCardList = new ArrayList<Integer>();
            for (int i = 0; i < getHand().getMyHand().size(); i++) {
                tempComputer.getHand().addCard(getHand().getMyHand().get(i));
            }
            ArrayList<Cards> listOfTriplesCards = integerToCards(listOfTriples);
            ArrayList<Cards> potentialTriples = new ArrayList<Cards>();
            for(int i=0; i< listOfTriplesCards.size(); i++){
                if(listOfTriplesCards.get(i).getValue() > lastHandPlayed.getMyHand().get(0).getValue()){
                    potentialTriples.add(listOfTriplesCards.get(i));
                    potentialTriples.add(listOfTriplesCards.get(i+1));
                    potentialTriples.add(listOfTriplesCards.get(i+2));
                    i += 2;

                } else{
                    i += 2;
                }
            }
            if(potentialTriples.size() != 0) {
                int numberOfTrashCards = 0;
                for (int i = 0; i < potentialTriples.size(); i++) {
                    tempComputer.getHand().removeCard(potentialTriples.get(i));
                    tempComputer.getHand().removeCard(potentialTriples.get(i + 1));
                    tempComputer.getHand().removeCard(potentialTriples.get(i + 2));
                    tempComputer.getHand().sortHand();
                    numberOfTrashCards = tempComputer.checkHowManyTrashCards();
                    trashCardList.add(numberOfTrashCards);
                    tempComputer.getHand().addCard(potentialTriples.get(i));
                    tempComputer.getHand().addCard(potentialTriples.get(i + 1));
                    tempComputer.getHand().addCard(potentialTriples.get(i + 2));
                    tempComputer.getHand().sortHand();
                    i += 2;
                }
                ArrayList<Cards> potentialSingles = new ArrayList<Cards>();
                for (int i = 0; i < potentialTriples.size(); i++) {
                    potentialSingles.add(potentialTriples.get(i));
                    i += 2;
                }
                ArrayList<Integer> sortedTrashCardList = new ArrayList<Integer>();
                for (int i = 0; i < trashCardList.size(); i++) {
                    sortedTrashCardList.add(trashCardList.get(i));
                }

                for (int i = 0; i < sortedTrashCardList.size(); i++) {
                    for (int x = i + 1; x < sortedTrashCardList.size(); x++) {
                        if (sortedTrashCardList.get(i) > sortedTrashCardList.get(x)) {
                            Integer tempNumber = sortedTrashCardList.get(i);
                            sortedTrashCardList.set(i, sortedTrashCardList.get(x));
                            sortedTrashCardList.set(x, tempNumber);
                        }
                    }
                }
                int positionOfTriple = 0;
                for (int i = 0; i < trashCardList.size(); i++) {
                    if (trashCardList.get(i) == sortedTrashCardList.get(0)) {
                        positionOfTriple = i;
                        break;
                    }
                }
                ArrayList<Cards> bestTriple = new ArrayList<Cards>();

                for (int i = 0; i < potentialTriples.size(); i++) {
                    if (potentialTriples.get(i).getName().charAt(0) == potentialSingles.get(positionOfTriple).getName().charAt(0)) {
                        bestTriple.add(potentialTriples.get(i));
                        if (bestTriple.size() == 3) {
                            break;
                        }
                    }
                }
                return bestTriple;
            } else {
                return null;
            }

        } else {
            return null;
        }
    }
    public ArrayList<Cards> findOptimalFourToPlay(PlayingHand lastHandPlayed){
        ArrayList<Integer> listOfFours = hasFourOfAKind();
        if(listOfFours != null) {
            Computer tempComputer = new Computer();
            ArrayList<Integer> trashCardList = new ArrayList<Integer>();
            for (int i = 0; i < getHand().getMyHand().size(); i++) {
                tempComputer.getHand().addCard(getHand().getMyHand().get(i));
            }
            ArrayList<Cards> listOfFoursCards = integerToCards(listOfFours);
            ArrayList<Cards> potentialFours = new ArrayList<Cards>();
            for(int i=0; i< listOfFoursCards.size(); i++){
                if(listOfFoursCards.get(i).getValue() > lastHandPlayed.getMyHand().get(0).getValue()){
                    potentialFours.add(listOfFoursCards.get(i));
                    potentialFours.add(listOfFoursCards.get(i+1));
                    potentialFours.add(listOfFoursCards.get(i+2));
                    potentialFours.add(listOfFoursCards.get(i+3));
                    i += 3;

                } else{
                    i += 3;
                }
            }

            if(potentialFours.size() != 0) {
                int numberOfTrashCards = 0;
                for (int i = 0; i < potentialFours.size(); i++) {
                    tempComputer.getHand().removeCard(potentialFours.get(i));
                    tempComputer.getHand().removeCard(potentialFours.get(i + 1));
                    tempComputer.getHand().removeCard(potentialFours.get(i + 2));
                    tempComputer.getHand().removeCard(potentialFours.get(i + 3));
                    tempComputer.getHand().sortHand();
                    numberOfTrashCards = tempComputer.checkHowManyTrashCards();
                    trashCardList.add(numberOfTrashCards);
                    tempComputer.getHand().addCard(potentialFours.get(i));
                    tempComputer.getHand().addCard(potentialFours.get(i + 1));
                    tempComputer.getHand().addCard(potentialFours.get(i + 2));
                    tempComputer.getHand().addCard(potentialFours.get(i + 3));
                    tempComputer.getHand().sortHand();
                    i += 3;
                }
                ArrayList<Cards> potentialSingles = new ArrayList<Cards>();
                for (int i = 0; i < potentialFours.size(); i++) {
                    potentialSingles.add(potentialFours.get(i));
                    i += 3;
                }
                ArrayList<Integer> sortedTrashCardList = new ArrayList<Integer>();
                for (int i = 0; i < trashCardList.size(); i++) {
                    sortedTrashCardList.add(trashCardList.get(i));
                }

                for (int i = 0; i < sortedTrashCardList.size(); i++) {
                    for (int x = i + 1; x < sortedTrashCardList.size(); x++) {
                        if (sortedTrashCardList.get(i) > sortedTrashCardList.get(x)) {
                            Integer tempNumber = sortedTrashCardList.get(i);
                            sortedTrashCardList.set(i, sortedTrashCardList.get(x));
                            sortedTrashCardList.set(x, tempNumber);
                        }
                    }
                }
                int positionOfFour = 0;
                for (int i = 0; i < trashCardList.size(); i++) {
                    if (trashCardList.get(i) == sortedTrashCardList.get(0)) {
                        positionOfFour = i;
                        break;
                    }
                }
                ArrayList<Cards> bestFour = new ArrayList<Cards>();

                for (int i = 0; i < potentialFours.size(); i++) {
                    if (potentialFours.get(i).getName().charAt(0) == potentialSingles.get(positionOfFour).getName().charAt(0)) {
                        bestFour.add(potentialFours.get(i));
                        if (bestFour.size() == 4) {
                            break;
                        }
                    }
                }
                return bestFour;
            } else {
                return null;
            }

        } else {
            return null;
        }
    }
    public ArrayList<Cards> findOptimalSequenceToPlay(PlayingHand lastHandPlayed){
        int lastHandPlayedSequenceSize = lastHandPlayed.getMyHand().size();
        ArrayList<Integer> integerSequenceList = hasSequence();
        ArrayList<Integer> testSequenceList = new ArrayList<Integer>();
        ArrayList<Integer> trashCardList = new ArrayList<Integer>();
        ArrayList<Cards> validSequenceCards = new ArrayList<Cards>();

        if(integerSequenceList != null && integerSequenceList.size() >= lastHandPlayed.getMyHand().size()){
            Computer tempComputer = new Computer();
            for(int i=0; i<getHand().getMyHand().size(); i++){
                tempComputer.getHand().getMyHand().add(getHand().getMyHand().get(i));
            }
            for(int i=0; i<integerSequenceList.size()-(lastHandPlayed.getMyHand().size()-1); i++) {
                for (int x = i; x < lastHandPlayedSequenceSize; x++) {
                    testSequenceList.add(integerSequenceList.get(x));
                }

                lastHandPlayedSequenceSize++;
                if(isHandValidSequence(testSequenceList)) {
                    validSequenceCards = integerToCards(testSequenceList);
                    if(validSequenceCards.get(validSequenceCards.size() - 1).getValue() > lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size()-1).getValue()){
                        for(int a=0; a< validSequenceCards.size(); a++){
                            tempComputer.getHand().removeCard(validSequenceCards.get(a));
                        }
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardList.add(trashCardNumber);
                        for(int a=0; a< validSequenceCards.size(); a++){
                            tempComputer.getHand().addCard(validSequenceCards.get(a));
                        }
                        tempComputer.getHand().sortHand();
                    }
                    testSequenceList = new ArrayList<Integer>();

                } else {
                    testSequenceList = new ArrayList<Integer>();
                }
            }
            if(trashCardList.size() == 0){
                return null;
            } else {
                //sort trash card list
                ArrayList<Integer> sortedTrashCardList = new ArrayList<Integer>();
                for (int i = 0; i < trashCardList.size(); i++) {
                    sortedTrashCardList.add(trashCardList.get(i));
                }

                for (int i = 0; i < sortedTrashCardList.size(); i++) {
                    for (int x = i + 1; x < sortedTrashCardList.size(); x++) {
                        if (sortedTrashCardList.get(i) > sortedTrashCardList.get(x)) {
                            Integer tempNumber = sortedTrashCardList.get(i);
                            sortedTrashCardList.set(i, sortedTrashCardList.get(x));
                            sortedTrashCardList.set(x, tempNumber);
                        }
                    }
                }

                lastHandPlayedSequenceSize = lastHandPlayed.getMyHand().size();




                for(int i=0; i<integerSequenceList.size()-(lastHandPlayed.getMyHand().size()-1); i++) {
                    for (int x = i; x < lastHandPlayedSequenceSize; x++) {
                        testSequenceList.add(integerSequenceList.get(x));
                    }
                    lastHandPlayedSequenceSize++;
                    if(isHandValidSequence(testSequenceList)) {
                        validSequenceCards = integerToCards(testSequenceList);
                        if(validSequenceCards.get(validSequenceCards.size() - 1).getValue() > lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size()-1).getValue()){
                            for(int a=0; a< validSequenceCards.size(); a++){
                                tempComputer.getHand().removeCard(validSequenceCards.get(a));
                            }
                            tempComputer.getHand().sortHand();
                            int trashCardNumber = tempComputer.checkHowManyTrashCards();
                            if(trashCardNumber == sortedTrashCardList.get(0)){
                                return validSequenceCards;
                            }

                            for(int a=0; a< validSequenceCards.size(); a++){
                                tempComputer.getHand().addCard(validSequenceCards.get(a));
                            }
                            tempComputer.getHand().sortHand();
                        }
                        testSequenceList = new ArrayList<Integer>();

                    } else {
                        testSequenceList = new ArrayList<Integer>();
                    }
                }
            }
        } else {
            return null;
        }
        return null;
    }
    private boolean isHandValidSequence(ArrayList<Integer> sequenceListInteger){
        ArrayList<Integer> testSequence = new ArrayList<Integer>();
        boolean isSequence = false;
        for(int i=0; i<sequenceListInteger.size()-1; i++){
            if(sequenceListInteger.get(i) + 1 == sequenceListInteger.get(i+1)){
                isSequence = true;
            } else {
                isSequence = false;
                break;
            }
        }
        return isSequence;


    }
    public ArrayList<Cards> findOptimalDoubleSequenceToPlay(PlayingHand lastHandPlayed){
        ArrayList<Integer> doubleSequenceList = hasDoubleSequence();
        if(doubleSequenceList !=null && doubleSequenceList.size() >= lastHandPlayed.getMyHand().size()){
            Computer tempComputer = new Computer();
            for(int a=0; a<getHand().getMyHand().size(); a++){
                tempComputer.getHand().addCard(getHand().getMyHand().get(a));
            }

            int lastHandPlayedDoubleSequenceSize = lastHandPlayed.getMyHand().size();
            ArrayList<Cards> doubleSequenceListCards = integerToCards(doubleSequenceList);
            Hand computerTempHand = new Hand();
            for(int i=0; i<getHand().getMyHand().size(); i++){
                computerTempHand.addCard(getHand().getMyHand().get(i));
            }
            ArrayList<Cards> testDoubleSequenceCards = new ArrayList<Cards>();
            ArrayList<Integer> trashCardList = new ArrayList<Integer>();
            for(int i=0; i<(doubleSequenceListCards.size() - lastHandPlayed.getMyHand().size()) + 1; i++){
                for(int x=i; x<lastHandPlayedDoubleSequenceSize; x++){
                    testDoubleSequenceCards.add(doubleSequenceListCards.get(x));
                }
                lastHandPlayedDoubleSequenceSize += 2;
                if(testDoubleSequenceCards.get(testDoubleSequenceCards.size()-1).getValue() > lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size()-1).getValue()){
                    for(int a=0; a<testDoubleSequenceCards.size(); a++){
                        tempComputer.getHand().removeCard(testDoubleSequenceCards.get(a));
                    }
                    int trashCardNumber = tempComputer.checkHowManyTrashCards();
                    trashCardList.add(trashCardNumber);

                    for(int a=0; a<testDoubleSequenceCards.size(); a++){
                        tempComputer.getHand().addCard(testDoubleSequenceCards.get(a));
                    }



                }
                testDoubleSequenceCards = new ArrayList<Cards>();
                i++;

            }
            lastHandPlayedDoubleSequenceSize = lastHandPlayed.getMyHand().size();

            if(trashCardList.size() == 0){
                return null;
            }

            //sort trash card list
            ArrayList<Integer> sortedTrashCardList = new ArrayList<Integer>();
            for(int i=0; i<trashCardList.size(); i++){
                sortedTrashCardList.add(trashCardList.get(i));
            }
            for (int i = 0; i < sortedTrashCardList.size(); i++) {
                for (int x = i + 1; x < sortedTrashCardList.size(); x++) {
                    if (sortedTrashCardList.get(i) > sortedTrashCardList.get(x)) {
                        Integer tempNumber = sortedTrashCardList.get(i);
                        sortedTrashCardList.set(i, sortedTrashCardList.get(x));
                        sortedTrashCardList.set(x, tempNumber);
                    }
                }
            }

            ArrayList<Cards> bestDoubleSequence = null;

            for(int i=0; i<(doubleSequenceListCards.size() - lastHandPlayed.getMyHand().size()) + 1; i++){
                for(int x=i; x<lastHandPlayedDoubleSequenceSize; x++){
                    testDoubleSequenceCards.add(doubleSequenceListCards.get(x));
                }
                lastHandPlayedDoubleSequenceSize += 2;
                if(testDoubleSequenceCards.get(testDoubleSequenceCards.size()-1).getValue() > lastHandPlayed.getMyHand().get(lastHandPlayed.getMyHand().size()-1).getValue()){
                    for(int a=0; a<testDoubleSequenceCards.size(); a++){
                        tempComputer.getHand().removeCard(testDoubleSequenceCards.get(a));
                    }
                    int trashCardNumber = tempComputer.checkHowManyTrashCards();
                    if(trashCardNumber == sortedTrashCardList.get(0)){
                        bestDoubleSequence = testDoubleSequenceCards;
                        break;
                    }

                    for(int a=0; a<testDoubleSequenceCards.size(); a++){
                        tempComputer.getHand().addCard(testDoubleSequenceCards.get(a));
                    }



                }
                testDoubleSequenceCards = new ArrayList<Cards>();
                i++;

            }
            return bestDoubleSequence;


        } else {
            return null;
        }


    }
    public ArrayList<Cards> playBestCards(PlayingHand lastHandPlayed){
        lastHandPlayed.checkTypeOfPlay();
        if(lastHandPlayed.isSingleCard()){
            if(lastHandPlayed.getMyHand().get(0).getValue() == 49 || lastHandPlayed.getMyHand().get(0).getValue() == 50 || lastHandPlayed.getMyHand().get(0).getValue() == 51 || lastHandPlayed.getMyHand().get(0).getValue() == 52){
                if(hasDoubleSequence() != null){
                    Computer tempComputer = new Computer();
                    for(int i=0; i<getHand().getMyHand().size(); i++){
                        tempComputer.getHand().addCard(getHand().getMyHand().get(i));
                    }
                    ArrayList<Integer> doubleSequenceList = hasDoubleSequence();
                    ArrayList<Integer> finalDoubleSequenceList = new ArrayList<Integer>();
                    ArrayList<Integer> trashCardList = new ArrayList<Integer>();
                    ArrayList<Cards> doubleSequenceCards = integerToCards(doubleSequenceList);

                    while(doubleSequenceCards.size() >0){
                        for(int i=0; i<doubleSequenceCards.size(); i++){
                            if(i + 2 != doubleSequenceCards.size()) {
                                if (doubleSequenceList.get(i) + 1 == doubleSequenceList.get(i + 2)) {
                                    finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                    finalDoubleSequenceList.add(doubleSequenceList.get(i + 1));
                                    i++;

                                } else {
                                    if (doubleSequenceList.get(i) - 1 == doubleSequenceList.get(i - 1)) {
                                        finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                        finalDoubleSequenceList.add(doubleSequenceList.get(i + 1));
                                        break;

                                    }

                                }
                            } else {
                                finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                finalDoubleSequenceList.add(doubleSequenceList.get(i+1));
                                break;
                            }
                        }
                        ArrayList<Cards> finalDoubleSequenceCards = integerToCards(finalDoubleSequenceList);
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputer.getHand().removeCard(finalDoubleSequenceCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardList.add(trashCardNumber);
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputer.getHand().addCard(finalDoubleSequenceCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        Computer tempComputerTwo = new Computer();
                        for(int i=0; i<doubleSequenceCards.size(); i++){
                            tempComputerTwo.getHand().addCard(doubleSequenceCards.get(i));
                        }
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputerTwo.getHand().removeCard(finalDoubleSequenceCards.get(i));
                        }
                        if(tempComputerTwo.getHand().getMyHand().size() !=0) {
                            tempComputerTwo.getHand().sortHand();
                        }
                        doubleSequenceCards = tempComputerTwo.getHand().getMyHand();

                        for(int i=0; i<finalDoubleSequenceList.size(); i++){
                            doubleSequenceList.remove(0);
                        }
                        finalDoubleSequenceList = new ArrayList<Integer>();

                    }

                    //sort trash card list
                    for (int i = 0; i < trashCardList.size(); i++) {
                        for (int x = i + 1; x < trashCardList.size(); x++) {
                            if (trashCardList.get(i) > trashCardList.get(x)) {
                                Integer tempNumber = trashCardList.get(i);
                                trashCardList.set(i, trashCardList.get(x));
                                trashCardList.set(x, tempNumber);
                            }
                        }
                    }

                    doubleSequenceList = hasDoubleSequence();
                    doubleSequenceCards = integerToCards(doubleSequenceList);

                    while(doubleSequenceCards.size() >0){
                        for(int i=0; i<doubleSequenceCards.size(); i++){
                            if(i + 2 != doubleSequenceCards.size()) {
                                if (doubleSequenceList.get(i) + 1 == doubleSequenceList.get(i + 2)) {
                                    finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                    finalDoubleSequenceList.add(doubleSequenceList.get(i + 1));
                                    i++;

                                } else {
                                    if (doubleSequenceList.get(i) - 1 == doubleSequenceList.get(i - 1)) {
                                        finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                        finalDoubleSequenceList.add(doubleSequenceList.get(i + 1));
                                        break;

                                    }

                                }
                            } else {
                                finalDoubleSequenceList.add(doubleSequenceList.get(i));
                                finalDoubleSequenceList.add(doubleSequenceList.get(i+1));
                                break;
                            }
                        }
                        ArrayList<Cards> finalDoubleSequenceCards = integerToCards(finalDoubleSequenceList);
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputer.getHand().removeCard(finalDoubleSequenceCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        if(trashCardNumber == trashCardList.get(0)){
                            return finalDoubleSequenceCards;
                        }
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputer.getHand().addCard(finalDoubleSequenceCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        Computer tempComputerTwo = new Computer();
                        for(int i=0; i<doubleSequenceCards.size(); i++){
                            tempComputerTwo.getHand().addCard(doubleSequenceCards.get(i));
                        }
                        for(int i=0; i<finalDoubleSequenceCards.size(); i++){
                            tempComputerTwo.getHand().removeCard(finalDoubleSequenceCards.get(i));
                        }

                        if(tempComputerTwo.getHand().getMyHand().size() !=0) {
                            tempComputerTwo.getHand().sortHand();
                        }
                        doubleSequenceCards = tempComputerTwo.getHand().getMyHand();

                        for(int i=0; i<finalDoubleSequenceList.size(); i++){
                            doubleSequenceList.remove(0);
                        }
                        finalDoubleSequenceList = new ArrayList<Integer>();

                    }
                } else if(hasFourOfAKind() != null){
                    Computer tempComputer = new Computer();
                    for(int i=0; i<getHand().getMyHand().size(); i++){
                        tempComputer.getHand().addCard(getHand().getMyHand().get(i));
                    }
                    ArrayList<Integer> fourOfKindList = hasFourOfAKind();
                    ArrayList<Integer> trashCardList = new ArrayList<Integer>();
                    ArrayList<Cards> fourOfKindCards = integerToCards(fourOfKindList);
                    while(fourOfKindCards.size() != 0) {
                        for (int i = 0; i < 4; i++) {
                            tempComputer.getHand().removeCard(fourOfKindCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardList.add(trashCardNumber);
                        for (int i = 0; i < 4; i++) {
                            tempComputer.getHand().addCard(fourOfKindCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        for(int i=0; i<4; i++){
                            fourOfKindCards.remove(0);
                        }
                    }
                    //sort trash card list
                    for (int i = 0; i < trashCardList.size(); i++) {
                        for (int x = i + 1; x < trashCardList.size(); x++) {
                            if (trashCardList.get(i) > trashCardList.get(x)) {
                                Integer tempNumber = trashCardList.get(i);
                                trashCardList.set(i, trashCardList.get(x));
                                trashCardList.set(x, tempNumber);
                            }
                        }
                    }
                    fourOfKindCards = integerToCards(fourOfKindList);

                    while(fourOfKindCards.size() != 0) {
                        for (int i = 0; i < 4; i++) {
                            tempComputer.getHand().removeCard(fourOfKindCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        if(trashCardNumber == trashCardList.get(0)){
                            ArrayList<Cards> bestFour = new ArrayList<Cards>();
                            for(int i=0; i<4; i++){
                                bestFour.add(fourOfKindCards.get(i));
                            }
                            return bestFour;
                        }
                        for (int i = 0; i < 4; i++) {
                            tempComputer.getHand().addCard(fourOfKindCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        for(int i=0; i<4; i++){
                            fourOfKindCards.remove(0);
                        }
                    }
                }
            }
            ArrayList<Cards> bestSingleCard = new ArrayList<Cards>();
            Cards bestSingle = findOptimalSingleCardToPlay(lastHandPlayed);
            if(bestSingle != null){
                bestSingleCard.add(bestSingle);
                return bestSingleCard;
            } else {
                return null;
            }

        } else if(lastHandPlayed.isPair()){
            ArrayList<Cards> bestPair = findOptimalPairToPlay(lastHandPlayed);
            if(bestPair !=null){
                return bestPair;
            } else{
                return null;
            }

        } else if(lastHandPlayed.isTriple()){
            ArrayList<Cards> bestTriple = findOptimalTripleToPlay(lastHandPlayed);
            if(bestTriple !=null){
                return bestTriple;
            } else {
                return null;
            }
        } else if(lastHandPlayed.isFourOfKind()){
            ArrayList<Cards> bestFour = findOptimalFourToPlay(lastHandPlayed);
            if(bestFour !=null){
                return bestFour;
            } else {
                return null;
            }
        } else if(lastHandPlayed.isSequence()){
            ArrayList<Cards> bestSequence = findOptimalSequenceToPlay(lastHandPlayed);
            if(bestSequence !=null){
                return bestSequence;
            } else{
                return null;
            }
        } else if(lastHandPlayed.isDoubleSequence()){
            ArrayList<Cards> bestDoubleSequence = findOptimalDoubleSequenceToPlay(lastHandPlayed);
            if(bestDoubleSequence != null){
                return bestDoubleSequence;
            } else {
                return null;
            }
        } else{
            return null;
        }
    }
    public ArrayList<Cards> playBestCards(){
        ArrayList<Cards> bestSequence = new ArrayList<Cards>();
        ArrayList<Cards> bestTriple = new ArrayList<Cards>();
        ArrayList<Cards> bestPair = new ArrayList<Cards>();
        ArrayList<Cards> bestSingle = new ArrayList<Cards>();

        int sequenceWithLeastTrash = 999;
        int tripleWithLeastTrash = 999;
        int pairWithLeastTrash = 999;
        int singleWithLeastTrash = 999;

        ArrayList<Integer> trashCardList = new ArrayList<Integer>();

        Computer tempComputer = new Computer();
        for(int i=0; i<getHand().getMyHand().size(); i++){
            tempComputer.getHand().addCard(getHand().getMyHand().get(i));
        }

        //find sequence with lowest trash cards
        ArrayList<Integer> sequenceList = hasSequence();

        if(sequenceList !=null) {
            ArrayList<Integer> trashCardListForSequence = new ArrayList<Integer>();
            ArrayList<Cards> sequenceListCards = integerToCards(sequenceList);
            ArrayList<Integer> testSequence = new ArrayList<Integer>();
            ArrayList<Cards> testSequenceCards;
            while (sequenceList.size() >= 3) {
                for (int i = 0; i < sequenceList.size() - 1; i++) {
                    if (sequenceList.get(i) + 1 == sequenceList.get(i + 1)) {
                        testSequence.add(sequenceList.get(i));
                        if (i + 1 == sequenceList.size() - 1) {
                            testSequence.add(sequenceList.get(i + 1));
                        }
                    } else {
                        testSequence.add(sequenceList.get(i));
                        break;
                    }
                }
                for (int i = 0; i < testSequence.size(); i++) {
                    sequenceList.remove(0);
                }
                testSequenceCards = integerToCards(testSequence);
                for (int i = 0; i < testSequenceCards.size(); i++) {
                    tempComputer.getHand().removeCard(testSequenceCards.get(i));
                }
                int trashCardNumber = tempComputer.checkHowManyTrashCards();
                trashCardListForSequence.add(trashCardNumber);

                for (int i = 0; i < testSequenceCards.size(); i++) {
                    tempComputer.getHand().addCard(testSequenceCards.get(i));
                }
                tempComputer.getHand().sortHand();
                testSequence = new ArrayList<Integer>();
                testSequenceCards = null;
            }

            //sort trash card list for sequence
            for (int i = 0; i < trashCardListForSequence.size(); i++) {
                for (int x = i + 1; x < trashCardListForSequence.size(); x++) {
                    if (trashCardListForSequence.get(i) > trashCardListForSequence.get(x)) {
                        Integer tempNumber = trashCardListForSequence.get(i);
                        trashCardListForSequence.set(i, trashCardListForSequence.get(x));
                        trashCardListForSequence.set(x, tempNumber);
                    }
                }
            }
            int leastAmountOfTrash = trashCardListForSequence.get(0);


            sequenceList = hasSequence();

            while (sequenceList.size() >= 3) {
                for (int i = 0; i < sequenceList.size() - 1; i++) {
                    if (sequenceList.get(i) + 1 == sequenceList.get(i + 1)) {
                        testSequence.add(sequenceList.get(i));
                        if (i + 1 == sequenceList.size() - 1) {
                            testSequence.add(sequenceList.get(i + 1));
                        }
                    } else {
                        testSequence.add(sequenceList.get(i));
                        break;
                    }
                }
                for (int i = 0; i < testSequence.size(); i++) {
                    sequenceList.remove(0);
                }
                testSequenceCards = integerToCards(testSequence);
                for (int i = 0; i < testSequenceCards.size(); i++) {
                    tempComputer.getHand().removeCard(testSequenceCards.get(i));
                }
                int trashCardNumber = tempComputer.checkHowManyTrashCards();

                for (int i = 0; i < testSequenceCards.size(); i++) {
                    tempComputer.getHand().addCard(testSequenceCards.get(i));
                }
                tempComputer.getHand().sortHand();

                if (trashCardNumber == leastAmountOfTrash) {
                    for(int i=0; i<testSequenceCards.size(); i++){
                        bestSequence.add(testSequenceCards.get(i));
                    }
                    sequenceWithLeastTrash = leastAmountOfTrash;
                    break;
                }


                testSequence = new ArrayList<Integer>();
                testSequenceCards = null;
            }
        }

            //find triple with lowest trash cards
            ArrayList<Integer> trashCardListForTriple = new ArrayList<Integer>();
            ArrayList<Integer> tripleList = hasTriple();
            if(tripleList !=null) {
                ArrayList<Cards> testTripleCards = integerToCards(tripleList);
                ArrayList<Cards> singleCards = new ArrayList<Cards>();
                for (int i = 0; i < testTripleCards.size(); i++) {
                    singleCards.add(testTripleCards.get(i));
                    i += 2;
                }
                while (testTripleCards.size() >= 3) {
                    for (int i = 0; i < 3; i++) {
                        tempComputer.getHand().removeCard(testTripleCards.get(i));
                    }
                    int trashCardNumber = tempComputer.checkHowManyTrashCards();
                    trashCardListForTriple.add(trashCardNumber);

                    for (int i = 0; i < 3; i++) {
                        tempComputer.getHand().addCard(testTripleCards.get(i));
                    }
                    tempComputer.getHand().sortHand();

                    for (int i = 0; i < 3; i++) {
                        testTripleCards.remove(0);
                    }

                }

                //sort trash card list for triple
                ArrayList<Integer> sortedTrashCardListForTriple = new ArrayList<Integer>();
                for (int i = 0; i < trashCardListForTriple.size(); i++) {
                    sortedTrashCardListForTriple.add(trashCardListForTriple.get(i));
                }
                for (int i = 0; i < sortedTrashCardListForTriple.size(); i++) {
                    for (int x = i + 1; x < sortedTrashCardListForTriple.size(); x++) {
                        if (sortedTrashCardListForTriple.get(i) > sortedTrashCardListForTriple.get(x)) {
                            Integer tempNumber = sortedTrashCardListForTriple.get(i);
                            sortedTrashCardListForTriple.set(i, sortedTrashCardListForTriple.get(x));
                            sortedTrashCardListForTriple.set(x, tempNumber);
                        }
                    }
                }

                tripleWithLeastTrash = sortedTrashCardListForTriple.get(0);

                int positionOfTriple = -1;

                for (int i = 0; i < trashCardListForTriple.size(); i++) {
                    if (trashCardListForTriple.get(i) == tripleWithLeastTrash) {
                        positionOfTriple = i;
                    }
                }
                testTripleCards = integerToCards(tripleList);
                for (int i = 0; i < testTripleCards.size(); i++) {
                    if (testTripleCards.get(i).getName().charAt(0) == singleCards.get(positionOfTriple).getName().charAt(0)) {
                        bestTriple.add(testTripleCards.get(i));
                    }
                    if (bestTriple.size() == 3) {
                        break;
                    }
                }
            }

                //find pair with lowest trash cards
                ArrayList<Integer> pairList = hasPair();
                if(pairList != null) {
                    ArrayList<Integer> trashCardListForPair = new ArrayList<Integer>();
                    ArrayList<Cards> testPairCards = integerToCards(pairList);
                    ArrayList<Cards> singleCards = new ArrayList<Cards>();
                    for (int i = 0; i < testPairCards.size(); i++) {
                        singleCards.add(testPairCards.get(i));
                        i++;
                    }
                    while (testPairCards.size() >= 2) {
                        for (int i = 0; i < 2; i++) {
                            tempComputer.getHand().removeCard(testPairCards.get(i));
                        }
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardListForPair.add(trashCardNumber);

                        for (int i = 0; i < 2; i++) {
                            tempComputer.getHand().addCard(testPairCards.get(i));
                        }
                        tempComputer.getHand().sortHand();
                        for (int i = 0; i < 2; i++) {
                            testPairCards.remove(0);
                        }
                    }

                    //sort trash card list for pair
                    ArrayList<Integer> sortedTrashCardListForPair = new ArrayList<Integer>();
                    for (int i = 0; i < trashCardListForPair.size(); i++) {
                        sortedTrashCardListForPair.add(trashCardListForPair.get(i));
                    }
                    for (int i = 0; i < sortedTrashCardListForPair.size(); i++) {
                        for (int x = i + 1; x < sortedTrashCardListForPair.size(); x++) {
                            if (sortedTrashCardListForPair.get(i) > sortedTrashCardListForPair.get(x)) {
                                Integer tempNumber = sortedTrashCardListForPair.get(i);
                                sortedTrashCardListForPair.set(i, sortedTrashCardListForPair.get(x));
                                sortedTrashCardListForPair.set(x, tempNumber);
                            }
                        }
                    }
                    pairWithLeastTrash = sortedTrashCardListForPair.get(0);
                    int positionOfPair = -1;

                    for (int i = 0; i < trashCardListForPair.size(); i++) {
                        if (trashCardListForPair.get(i) == pairWithLeastTrash) {
                            positionOfPair = i;
                            break;
                        }
                    }
                    testPairCards = integerToCards(pairList);

                    for (int i = 0; i < testPairCards.size(); i++) {
                        if (testPairCards.get(i).getName().charAt(0) == singleCards.get(positionOfPair).getName().charAt(0)) {
                            bestPair.add(testPairCards.get(i));
                        }
                        if (bestPair.size() == 2) {
                            break;
                        }
                    }
                }

                    //find single card with lowest trash cards
                    ArrayList<Integer> trashCardListForSingle = new ArrayList<Integer>();
                    for(int i=0; i<getHand().getMyHand().size(); i++){
                        tempComputer.getHand().getMyHand().remove(i);
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        trashCardListForSingle.add(trashCardNumber);
                        tempComputer.getHand().getMyHand().add(getHand().getMyHand().get(i));
                        tempComputer.getHand().sortHand();
                    }

                    //sort trash card list for single
                    for (int i = 0; i < trashCardListForSingle.size(); i++) {
                        for (int x = i + 1; x < trashCardListForSingle.size(); x++) {
                            if (trashCardListForSingle.get(i) > trashCardListForSingle.get(x)) {
                                Integer tempNumber = trashCardListForSingle.get(i);
                                trashCardListForSingle.set(i, trashCardListForSingle.get(x));
                                trashCardListForSingle.set(x, tempNumber);
                            }
                        }
                    }
                    singleWithLeastTrash = trashCardListForSingle.get(0);

                    for(int i=0; i<getHand().getMyHand().size(); i++){
                        tempComputer.getHand().getMyHand().remove(i);
                        int trashCardNumber = tempComputer.checkHowManyTrashCards();
                        if(trashCardNumber == singleWithLeastTrash){
                            bestSingle.add(getHand().getMyHand().get(i));
                            break;
                        }
                        tempComputer.getHand().getMyHand().add(getHand().getMyHand().get(i));
                        tempComputer.getHand().sortHand();
                    }

                    trashCardList.add(sequenceWithLeastTrash);
                    trashCardList.add(tripleWithLeastTrash);
                    trashCardList.add(pairWithLeastTrash);
                    trashCardList.add(singleWithLeastTrash);

                    //sort trash card list
                    for (int i = 0; i < trashCardList.size(); i++) {
                        for (int x = i + 1; x < trashCardList.size(); x++) {
                            if (trashCardList.get(i) > trashCardList.get(x)) {
                                Integer tempNumber = trashCardList.get(i);
                                trashCardList.set(i, trashCardList.get(x));
                                trashCardList.set(x, tempNumber);
                            }
                        }
                    }
                    int handWithLeastTrash = trashCardList.get(0);

                    //check to see if computer can play four of a kind or double sequence if there are no other hands available
                    if(sequenceList == null && tripleList == null && pairList == null){
                        if(hasDoubleSequence() !=null){
                            ArrayList<Integer> doubleSequenceList = hasDoubleSequence();
                            ArrayList<Cards> doubleSequenceListCards = integerToCards(doubleSequenceList);
                            return doubleSequenceListCards;
                        } else if(hasFourOfAKind() != null){
                            ArrayList<Integer> fourOfAKindList = hasFourOfAKind();
                            ArrayList<Cards> fourOfaKindCards = integerToCards(fourOfAKindList);
                            return fourOfaKindCards;
                        }
                    }


                    //decide whether to play sequence, triple, pair, or single based on whichever hand leaves the lowest amount of trash cards
                    if(sequenceWithLeastTrash == handWithLeastTrash){
                        return bestSequence;
                    } else if(tripleWithLeastTrash == handWithLeastTrash){
                        return bestTriple;
                    } else if(pairWithLeastTrash == handWithLeastTrash){
                        return bestPair;
                    } else if(singleWithLeastTrash == handWithLeastTrash){
                        return bestSingle;
                    }

                    return null;




    }

    public ArrayList<Cards> integerToCards(ArrayList<Integer> listOfIntegers){
        ArrayList<Cards> convertedToCards = new ArrayList<Cards>();
        Hand computerTempHand = new Hand();
        for(int i=0; i<getHand().getMyHand().size(); i++){
            computerTempHand.addCard(getHand().getMyHand().get(i));
        }
        for(int i=0; i< listOfIntegers.size(); i++){
            if(listOfIntegers.get(i) == 10){ //if integer represents a 10
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == '1'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;

                    }
                }
            }else if(listOfIntegers.get(i) == 11){ //if integer represents a Jack
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == 'J'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;

                    }
                }
            } else if(listOfIntegers.get(i) == 12){ //if integer represents a Queen
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == 'Q'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;
                    }
                }
            } else if(listOfIntegers.get(i) == 13){ //if integer represents a King
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == 'K'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;
                    }
                }
            } else if(listOfIntegers.get(i) == 14){ //if integer represents an Ace
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == 'A'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;
                    }
                }
            } else if(listOfIntegers.get(i) == 16){ //if integer represents a 2
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    if(computerTempHand.getMyHand().get(x).getName().charAt(0) == '2'){
                        convertedToCards.add(computerTempHand.getMyHand().get(x));
                        computerTempHand.getMyHand().remove(x);
                        break;
                    }
                }
            } else{
                Integer digit = 0;
                for(int x=0; x<computerTempHand.getMyHand().size(); x++){
                    String digitString = String.valueOf(computerTempHand.getMyHand().get(x).getName().charAt(0));
                    if(!digitString.equals("J") && !digitString.equals("Q") && !digitString.equals("K") && !digitString.equals("A")) {
                        digit = Integer.parseInt(digitString);
                        if(listOfIntegers.get(i) == digit){
                            convertedToCards.add(computerTempHand.getMyHand().get(x));
                            computerTempHand.getMyHand().remove(x);
                            break;
                        }
                    }

                }
            }
        }
        if(convertedToCards.size() > 0){
            return convertedToCards;
        } else {
            return null;
        }
    }
    public int checkHowManyTrashCards(){
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> trashCardsList = new ArrayList<Integer>();
        int trashCards = 0;

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }

        for(int i=0; i<cardsInHand.size(); i++){
            if(isSingleCardPartOfPair(getHand().getMyHand().get(i))){
                continue;
            } else if(isSingleCardPartOfTriple(getHand().getMyHand().get(i))){
                continue;
            } else if(isSingleCardPartOfFourOfAKind(getHand().getMyHand().get(i))){
                continue;
            } else if(isSingleCardPartOfSequence(getHand().getMyHand().get(i))){
                continue;
            } else if(isSingleCardPartOfDoubleSequence(getHand().getMyHand().get(i))){
                continue;
            } else{
                if(getHand().getMyHand().get(i).getName().charAt(0) != 'A' && getHand().getMyHand().get(i).getName().charAt(0) != '2'){
                    trashCards += 1;
                    trashCardsList.add(cardsInHand.get(i));
                }

            }

        }

        return trashCards;


    }
    public boolean playPair(){
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> listOfPairs = new ArrayList<Integer>();
        ArrayList<Cards> listOfPairsCards = new ArrayList<Cards>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }

            //add all the pairs in the hand to listOfPairs integer array list
            for(int x=0; x<cardsInHand.size() - 1; x++){
                if(cardsInHand.get(x) == cardsInHand.get(x+1)){
                    listOfPairs.add(getHand().getMyHand().get(x).getValue());
                    listOfPairs.add(getHand().getMyHand().get(x+1).getValue());
                    listOfPairsCards.add(getHand().getMyHand().get(x));
                    listOfPairsCards.add(getHand().getMyHand().get(x+1));
                    x++;

                }
            }

            //add the lowest pair from the listOfPairs to a new array list that holds card objects
            ArrayList<Cards> lowestPair = new ArrayList<Cards>();
            lowestPair.add(listOfPairsCards.get(0));
            lowestPair.add(listOfPairsCards.get(1));
            listOfPairsCards.remove(0);
            listOfPairs.remove(1);

            //check to see if lowest pair of cards are part of a sequence, double sequence, or four of a kind
        return true;


    }
    public ArrayList<Integer> hasSequence(){
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> tempCardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> sequenceList = new ArrayList<Integer>();
        ArrayList<Integer> finalSequenceList = new ArrayList<Integer>();
        ArrayList<Cards> sequenceListCards = new ArrayList<Cards>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }
        for(int i=0; i< cardsInHand.size(); i++){
            tempCardsInHand.add(cardsInHand.get(i));
        }
        while(tempCardsInHand.size() > 2) {
            sequenceList.add(tempCardsInHand.get(0));
            int checkCard = tempCardsInHand.get(0) + 1;
            tempCardsInHand.remove(0);
            for (int i = 0; i < tempCardsInHand.size(); i++) {
                if (tempCardsInHand.get(i) == checkCard) {
                    sequenceList.add(tempCardsInHand.get(i));
                    tempCardsInHand.remove(i);
                    checkCard++;
                    i--;
                }
            }
            if (sequenceList.size() > 2) {
                for(int i=0; i<sequenceList.size(); i++){
                    finalSequenceList.add(sequenceList.get(i));
                }
                sequenceList = new ArrayList<Integer>();

            } else{
                sequenceList = new ArrayList<Integer>();
            }
        }
        if(finalSequenceList.size() != 0){
            return finalSequenceList;
        } else{
            return null;
        }
    }
    public ArrayList<Integer> hasDoubleSequence() {
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> listOfDoubles = new ArrayList<Integer>();
        ArrayList<Integer> doubleSequenceList = new ArrayList<Integer>();
        ArrayList<Cards> doubleSequenceCards = new ArrayList<Cards>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }

        for (int i = 0; i < cardsInHand.size()-1; i++) {
            if (cardsInHand.get(i) == cardsInHand.get(i + 1)) {
                listOfDoubles.add(cardsInHand.get(i));
                listOfDoubles.add(cardsInHand.get(i + 1));
                doubleSequenceCards.add(getHand().getMyHand().get(i));
                doubleSequenceCards.add(getHand().getMyHand().get(i+1));
                i++;
            }
        }
        if(doubleSequenceCards.size() > 5) {
            Computer tempComputer = new Computer();
            for(int i=0; i< doubleSequenceCards.size(); i++){
                tempComputer.getHand().addCard(doubleSequenceCards.get(i));
                i++;
            }
            ArrayList<Integer> finalSequenceList = tempComputer.hasSequence();
            if(finalSequenceList != null){
                return listOfDoubles;
            } else {
                return null;
            }
        }
        return null;

    }
    public ArrayList<Integer> hasPair(){
        ArrayList<Integer> listOfPairs = new ArrayList<Integer>();
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }

        for (int i = 0; i < cardsInHand.size()-1; i++) {
            if (cardsInHand.get(i) == cardsInHand.get(i + 1)) {
                listOfPairs.add(cardsInHand.get(i));
                listOfPairs.add(cardsInHand.get(i + 1));
                i++;
            }
        }
        if(listOfPairs.size() > 1){
            return listOfPairs;
        } else {
            return null;
        }


    }
    public ArrayList<Integer> hasTriple(){
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> listOfTriple = new ArrayList<Integer>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }

        for(int i=0; i<cardsInHand.size()-2; i++){
            if(cardsInHand.get(i) == cardsInHand.get(i + 1)  && cardsInHand.get(i + 1) == cardsInHand.get(i + 2)){
                listOfTriple.add(cardsInHand.get(i));
                listOfTriple.add(cardsInHand.get(i+1));
                listOfTriple.add(cardsInHand.get(i+2));
                i += 2;

            }
        }
        if(listOfTriple.size() != 0){
            return listOfTriple;
        } else {
            return null;
        }
    }
    public ArrayList<Integer> hasFourOfAKind(){
        ArrayList<Integer> cardsInHand = new ArrayList<Integer>();
        ArrayList<Integer> listOfFour = new ArrayList<Integer>();

        int numDigit = 0;
        for (int i = 0; i < getHand().getMyHand().size(); i++) {
            char digit = getHand().getMyHand().get(i).getName().charAt(0);
            String digitString = String.valueOf(digit);

            if (digitString.equals("J")) {
                numDigit = 11;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("Q")) {
                numDigit = 12;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("K")) {
                numDigit = 13;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("A")) {
                numDigit = 14;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("2")) {
                numDigit = 16;
                cardsInHand.add(numDigit);
            } else if (digitString.equals("1")) {
                numDigit = 10;
                cardsInHand.add(numDigit);
            } else {
                numDigit = Integer.parseInt(digitString);
                cardsInHand.add(numDigit);
            }
        }
        for(int i=0; i<cardsInHand.size()-3; i++){
            if(cardsInHand.get(i) == cardsInHand.get(i+1) && cardsInHand.get(i+1) == cardsInHand.get(i+2) && cardsInHand.get(i+2) == cardsInHand.get(i+3)){
                listOfFour.add(cardsInHand.get(i));
                listOfFour.add(cardsInHand.get(i+1));
                listOfFour.add(cardsInHand.get(i+2));
                listOfFour.add(cardsInHand.get(i+3));
                i += 3;
            }

        }
        if(listOfFour.size() != 0){
            return listOfFour;
        } else {
            return null;
        }

    }

    public boolean isPairPartOfSequence(Cards firstCard, Cards secondCard){
        if(isSingleCardPartOfSequence(firstCard)){
            return true;
        } else {
            return false;
        }

    }
    public boolean isSingleCardPartOfSequence(Cards singleCard){
        Integer digit;
        ArrayList<Integer> finalSequenceList = hasSequence();
        if(finalSequenceList != null){
            char firstLetter = singleCard.getName().charAt(0);
            String digitString = String.valueOf(firstLetter);
            if(digitString.equals("J")){
                digit = 11;
            } else if(digitString.equals("Q")){
                digit = 12;
            } else if(digitString.equals("K")){
                digit = 13;
            } else if(digitString.equals("A")){
                digit = 14;
            } else if(digitString.equals("2")){
                digit = 16;
            } else if(digitString.equals("1")){
                digit = 10;
            } else {
                digit = Integer.parseInt(digitString);
            }
            if(finalSequenceList.contains(digit)){
                return true;
            } else{
                return false;
            }

        } else {
            return false;
        }
    }
    public boolean isSingleCardPartOfPair(Cards singleCard){
        ArrayList<Integer> finalPairsList = hasPair();
        if(finalPairsList != null){
            int digit = 0;
            char firstLetter = singleCard.getName().charAt(0);
            String digitString = String.valueOf(firstLetter);
            if(digitString.equals("J")){
                digit = 11;
            } else if(digitString.equals("Q")){
                digit = 12;
            } else if(digitString.equals("K")){
                digit = 13;
            } else if(digitString.equals("A")){
                digit = 14;
            } else if(digitString.equals("2")){
                digit = 16;
            } else if(digitString.equals("1")){
                digit = 10;
            } else {
                digit = Integer.parseInt(digitString);
            }
            if(finalPairsList.contains(digit)){
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean isSingleCardPartOfTriple(Cards singleCard){
        ArrayList<Integer> listOfTriple = hasTriple();
        int digit = 0;
        if(listOfTriple != null){
            char letter = singleCard.getName().charAt(0);
            String digitString = String.valueOf(letter);

            if(digitString.equals("J")){
                digit = 11;
            } else if(digitString.equals("Q")){
                digit = 12;
            } else if(digitString.equals("K")){
                digit = 13;
            } else if(digitString.equals("A")){
                digit = 14;
            } else if(digitString.equals("2")){
                digit = 16;
            } else if(digitString.equals("1")){
                digit = 10;
            } else {
                digit = Integer.parseInt(digitString);
            }

            if(listOfTriple.contains(digit)){
                return true;
            } else{
                return false;
            }


        } else {
            return false;
        }
    }
    public boolean isSingleCardPartOfFourOfAKind(Cards singleCard){
        ArrayList<Integer> listOfFour = hasFourOfAKind();
        int digit = 0;
        if(listOfFour !=null){
            char letter = singleCard.getName().charAt(0);
            String digitString = String.valueOf(letter);

            if(digitString.equals("J")){
                digit = 11;
            } else if(digitString.equals("Q")){
                digit = 12;
            } else if(digitString.equals("K")){
                digit = 13;
            } else if(digitString.equals("A")){
                digit = 14;
            } else if(digitString.equals("2")){
                digit = 16;
            } else if(digitString.equals("1")){
                digit = 10;
            } else {
                digit = Integer.parseInt(digitString);
            }

            if(listOfFour.contains(digit)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean isSingleCardPartOfDoubleSequence(Cards singleCard){
        ArrayList<Integer> finalDoubleSequenceList = hasDoubleSequence();
        if(finalDoubleSequenceList != null){
            int digit = 0;
            char firstLetter = singleCard.getName().charAt(0);
            String digitString = String.valueOf(firstLetter);
            if(digitString.equals("J")){
                digit = 11;
            } else if(digitString.equals("Q")){
                digit = 12;
            } else if(digitString.equals("K")){
                digit = 13;
            } else if(digitString.equals("A")){
                digit = 14;
            } else if(digitString.equals("2")){
                digit = 16;
            } else if(digitString.equals("1")){
                digit = 10;
            } else {
                digit = Integer.parseInt(digitString);
            }
            if(finalDoubleSequenceList.contains(digit)){
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }




}
