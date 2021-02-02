import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TwoButtons  {

    JFrame frame;
    JLabel label;
    MyDrawPanel drawPanel;

    public static void main(String[] args) {
        
        TwoButtons gui = new TwoButtons();
        gui.go();

    }

    public void go()  {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change Label!");
        labelButton.addActionListener(new LabelListener());

        JButton colorButton = new JButton("Change color!");
        colorButton.addActionListener(new ColorListener());

        label = new JLabel("Im a goddamn label!");
        drawPanel = new MyDrawPanel();

        Container pane = frame.getContentPane();

        pane.add(BorderLayout.SOUTH, colorButton);
        pane.add(BorderLayout.CENTER, drawPanel);
        pane.add(BorderLayout.EAST, labelButton);
        pane.add(BorderLayout.WEST, label);

        frame.setSize(600,600);
        frame.setVisible(true);

    }

    class LabelListener implements ActionListener  {

        public void actionPerformed(ActionEvent event) {

            label.setText(String.valueOf(drawPanel.red));

        }
    }

    class ColorListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            frame.repaint();

        }
    }

    // End outer

}