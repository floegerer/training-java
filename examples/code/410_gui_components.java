import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import static java.lang.System.*;

class MyGUI {

    static MyGUI gui;

    JButton button;
    JTextField field;
    JTextArea textarea;
    JScrollPane scrollpane;
    JCheckBox checkbox;
    JList list;

    public static void main(String [] args) {

        gui = new MyGUI();
        gui.setLook();
        gui.start();

    }

    public void setLook() { //off

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception e) {}
        
    } //on

    public void start() {


        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel textpanel = new JPanel();
        JButton button2 = new JButton("Load");

        Listen listen = new Listen();
        Container content = frame.getContentPane();
        Insets margin = new InsetsUIResource(8, 16, 8, 16);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        String [] listEntries = {"Disabled", "Active", "Weird", "More", "And more"};

        int pad = 8;
        int winSize = 400;
        int winPosx = (int) screenSize.getWidth() - winSize;
        int winPosy = (int) screenSize.getHeight() - winSize;

        list = new JList <String>(listEntries);
        textarea = new JTextArea(20, 10);
        field = new JTextField("Input text and copy");
        button = new JButton("Save");
        scrollpane = new JScrollPane(textarea);
        checkbox = new JCheckBox("Do it!");

        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        frame.setSize(winSize, winSize);
        frame.setLocation(winPosx, winPosy);
        frame.setVisible(true);
        frame.setMenuBar(new MenuBar());
        frame.getMenuBar().add(new Menu("File"));
        frame.getMenuBar().add(new Menu("Edit"));

        content.add(BorderLayout.SOUTH, panel);
        content.add(BorderLayout.CENTER, textpanel);

        // panel.setBackground(new ColorUIResource(230, 230, 230));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(pad, pad, pad, pad));
        panel.add(button);
        panel.add(button2);
        panel.add(checkbox);

        list.setVisibleRowCount(4);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        textpanel.setLayout(new BoxLayout(textpanel, BoxLayout.Y_AXIS));
        textpanel.setBorder(new EmptyBorder(pad, pad, pad, pad));
        textpanel.add(field);
        textpanel.add(list);
        textpanel.add(scrollpane); //off
        
        field.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new ColorUIResource(150, 150, 150)), new EmptyBorder(pad, pad, pad, pad)));
        scrollpane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(pad * 2, 0, 0, 0), new LineBorder(new ColorUIResource(150, 150, 150))));

        button.setMargin(margin); //on
        button2.setMargin(margin);

        field.addKeyListener(listen);
        button.addActionListener(listen);
        checkbox.addItemListener(listen);
        list.addListSelectionListener(listen);

        field.requestFocus();

    }

    class Listen implements ActionListener, KeyListener, ItemListener, ListSelectionListener { //off

        public void run() {

            System.out.println("Event");
            textarea.append("\n" + field.getText());
            field.setText("");

        }

        public void keyPressed(KeyEvent e) {

            Integer keyCode = e.getKeyCode();
            Boolean checked = checkbox.isSelected();
            boolean liststate = list.getSelectedValuesList().indexOf("Active") >= 0;
            
            if (keyCode == 10 && (checked || liststate)) { run(); }
            if (keyCode == 10 && !checked && !liststate) { textarea.append("\nCheckbox disabled!"); }
            
            out.println(keyCode.toString());

        }

        public void itemStateChanged(ItemEvent e) {

            textarea.append("\nCheckbox state changed!");

        }
        
        public void valueChanged(ListSelectionEvent e) {

            if (!e.getValueIsAdjusting()) {

                textarea.append("\nList state changed: " + list.getSelectedValuesList());

            }

        }

        public void keyTyped(KeyEvent e) { }
        public void actionPerformed(ActionEvent e) { run(); }
        public void keyReleased(KeyEvent e) { }


    } //on
}
