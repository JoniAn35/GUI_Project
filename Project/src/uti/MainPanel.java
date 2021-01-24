package uti;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel questionPanel = new JPanel();
	private JLabel imageHolder = new JLabel();
	
	private JLabel question = new JLabel();
	private JLabel result = new JLabel();
	private JButton submit = new JButton();
	private JPanel bottomPanel = new JPanel();
	
	// creating two final variables for the background colors when the user checks his/hers answers
	private final Color success = new Color(0xB8, 0xF2, 0x7E); // green
	private final Color error = new Color(0xE8, 0x8B, 0x8B); // red
	private final int amount = 25; // the number of the questions
	private ArrayList<Integer> questionOrder = new ArrayList<>();
	private int curretnQuestion = 0;
	
	// creating a list of type JCheckBox to handle the answer checking
	private ArrayList<JCheckBox> list = new ArrayList<>();
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = DriversLicenseTest.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		return null;
		
	}
	
	private void fillTheRightPanel() {
		// before start "filling" the panel with the new question, the check boxes from it must be deleted
		while(!(list.isEmpty())) {
			rightPanel.remove(list.get(0));
			list.remove(0);
		}
		
		/*
		 * every q*.txt file has a template of how it should be made:
		 * line 1 is "въпрос:" - it must be skipped
		 * line 2 is the text of the question - it must be printed
		 * line 3 is empty - it must be skipped
		 * line 4 is "отговори:" - it must be skipped
		 * the next few lines (2/3/4) are whit the answers that must be checked - they must be prrinted
		 * 
		 * every a*.txt file has a template of how it should be made:
		 * line 1 is "орговор/и:" - it must be skipped
		 * line 2 are the correct answers, separated by a "," (if there are more than one correct option
		 */
		
		// the scanner takes the information from the .txt file line by line
		Scanner sc = new Scanner(DriversLicenseTest.class.getResourceAsStream("questions/q" + 
			questionOrder.get(curretnQuestion) + ".txt")); 
		sc.nextLine(); // skipping the second line
		
		String ques = sc.nextLine();
		sc.nextLine(); // skipping the third line
		sc.nextLine(); // skipping the fourth line
		
		question.setText(ques);
		// added additional panel for better design
		rightPanel.add(questionPanel);
		
		int i = 1;
		while (sc.hasNextLine()) {
			/*
			 * creating the check boxes, according to how many answers are in the question
			 * putting them in the ArrayList and then adding them to the panel
			 * and doing so until there are no more lines in the q*.txt file
			 */
			JCheckBox option = new JCheckBox(sc.nextLine());
			option.setOpaque(true);
			option.setName("a" + i);
			
			list.add(option);
			rightPanel.add(option);
			
			i++;
		}
		rightPanel.add(bottomPanel);
	}
	
	private void arrayFilling() {
		/*
		 * made ArrayList of type Integer for the order of the questions
		 * at first it is filled: 1,2,3,4,....
		 */
		questionOrder.clear();
		for (int i = 0; i < amount; i++) {
			questionOrder.add(i + 1);
		}
		
		// then the numbers are randomly shuffled
		Collections.shuffle(questionOrder);
		curretnQuestion = 0;
	}
	
	public MainPanel() {
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		imageHolder.setHorizontalAlignment(0); // 0 = CENTER
		imageHolder.setVerticalAlignment(0);
		leftPanel.add(imageHolder);
		
		this.add(leftPanel);
		this.add(rightPanel);
		
		submit.setText("Провери");
		submit.setName("check");
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		result.setOpaque(true);
		bottomPanel.add(result);
		bottomPanel.add(submit);
		
		// added new class to handle button click
		submit.addActionListener(new ButtonAction(this));
		
		questionPanel.add(question);
		
		arrayFilling();
		
	}
	
	public void generateNextQuestion () {
		// if the current question number is 25 or above the ArrayList must be filled again
		if (curretnQuestion >= amount) {
			arrayFilling();
		}

		// the result label should be empty until the user clicks "Провери"
		result.setText("");
		
		String picturePath = "images/p" + questionOrder.get(curretnQuestion) + ".jpg";
		imageHolder.setIcon(createImageIcon(picturePath));

		fillTheRightPanel();
	}
	
	public void checkAnswer() {
		// taking the information from the a*.txt file just like with the q*.txt
		Scanner sc = new Scanner(DriversLicenseTest.class.getResourceAsStream("answers/a" + 
				questionOrder.get(curretnQuestion) + ".txt")); 
		sc.nextLine();
		
		String trueAnswer = sc.nextLine();
		// making an ArrayList from the correct answers using the "," as the separator
		// for example: 1,3 -> 1 on position 0 and 3 on position 1
		ArrayList<String> answers = new ArrayList<>(Arrays.asList(trueAnswer.split(",")));
		
		String answerGiven = "";
		for (JCheckBox box : list) {
			// when checking the answers the user shouldn't be able to check more check boxes
			box.setEnabled(false);
			
			if (box.isSelected()) {
				answerGiven += box.getName().charAt(1) + ",";
				
				// setting the background depending on whether the selected check box "exist" in the correct answers 
				if (answers.contains("" + box.getName().charAt(1))) {
					box.setBackground(success);
				}
				else {
					box.setBackground(error);
				}
			}
			else {
				// and if the check box is correct but not checked, making its color also green 
				if (answers.contains("" + box.getName().charAt(1))) {
					box.setBackground(success);
				}
			}
			
		}
		
		answerGiven = answerGiven.substring(0, answerGiven.length() - 1);

		// "filling" the result label, depending on whether the answers is correct or wrong
		if (trueAnswer.equals(answerGiven)) {
			result.setBackground(success);
			result.setText("Вярно");

		}
		else {
			result.setBackground(error);
			result.setText("Грешно");
		}
		
		curretnQuestion++;
	}
	
}
