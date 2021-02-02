import java.awt.*;
import javax.swing.*;

class MyWindow {

    public static void main(String[] args) {

        MyWindow gui = new MyWindow();
        gui.go();

    }

    public void go() {

        MyDrawPane draw = new MyDrawPane();
        JFrame frame = new JFrame();
        Container panel = frame.getContentPane();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setVisible(true);

        panel.add(draw);

    }

    class MyDrawPane extends JPanel {

        public void paintComponent(Graphics g) {

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            Image image = new ImageIcon("./test.png").getImage();
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(70, 70, Color.BLUE, 150, 150, Color.ORANGE);

            g2d.setPaint(gradient);
            g2d.fillOval(0, 200, 100, 100);

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color start = new Color(red, green, blue);

            red = (int) (Math.random() * 255);
            green = (int) (Math.random() * 255);
            blue = (int) (Math.random() * 255);
            Color end = new Color(red, green, blue);

            gradient = new GradientPaint(70, 70, start, 150, 150, end);

            g2d.setPaint(gradient);
            g2d.fillOval(50, 50, this.getWidth(), this.getHeight());

        }
    }
}