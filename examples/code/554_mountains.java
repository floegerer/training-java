import java.util.*;


class SortMountains {

    LinkedList <Mountain> mtn = new LinkedList <Mountain>();

    class NameCompare implements Comparator <Mountain> {

        public int compare(Mountain one, Mountain two) {

            return one.getName().compareTo(two.getName());

        }

    }

    class HeightCompare implements Comparator <Mountain> {

        public int compare(Mountain one, Mountain two) {

            return two.getHeight().compareTo(one.getHeight());

        }


    }

    public static void main(String [] args) {

        new SortMountains().go();

    }

    public void go() {

        mtn.add(new Mountain("Longs", 14255));
        mtn.add(new Mountain("Elbert", 14433));
        mtn.add(new Mountain("Maroon", 14156));
        mtn.add(new Mountain("Castle", 14265));

        System.out.println("as entered:\n" + mtn);

        NameCompare nc = new NameCompare();
        Collections.sort(mtn, nc);
        System.out.println("by name: \n" + mtn);

        HeightCompare hc = new HeightCompare();
        Collections.sort(mtn, hc);
        System.out.println("by height: \n" + mtn);

    }

    class Mountain {

        String name = "Mountain";
        Integer height = 4000;

        Mountain(String n, Integer h) {

            name = n;
            height = h;

        }

        public String getName() {

            return name;

        }

        public Integer getHeight() {

            return height;

        }

        public String toString() {

            return name + " " + height.toString();

        }

    }
}
