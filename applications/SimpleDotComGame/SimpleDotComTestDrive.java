public class SimpleDotComTestDrive {

    public static void main(String[] args) {

        SimpleDotCom dot = new SimpleDotCom();

        int[] locations = { 2, 3, 4 };
        dot.setLocationCells(locations);

        // Test 1

        String guess = "2";
        String check = dot.checkYourself(guess);
        String result = "Test 1 failed";

        if (check.equals("hit")) {

            result = "Test 1 passed (hit)";

        }

        System.out.println(result);

        // Test 2

        guess = "7";
        check = dot.checkYourself(guess);
        result = "Test 2 failed";

        if (check.equals("miss")) {

            result = "Test 2 passed (miss)";

        }

        System.out.println(result);

        // Test 3

        dot.numOfHits = 2;

        guess = "3";
        check = dot.checkYourself(guess);
        result = "Test 3 failed";

        if (check.equals("kill")) {

            result = "Test 3 passed (kill)";

        }

        System.out.println(result);

    }

}