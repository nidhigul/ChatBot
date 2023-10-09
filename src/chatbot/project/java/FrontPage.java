package chatbot.project.java;

import javax.swing.*;
import java.awt.event.*;

public class FrontPage {
	public static void main(String[] args) {
		final JFrame f= new JFrame("WELCOME"); 
		f.setExtendedState(f.MAXIMIZED_BOTH);
		JButton b=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\bot.jpg"));
		b.setBounds(400,100,500, 500); 
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				new Chat();
			}
		});
		f.add(b);       
		f.setVisible(true); 
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
}
}
