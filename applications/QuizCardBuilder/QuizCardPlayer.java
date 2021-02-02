import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList <QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public static void main(String [] args) {

        QuizCardPlayer reader = new QuizCardPlayer();
        reader.setLook();
        reader.go();


    }

    public void setLook() { //off

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception e) {}
        
    } //on

    public void go() {

        // build and display the ui

        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new FontUIResource("sansserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);

        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);

    }

    class NextCardListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            if (isShowAnswer) {

                // check the is showanswer boolean flag
                // show the answer because theve seen the question

                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;

            } else {

                // show the next question

                if (currentCardIndex < cardList.size()) {

                    showNextCard();

                } else {

                    // there are no more cards

                    display.setText("That was last card");
                    nextButton.setEnabled(false);

                }

            }

            // if this is a questions, show the answer, otherwise show next
            // set a flag for whether were viewing a question or answer

        }
    }

    class OpenMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            JFileChooser fileOpen = new JFileChooser();
            int openDialog = fileOpen.showOpenDialog(frame);

            if (openDialog == JFileChooser.APPROVE_OPTION) {

                loadFile(fileOpen.getSelectedFile());

            }


            // bring up a file dialog box
            // let the user navigate to and choose a card set to open
        }
    }

    private void loadFile(File file) {

        cardList = new ArrayList <QuizCard>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {

                makeCard(line);

            }

            reader.close();

        } catch (Exception e) {

            System.out.println("Couldnt read the card file");
            // e.printStackTrace();

        }

        showNextCard();

        // must build an ArrayList of cards by reading them from a text file
        // called from the OpenMenuListener event handler, reads the file one line at a time
        // tells the makeCard() method to make a new card out of the line
        // (one line in the file holds both the question and answer, seperated by a "/")

    }

    private void makeCard(String lineToParse) {

        String [] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result [0], result [1]);
        cardList.add(card);
        System.out.println("made a card");

        // called by the loadFile method, takes a line from the text file
        // parses into 2 piecse - question and answer - and creates a new QuizCard
        // adds it to the ArrayList called CardList
    }

    private void showNextCard() {

        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;

    }

}
