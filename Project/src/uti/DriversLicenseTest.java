package uti;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class DriversLicenseTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Провери знанията си ...");
		
		// added new class to handle panel filling, question generating and answer checking
		MainPanel mainPanel = new MainPanel();				
		mainPanel.generateNextQuestion();					

		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.pack();
        frame.setVisible(true);
	}

}
