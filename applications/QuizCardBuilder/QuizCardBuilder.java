import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList <QuizCard> cardList;
    private JFrame frame;

    public static void main(String [] args) {

        QuizCardBuilder builder = new QuizCardBuilder();
        builder.setLook();
        builder.go();

    }

    public void setLook() { //off

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception e) {}
        
    } //on

    public void go() {

        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);;
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList <QuizCard>();

        JLabel qJLabel = new JLabel("Question");
        JLabel aLabel = new JLabel("Answer");

        mainPanel.add(qJLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());;

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());

        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);

        // build display gui
    }

    private class NextCardListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();

            // add the current card to the list and clear the text areas

        }

    }

    private class SaveMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());

            // bring up a dialog box
            // let the user name and save the set

        }
    }

    private class NewMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            cardList.clear();
            clearCard();

            // clear out the card list and clear the text areas

        }
    }

    private void clearCard() {

        question.setText("");
        answer.setText("");
        question.requestFocus();

    }

    private void saveFile(File file) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (QuizCard card : cardList) {

                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");

            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Could not write the cardList out");
            e.printStackTrace();

        }

        // iterate through the list of cards and write each one to a text file
        // in parseable way (in other words with clear seperations between parts)
    }
}
