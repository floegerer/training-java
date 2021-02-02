import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SimpleGui3C implements ActionListener {

    JFrame frame;

    public static void main(String[] args) {
        
        SimpleGui3C gui = new SimpleGui3C();
        gui.go();

    }

    public void go()  {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change colors!");
        button.addActionListener(this);

        MyDrawPanel drawPanel = new MyDrawPanel();
        Container pane = frame.getContentPane();

        pane.add(BorderLayout.SOUTH, button);
        pane.add(BorderLayout.CENTER, drawPanel);
        
        frame.setSize(300,300);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {

        frame.repaint();

    }
}

class MyDrawPanel extends JPanel {

    static Color start;
    static Color end;

    int red;

    public void paintComponent(Graphics g) {

        red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        start = new Color(red, green, blue);

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        end = new Color(red, green, blue);

        GradientPaint gradient = new GradientPaint(0, 0, start, this.getWidth(), this.getHeight(), end);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    }
}