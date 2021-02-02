import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class MiniMusicPlayer3 {

    static JFrame f = new JFrame("My first Music Video");
    static MyDrawPanel ml;

    public static void main(String[] args) {

        MiniMusicPlayer3 player = new MiniMusicPlayer3();
        player.playerStart();

    }

    public void setupGUI() {

        ml = new MyDrawPanel();
        f.setContentPane(ml);
        f.setBounds(30, 30, 300, 300);
        f.setVisible(true);

    }

    public void playerStart() {

        setupGUI();

        try {

            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsWanted = { 127 };
            sequencer.addControllerEventListener(ml, eventsWanted);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 5; i < 61; i += 4) {

                track.add(makeEvent(176, 1, 127, 1, i)); // Controller
                track.add(makeEvent(144, 1, i, 100, i)); // Noteon
                track.add(makeEvent(128, 1, i, 100, i + 4)); // Noteoff

            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(200);
            sequencer.start();

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {

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

    class MyDrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

        public void controlChange(ShortMessage event) {

            msg = true;
            repaint();

        }

        public void paintComponent(Graphics g) {

            if (msg) {

                Graphics2D g2 = (Graphics2D) g;

                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);

                g.setColor(new Color(r, gr, b));

                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g.fillRect(x, y, ht, width);
                msg = false;

            }
        }

    }

}