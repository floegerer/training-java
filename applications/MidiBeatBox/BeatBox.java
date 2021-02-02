import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class BeatBox {

    JPanel mainPanel;
    ArrayList <JCheckBox> checkboxList; // Store checkboxes in an ArrayList
    Sequencer player;
    Sequence sequence;
    Track track;
    JFrame frame;

    String [] instrumentNames = {"Bass Drum", "Closed Hihat", "Open Hihat", "Acoustic Snare",
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
            "Cowbell", "Vibraslap", "Lowmid Tom", "High Agogo", "Open Hi Conga"};

    int [] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String [] args) {

        new BeatBox().buildGUI();

    }

    public void buildGUI() {

        // Set up the application GUI and add event listeners

        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkboxList = new ArrayList <JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton saveButton  = new JButton("Save File");
        saveButton.addActionListener(new MySendListener());
        buttonBox.add(saveButton);

        JButton loadButton  = new JButton("Load file");
        loadButton.addActionListener(new MyReadInListener());
        buttonBox.add(loadButton);

        Box nameBox = new Box(BoxLayout.Y_AXIS);

        for (int i = 0; i < 16; i++) {

            nameBox.add(new Label(instrumentNames [i]));

        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {

            // Make the checkboxes set them to false
            // Add them to the ArrayList and to the GUI panel

            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);

        }

        setUpMidi();

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);

    }

    public void setUpMidi() {

        // Set up the player, sequence and track
        // Set tempo
        try {

            player = MidiSystem.getSequencer();
            player.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            player.setTempoInBPM(120);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void buildTrackAndStart() {

        int [] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) { // 16 instruments

            trackList = new int[16];

            int key = instruments [i];

            for (int j = 0; j < 16; j++) { // 16 beats

                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i));

                if (jc.isSelected()) {

                    trackList [j] = key;

                } else {

                    trackList [j] = 0;

                }
            }

            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));

        }

        track.add(makeEvent(192, 9, 1, 0, 15));

        try {

            player.setSequence(sequence);
            player.setLoopCount(player.LOOP_CONTINUOUSLY);
            player.start();
            player.setTempoInBPM(120);


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void makeTracks(int [] list) {

        // Make events for one instrument at a time for all 16 beats
        // Either int # of the insrument that should play at i or zero if the beat is empty

        for (int i = 0; i < 16; i++) {

            int key = list [i];

            if (key != 0) {

                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));

            }
        }

    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {

        // Utility method for quickly making MIDI events

        MidiEvent event = null;

        try {

            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return event;

    }

    public class MySendListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {

            boolean [] checkboxState = new boolean[256];

            for (int i = 0; i < checkboxState.length; i++) {

                JCheckBox check = (JCheckBox) checkboxList.get(i);

                if (check.isSelected()) {

                    checkboxState [i] = true;

                }

            }

            try {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(frame);
                File file = fileChooser.getSelectedFile();

                FileOutputStream fileStream = new FileOutputStream(file);
                ObjectOutputStream os = new ObjectOutputStream(fileStream);
                os.writeObject(checkboxState);

            } catch (Exception e) {

                e.printStackTrace();

            }


        }
    }

    public class MyReadInListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {

            boolean [] checkboxState = null;

            try {

                
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(frame);
                File file = fileChooser.getSelectedFile();

                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream is = new ObjectInputStream(fileIn);
                checkboxState = (boolean []) is.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < checkboxState.length; i++) {

                JCheckBox check = (JCheckBox) checkboxList.get(i);

                if (checkboxState [i]) {

                    check.setSelected(true);

                } else {

                    check.setSelected(false);

                }
            }

            player.stop();
            buildTrackAndStart();

        }
    }

    public class MyStartListener implements ActionListener {

        // Listeners for the buttons

        public void actionPerformed(ActionEvent a) {

            buildTrackAndStart();

        }

    }

    public class MyStopListener implements ActionListener {

        // Listener for Stop button

        public void actionPerformed(ActionEvent a) {

            player.stop();

        }
    }

    public class MyUpTempoListener implements ActionListener {

        // Listener for the tempo button

        public void actionPerformed(ActionEvent a) {

            float tempoFactor = player.getTempoFactor();
            player.setTempoFactor((float) (tempoFactor * 1.03));

        }
    }

    public class MyDownTempoListener implements ActionListener {

        // Listener for the tempo button

        public void actionPerformed(ActionEvent a) {

            float tempoFactor = player.getTempoFactor();
            player.setTempoFactor((float) (tempoFactor * .97));

        }
    }

}

