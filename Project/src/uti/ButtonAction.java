package uti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonAction implements ActionListener {
	private MainPanel panel;

	public ButtonAction(MainPanel main) {
		this.panel = main;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the object that fires the event
		JButton btn = (JButton)e.getSource();		
		
		// Setting the name of the button, according to the needed action
		switch (btn.getName()) {
			case "check":
				btn.setText("Следващ въпрос");
				btn.setName("next");
				panel.checkAnswer();
				break;
			case "next":
				btn.setText("Провери");
				btn.setName("check");
				panel.generateNextQuestion();
				break;
			default: 
				break;
		}
	}
	
}
