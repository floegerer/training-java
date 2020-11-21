class SimpleDotComGame {

    public static void main(String[] args) {

        int numOfGuesses = 0;

        GameHelper gameHelper = new GameHelper();

        SimpleDotCom theDotCom = new SimpleDotCom();

        int randomNum = (int) (Math.random() * 5);

        int [] locations = {randomNum, randomNum+1, randomNum+2};

        theDotCom.setLocationCells(locations);

        boolean isAlive = true;

        while (isAlive == true) {

            String guess = gameHelper.getUserInput("enter a number");

            String result = theDotCom.checkYourself(guess);

            numOfGuesses++;

            if (result == "kill") {
                System.out.println(numOfGuesses);
                isAlive = false;
            }

        }




    }

}