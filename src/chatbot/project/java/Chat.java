package chatbot.project.java;

import javax.swing.*;
import javax.swing.border.Border;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Chat {
	Chat(){
		//GUI
		final JFrame f1= new JFrame("CHAT HERE"); 
		f1.setExtendedState(f1.MAXIMIZED_BOTH);
		JPanel panel=new JPanel(); 
		JButton b=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\buttton.JPG"));
		panel.setBackground(Color.GRAY);
		final JTextField ta = new JTextField("Ask me anything...",100);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    ta.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		final JTextArea disp = new JTextArea();
		disp.setRows(30);
		disp.setColumns(100);
		disp.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		disp.setEditable(false);
		b.setBounds(1200,580,150,58);
		f1.add(b); 
		panel.add(disp);
		panel.add(ta);
		f1.add(panel);
		f1.setVisible(true); 

		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String question = ta.getText();
				try {
					String url = "jdbc:mysql://localhost:3306/chatbot";
					String username = "root";
					String password = "blueheart";
					
					Class.forName("com.mysql.jdbc.Driver"); 
					Connection con=DriverManager.getConnection(  
							url,username,password);  
					
					java.sql.Statement st = con.createStatement();
		            String Sql = "Select * from chats";
		           ResultSet rs = st.executeQuery(Sql);
		            
					while(rs.next()) {
						String question = ta.getText();
		                    String ques = rs.getString("question");
		                    if(ques.trim().equalsIgnoreCase(question.trim()))
		                    {
		                    	String ans = rs.getString("answer");
								disp.setText("User -> "+question + "\n");
								disp.setText("\n"+"Blue -> "+ans);
		                    }
		                    else {
		                    	disp.setText("Blue - > Sorry, i don't know answer to this question");
		                    }
					}
					st.close();
					con.close();
				}
				catch (Exception e) {
                    e.printStackTrace();                }
			}
			
		});
		f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
