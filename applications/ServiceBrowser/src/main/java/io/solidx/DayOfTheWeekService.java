package io.solidx;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class DayOfTheWeekService implements Service {

    JLabel outputLabel;
    JComboBox month;
    JTextField day;
    JTextField year;
    
    public JPanel getGuiPanel() { 
        
        // The service interface method that builds the GUI

        JPanel panel = new JPanel();
        JButton button = new JButton("Do it!");
        button.addActionListener(new DoItListener());
        outputLabel = new JLabel("Data appears here");
        DateFormatSymbols dateStuff = new DateFormatSymbols();
        month = new JComboBox<>(dateStuff.getMonths());
        day = new JTextField(8);
        year = new JTextField(8);
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        
        inputPanel.add(new JLabel("Month"));
        inputPanel.add(month);
        inputPanel.add(new JLabel("Day"));
        inputPanel.add(day);
        inputPanel.add(new JLabel("Year"));
        inputPanel.add(year);

        panel.add(inputPanel);
        panel.add(button);
        panel.add(outputLabel);
        return panel;

    }

    public class DoItListener implements ActionListener  {

        public void actionPerformed(ActionEvent ev) {

            int monthNum = month.getSelectedIndex();
            int dayNum = Integer.parseInt(day.getText());
            int yearNum = Integer.parseInt(year.getText());
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, monthNum);
            c.set(Calendar.DAY_OF_MONTH, dayNum);
            c.set(Calendar.YEAR, yearNum);
            Date date = c.getTime();

            // Refer to chapter IO if you need a reminder of how number and date
            // formatting works. The code is slightly different however
            // because it uses the calender class. Also the SimpleDataFormat lets us specify a pattern of 
            // how the date should print out.

            String dayOfWeek = (new SimpleDateFormat("EEEE")).format(date);
            outputLabel.setText(dayOfWeek);
              
        }
    }
}
