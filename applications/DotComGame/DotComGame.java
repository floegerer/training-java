import java.util.*;

class DotComGame {
    
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {

        DotCom one = new DotCom();
        one.setName("Shithead.com");
        DotCom two = new DotCom();
        two.setName("Fuck.com");
        DotCom three = new DotCom();
        three.setName("Dickhead.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Your goal is to sink all the fucking dot coms.");
        
        for (DotCom dotComToSet : dotComsList) {

            ArrayList<String> newLocation = helper.placeDotCom(3);

            dotComToSet.setLocationCells(newLocation);
        }
        
    }

    private void startPlaying() {

        while(!dotComsList.isEmpty())
        {

            String userGuess = helper.getUserInput("enter a number");
            checkUserGuess(userGuess);

        }

        finishGame();

    }

    private void checkUserGuess(String userGuess) {

        numOfGuesses++;

        String result = "miss";

        for (DotCom dotComToTest : dotComsList) {

            result = dotComToTest.checkYourself(userGuess);

            if (result.equals("hit")) {
                
                break;
            }

            if (result.equals("kill")) {

                dotComsList.remove(dotComToTest);
                break;
            }
        }

        System.out.println(result);

    }

    private void finishGame() {

        System.out.println("All dot coms are dead. You took " + numOfGuesses);
    
    }

    public static void main(String[] args) {

        DotComGame game = new DotComGame();
        game.setUpGame();
        game.startPlaying();

    }

}