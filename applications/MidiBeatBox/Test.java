
import java.awt.event.*;


public class Test {

    public static void main(String [] args) {

        Test test = new Test();
        
        test.testReturn();
        
        TestClassTwoOneThree test2 = new TestClassTwoOneThree();
        test2.testIntMethodQuad();
        

    }

    public void testMethod() {

        String testString = "I am a test string";
        int testNubmer = 12;

    }

    public int testReturn() {

        int returnNumber = 45;
        return returnNumber;

    }

    public class testListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {

            testReturn();

        }
    }
}

class TestClassTwoOneThree {

    int testIntTwo = 230;
    int testIntThree = testIntTwo * 4;

    public void testIntMethodQuad() {

        System.out.println("TestInputEngagedThisLineSuperTestInputEngaged");
        
    }

}
