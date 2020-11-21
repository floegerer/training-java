public class SimpleDotCom {

    int [] locationCells;
    int numOfHits = 0;

    String checkYourself( String userGuess ) {

        int guess = Integer.parseInt(userGuess);

        String result = "miss";

        for (int cell : locationCells) {

            if(guess == cell) {

                result = "hit";

                numOfHits++;

                break;

            }

        }

        if (numOfHits == locationCells.length) {
            result = "kill";
        }

        System.out.println(result);

        return result;

    }

    void setLocationCells(int[] cellLocations) {

        locationCells = cellLocations;

    }

}
