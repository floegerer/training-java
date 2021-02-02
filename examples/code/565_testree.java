import java.util.*;

class TestTree {

    public static void main(String[] args) {

        new TestTree().go();

    }

    public void go() { 

        Book b1 = new Book("How Cats Work");
        Book b3 = new Book("Finding Emo");
        Book b2 = new Book("Remix your Body");
        BookCompare bookCompare  = new BookCompare();

        TreeSet <Book> tree = new TreeSet<Book>(bookCompare);
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);

        System.out.println(tree);
        
    }
}

class Book implements Comparable { 

    String title;
    Integer id;
    static Integer index = 0;
    
    public Book(String t) {

        title = t;
        index++;
        id = index;

    }

    public int compareTo(Object b) {

        Book book  = (Book) b;
        return (title.compareTo(book.title));

    }

    public String toString() {

        return id + " " + title;

    }
}

class BookCompare implements Comparator<Book> {

    public int compare(Book one, Book two) {

        return (one.id.compareTo(two.id));

    }
}