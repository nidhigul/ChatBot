package chatbot.project.java;

import javax.swing.*;
import javax.swing.border.Border;
import com.mysql.cj.xdevapi.Statement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Chat {
	Chat(){
		// Creat einterface.
		final JFrame f1= new JFrame("CHAT HERE"); 
		f1.setExtendedState(f1.MAXIMIZED_BOTH);
		JPanel panel=new JPanel(); 
		JButton b=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\buttton.JPG"));
		panel.setBackground(Color.GRAY);
		final JTextField ta = new JTextField("Ask me anything...",100);
		Border border = BorderFactory.createLineBorder(Color.black);
	    ta.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		final ImageIcon imageIcon = new ImageIcon("C:\\\\Users\\\\rama bebs\\\\Downloads\\\\Java Module\\\\bot.jpg");
		JTextArea disp = new JTextArea(){
		      Image image = imageIcon.getImage();
		      {
		        setOpaque(false);
		      }

		      public void paint(Graphics g) {
		        g.drawImage(image, 0, 0, this);
		        super.paint(g);
		      }
		    };
		disp.setForeground(Color.white);
		disp.setRows(30);
		disp.setColumns(100);
		disp.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		disp.setEditable(false);
		disp.setFont(new Font("Monospaced",Font.PLAIN,15));
		disp.setText("Welcome, my name is blue and i will be your friend. let's get to know each other");
		b.setBounds(1200,580,150,58);
		f1.add(b); 
		panel.add(disp);
		panel.add(ta);
		f1.add(panel);
		f1.setVisible(true); 

		//To establish a connection with MySql using jdbc
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String url = "jdbc:mysql://localhost:3306/chatbot";
					String username = "root";
					String password = "blueheart";

					Class.forName("com.mysql.jdbc.Driver"); 
					Connection con=DriverManager.getConnection(  
							url,username,password);  
					
					java.sql.Statement st = con.createStatement();
		            String sql = "Select * from chats";
		           
		           ResultSet rs = st.executeQuery(sql);
		           
		           while(rs.next()) {
		        	   String questions = ta.getText();
		                    String ques = rs.getString("question");
		                    if(ques.trim().equals(questions.trim()))
		                    {
		                    	String ans = rs.getString("answer");
								disp.setText("User -> "+questions + "\n"+"Blue -> "+ans);
		                    }
					    // To call google in case the question is not in the database
		                    else {
		                    	disp.setText("Sorry, I don't know the answer to this question, "
		                    			+ "but here's something from where you can get it");
		                    	String googleUrl = "https://www.google.com";
		                    	try {
		                                Desktop desktop = Desktop.getDesktop();
		                                URI uri = new URI(googleUrl);
		                                desktop.browse(uri);
		                                break;
		                        } catch (Exception e) {
		                            e.printStackTrace();
		                        }
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
