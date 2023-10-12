package chatbot.project.java;

import javax.swing.*;
import java.awt.event.*;

public class FrontPage {
	public static void main(String[] args) {
		final JFrame frame= new JFrame("WELCOME"); 
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		JButton button=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\bot2.jpg"));
		button.setBounds(400,100,500, 500); 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				new Chat();
			}
		});
		frame.add(button);       
		frame.setVisible(true); 
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
}
}
