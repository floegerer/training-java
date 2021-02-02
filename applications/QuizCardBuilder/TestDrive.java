import java.util.ArrayList;

public class TestDrive {

    String myvar = "Instance";

    public TestDrive() {

        myvar = "Shit";

    }

    public TestDrive(String name) {

        myvar = name;

    }

    public void go() {

        int iam = 23;
        String thisisme = "I am a string";

        for (int i = 0; i < 23; i++) {

            int make = i;

        }

        returnString();

    }

    public void go(String hell) {

        myvar = hell;
        returnString();
        System.out.println(thisFunc(hell));

    }

    public String returnString() {

        System.out.println(myvar);
        return myvar;

    }

    public static void main(String [] args) {

        TestDrive test = new TestDrive("Fuckhead Joe");
        test.go("Huuuuuuuh?");

    }

    public String thisFunc(String shitload) {

        String yeah = "";
        String [] myArray = {"One", "Two", "Three"};

        for (String entry : myArray) {

            yeah += entry + "\n";

        }

        return yeah;


    }

}