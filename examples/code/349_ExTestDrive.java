import static java.lang.System.out;

class MyEx extends Exception {}

class ExTestDrive {

    public static void main(String[] args) {
        String test = args[0];
        out.print("t");

        try {

            doRisky(test);
            out.print("ro");

        } catch (MyEx e) {

            out.print("a");

        } finally {

            out.print("ws");

        }

    }

    static void doRisky(String t) throws MyEx {

        out.print("h");

        if ("yes".equals(t)) {
            throw new MyEx();
        }

    }

}