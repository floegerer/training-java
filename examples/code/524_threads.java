
class TestThreads {


    public static void main(String [] args) {

        ThreadOne t1 = new ThreadOne();
        Thread one = new Thread(t1);

        ThreadTwo t2 = new ThreadTwo();
        Thread two = new Thread(t2);

        one.start();
        two.start();

    }
}


class Accum {

    private static Accum a = new Accum(); //d
    private int counter = 0;
    
    private Accum() {} //f

    public static Accum getAccum() {

        return a;

    }

    public void updateCounter(int add) {

        counter += add;

    }

    public int getCount() {

        return counter;

    }


}


class ThreadOne implements Runnable {

    Accum a = Accum.getAccum();

    public void run() {

        for (int i = 0; i < 98; i++) {

            a.updateCounter(1000);

            try {

                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("one " + a.getCount());

    }

}


class ThreadTwo implements Runnable {
    
    Accum a = Accum.getAccum();

    public void run() {

        for (int i = 0; i < 99; i++) {

            a.updateCounter(1);
            
            try {

                Thread.sleep(50);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("two " + a.getCount());

    }

}

// one 98098
// two 98099
