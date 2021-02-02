import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static java.lang.System.*;

class MyAnimation {

    MyPanel panel;
    MyFrame frame;
    MyListener listen;

    public static void main(String[] args) {

        MyAnimation an = new MyAnimation();
        an.setFrame();

    }

    class MyListener extends MouseAdapter {

        public void mouseEntered(MouseEvent e) { startThread(); }

        public void mouseExited(MouseEvent e) { startThread(); }

        public void startThread() {

            Thread myThread = new Thread(panel);
            myThread.start();

        }

    }

    class MyFrame extends JFrame {

        public void setPanel() {

            Container content = frame.getContentPane();
            panel = new MyPanel();
            content.add(panel);
            frame.validate();
            panel.animate();
            out.println("setPanel()");

        }

        public void removePanel() {

            Container content = frame.getContentPane();
            content.remove(panel);
            out.println("removePanel()");

        }

    }

    class MyPanel extends JPanel implements Runnable {

        int x = 0;
        int y = 0;

        public void paintComponent(Graphics g) {

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // TODO Add object and walk though object with different states

            for (int i = 0; i < 1000; i++) {

                x = (int) (i);

                int red = (int) (Math.random() * 255);
                int green = (int) (Math.random() * 255);
                int blue = (int) (Math.random() * 255);

                Color start = new Color(red, green, blue);
    
                red = (int) (Math.random() * 255);
                green = (int) (Math.random() * 255);
                blue = (int) (Math.random() * 255);

                Color end = new Color(red, green, blue);
    
                GradientPaint gradient = new GradientPaint(70, 70, start, 150, 150, end);
                Graphics2D g2 = (Graphics2D) g;

                g2.setPaint(gradient);
                g2.fillOval(x, y, 50, 50);

            }

        }

        public void animate() {

            for (int i = 0; i < frame.getWidth(); i++) {

                y = (int) (i * i / 30);

                frame.repaint();

                try {

                    Thread.sleep(1000 / 60);

                } catch (Exception ex) {
                }

            }

            out.println("Animation() Done");

        }

        public void run() {

            frame.removePanel();
            frame.setPanel();

        }

    }

    public void setFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int winSize = 400;
        int winPosx = (int) screenSize.getWidth() - winSize;
        int winPosy = (int) screenSize.getHeight() - winSize;

        frame = new MyFrame();
        frame.setVisible(true);
        frame.setSize(winSize, winSize);
        frame.setLocation(winPosx, winPosy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(new MyListener());
        frame.setPanel();

    }

}
