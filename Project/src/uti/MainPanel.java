package uti;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JLabel imageHolder = new JLabel();
	
	private JLabel question = new JLabel();
	private JLabel result = new JLabel();
	private JButton submit = new JButton();
	private JPanel bottomPanel = new JPanel();
	
	private int questionNumber;
	private final Color success = new Color(0xB8, 0xF2, 0x7E);
	private final Color error = new Color(0xE8, 0x8B, 0x8B);
	
	private ArrayList<JCheckBox> list = new ArrayList<JCheckBox>();
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = DriversLicenseTest.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.out.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	private void fillTheRightPanel() {
		while(!(list.isEmpty())) {
			rightPanel.remove(list.get(0));
			list.remove(0);
		}
		
		Scanner sc = new Scanner(DriversLicenseTest.class.getResourceAsStream("questions/q" + questionNumber + ".txt")); 
		sc.nextLine();
		
		String ques = sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		
		System.out.println(ques);;
		
		question.setText(ques);

		rightPanel.add(question);
		
		
		int i = 1;
		while (sc.hasNextLine()) {
			JCheckBox option = new JCheckBox(sc.nextLine());
			option.setOpaque(true);
			option.setName("a" + i);
			
			list.add(option);
			
			rightPanel.add(option);
			i++;
		}
		
		rightPanel.add(bottomPanel);
		
	 }
	
	public MainPanel() {
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		imageHolder.setHorizontalAlignment(JLabel.CENTER);
		imageHolder.setVerticalAlignment(JLabel.CENTER);
		leftPanel.add(imageHolder);
		
		this.add(leftPanel);
		this.add(rightPanel);
		
		submit.setText("Провери");
		submit.setName("check");
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		result.setOpaque(true);
		bottomPanel.add(result);
		bottomPanel.add(submit);
		submit.addActionListener(new ButtonAction(this));		// added new class to handle button click
		
	}
	
	public void debugName () {
		System.out.println(submit.getName());
	}
	
	public void generateNextQuestion () {
		result.setText("");
		
		Random rand = new Random();
		questionNumber = rand.nextInt(5) + 1;
		
		System.out.println(questionNumber);
		
		String picturePath = "images/p" + questionNumber + ".jpg";
		imageHolder.setIcon(createImageIcon(picturePath));

		fillTheRightPanel();
	}
	
	public void checkAnswer() {
		Scanner sc = new Scanner(DriversLicenseTest.class.getResourceAsStream("answers/a" + questionNumber + ".txt")); 
		sc.nextLine();
		
		String trueAnswer = sc.nextLine();
		ArrayList<String> answers = new ArrayList<String>(Arrays.asList(trueAnswer.split(",")));
		
		String answerGiven= "";
		for (JCheckBox box : list) {
			box.setEnabled(false);
			
			if (box.isSelected()) {
				answerGiven += box.getName().charAt(1) + ",";
				
				if (answers.contains("" + box.getName().charAt(1))) {
					box.setBackground(success);
				}
				else {
					box.setBackground(error);
				}
			}
			else {
				if (answers.contains("" + box.getName().charAt(1))) {
					box.setBackground(success);
				}
			}
			
		}
		answerGiven = answerGiven.substring(0, answerGiven.length() - 1);

		if (trueAnswer.equals(answerGiven)) {
			result.setBackground(success);
			result.setText("Вярно");

		}
		else {
			result.setBackground(error);
			result.setText("Грешно");
		}
		
	}
	
}
