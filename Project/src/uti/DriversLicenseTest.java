package uti;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DriversLicenseTest {
	/**
	 * кое къде:
	 * 	картинка -> ляво и в средата			-> поле
	 * 	въпрос 	-> дясно и отгоре				-> текстово поле
	 * 	отговори -> дясно и под въпроса 		-> check box
	 * 	бутон 	-> дясно и под отговорите		-> бутон
	 */
	public static void main(String[] args) {
//		Random rand = new Random();
//		int questionNumber = rand.nextInt(30);
		
		JFrame frame = new JFrame("Test your knowledge");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		fillTheRightPanel(rightPanel);
		ImageIcon image = createImageIcon("images/p1.jpg");
		JLabel label = new JLabel(image, JLabel.CENTER);
		leftPanel.add(label);
		
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		// Display the window.
		frame.pack();
        frame.setVisible(true);
	}
	
	public static void checkBoxMaking (JPanel pane) {
		int answers = 4; 	// check how many are the answers of the question
		while (answers > 0) {
			JCheckBox option = new JCheckBox("*text of the answer*"); 
			// position...
			pane.add(option);
			answers --;
		}
	}
	
	public static void fillTheRightPanel(JPanel pane) {
		String ques = "Question";	// get the text from the file
		JLabel question = new JLabel(ques);
		JButton submit = new JButton("Submit");
		JLabel result = new JLabel("True/False");
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		bottomPanel.add(result);
		bottomPanel.add(submit);
		
		pane.add(question);
		checkBoxMaking(pane);
		pane.add(bottomPanel);
	}
	
	 protected static ImageIcon createImageIcon(String path) {
	       java.net.URL imgURL = DriversLicenseTest.class.getResource(path);
	       if (imgURL != null) {
	           return new ImageIcon(imgURL);
	       } else {
	           System.err.println("Couldn't find file: " + path);
	           return null;
	       }
	   }
}
