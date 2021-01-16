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
		JButton btn = (JButton)e.getSource();
		
		System.out.println(e.getActionCommand());
		
		switch (btn.getName()) {
			case "check":
				btn.setText("Следващ въпрос");
				btn.setName("next");
				panel.checkAnswer();
				panel.debugName();
				break;
			case "next":
				btn.setText("Провери");
				btn.setName("check");
				panel.debugName();
				panel.generateNextQuestion();
				break;
			default: 
				break;
		}
	}
	
}
