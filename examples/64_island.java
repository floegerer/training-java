class TestArrays {

    public static void main(String[] args) {

        Output out = new Output();
        out.start();
        
        String [] islands = new String[4];

        islands[0] = "Bermuda";
        islands[1] = "Fiji";
        islands[2] = "Azores";
        islands[3] = "Cozumel";
        
        int [] index = new int[4];
        
        index[0] = 1;
        index[1] = 3;
        index[2] = 0;
        index[3] = 2;
        
        int y = 0;
        int ref;
        
        while (y < 4) {

            ref = index[y];

            y = y + 1;

            out.echoes("island[" + ref + "] = ");
            out.print(islands[ref]);
        }

        out.end();

    }
}


class Output {
    void start() {
        System.out.println("\n\n\n\n\n\n\n\n------------\n\n");
    }

    void end() {
        System.out.println("\n");
    }

    void print(String stringPrint) {
        System.out.println(stringPrint);
    }

    void echoes(String stringPrint) {
        System.out.print(stringPrint);
    }
}