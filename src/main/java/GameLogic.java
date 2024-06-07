import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
	This class implements the logic behind the game
	e.g. calculating winnings, finding numbers that matched, generating random numbers
 */
public class GameLogic {
    Map<Integer, Integer> oneSpot = new HashMap<>();
    Map<Integer, Integer> fourSpot = new HashMap<>();
    Map<Integer, Integer> eightSpot = new HashMap<>();
    Map<Integer, Integer> tenSpot = new HashMap<>();

    GameLogic() {
        // initialize spot maps for numMatched (key) and amountWon (value)
        oneSpot.put(1, 2);

        fourSpot.put(2, 1);
        fourSpot.put(3, 5);
        fourSpot.put(4, 75);

        eightSpot.put(4, 2);
        eightSpot.put(5, 12);
        eightSpot.put(6, 50);
        eightSpot.put(7, 750);
        eightSpot.put(8, 10000);

        tenSpot.put(0, 5);
        tenSpot.put(5, 2);
        tenSpot.put(6, 15);
        tenSpot.put(7, 40);
        tenSpot.put(8, 450);
        tenSpot.put(9, 4250);
        tenSpot.put(10, 100000);
    }
    // make 20 random numbers
    public ArrayList<Integer> generateNums() {
        ArrayList<Integer> randomNums = new ArrayList<>();

        int num = (int) (Math.random() * 80 + 1);
        randomNums.add(num);


        for (int i = 0; i < 19; i++) {
            while (randomNums.contains(num)) {
                num = (int) (Math.random() * 80 + 1);
            }

            randomNums.add(num);
        }

        //returns array holding twenty unique random numbers
        return randomNums;
    }

    //Creates an array list of only the player numbers that matched one of the twenty randomly generated
    ArrayList<Integer> matchedNums(ArrayList<Integer> numbersSelected, ArrayList<Integer> randomNums) {
        ArrayList<Integer> matchedNums = new ArrayList<>();
        for (int i = 0; i < numbersSelected.size(); i++) {
            for (int j = 0; j < randomNums.size(); j++) {
                if (numbersSelected.get(i) == randomNums.get(j)) {
                    matchedNums.add(numbersSelected.get(i));
                }
            }
        }
        return matchedNums;
    }

    //Calculates the winnings based on the odds and amount matched (using maps)
    int calculateWinnings(int numSpots, ArrayList<Integer> matchedNums) {
        int numMatched = matchedNums.size();
        int amountWon;
        if(numSpots == 1 && oneSpot.containsKey(numMatched)) {
            amountWon = oneSpot.get(numMatched);
        }
        else if(numSpots == 4 && fourSpot.containsKey(numMatched)) {
            amountWon = fourSpot.get(numMatched);
        }
        else if(numSpots == 8 && eightSpot.containsKey(numMatched)) {
            amountWon = eightSpot.get(numMatched);
        }
        else if(numSpots == 10 && tenSpot.containsKey(numMatched)) {
            amountWon = tenSpot.get(numMatched);
        }
        else {
            amountWon = 0;
        }
        return amountWon;
    }

}