package uti;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DriversLicenseTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Провери знанията си ...");
		MainPanel mainPanel = new MainPanel();				// added new class to handle panel filling, question  
		mainPanel.generateNextQuestion();					// generating and answer checking

		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.pack();
        frame.setVisible(true);
	}

}
