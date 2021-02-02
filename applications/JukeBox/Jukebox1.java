import java.util.*;
import java.io.*;

public class Jukebox1 {

    ArrayList<Song> songList = new ArrayList<Song>();

    public static void main(String[] args) {

        new Jukebox1().go();
        
    }

    class ArtistCompare implements Comparator<Song> {

        public int compare(Song one, Song two) {

            return one.getArtist().compareTo(two.getArtist());

        }
    }

    public void go() { 

        getSongs();

        ArtistCompare artistCompare  = new ArtistCompare();
        Collections.sort(songList, artistCompare);
        System.out.println(songList);

    }

    void getSongs() { 

        try {

            File file = new File("Songlist.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {

                addSong(line);
                
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    void addSong(String lineToParse) { 

        String [] tokens = lineToParse.split("/");

        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);

    }
}