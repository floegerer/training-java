import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleChatClientA {

    JTextField outgoing;
    PrintWriter writer;
    Socket sock;

    public void go() {

        // make gui and register a listener with the send button
        // cass the setUpNetworking() method

        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();
        frame.setSize(400, 500);
        frame.setVisible(true);

    }

    public void setUpNetworking() {

        // make a Socket, then make a PrintWriter
        // assign the PrintWriter to writer instance variable

        try {

            sock = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");

        } catch (Exception e) {
            /* off */ e.printStackTrace();
            /* on */}

    }

    public class SendButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            try {

                writer.println(outgoing.getText());
                writer.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }

            outgoing.setText("");
            outgoing.requestFocus();

            // get the text from the text field and
            // send it to the server using the writer (a PrintWriter)

        }

    }

    public static void main(String [] args) {

        new SimpleChatClientA().go();

    }
}
