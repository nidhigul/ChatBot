package chatbot.project.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Chat {
	Chat(){
		final JFrame frame= new JFrame("CHAT HERE"); 
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		JPanel panel=new JPanel();
		JButton button=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\buttton.JPG"));
		panel.setBackground(Color.gray);
		final JTextField questionField = new JTextField("Ask me anything...",100);
		final JTextArea display = new JTextArea();
		display.setRows(28);
		display.setColumns(100);
		display.setEditable(false);
		display.setLineWrap(true); 
		display.setFont(new Font("Monospaced",Font.PLAIN,15));
		questionField.setFont(new Font("Monospaced",Font.PLAIN,15));
		display.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		questionField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setBackground(Color.gray);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		display.append("Welcome, my name is blue and i will be your friend. let's get to know each other. \n");
		button.setBounds(600,640,150,58);
		frame.add(button); 
		panel.add(scrollPane);
		panel.add(questionField);
		frame.add(panel);
		frame.setVisible(true); 


		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String url = "jdbc:mysql://localhost:3306/chatbot";
					String username = "root";
					String password = "blueheart"; 
					Connection con=DriverManager.getConnection(  
							url,username,password);  
					String questions = questionField.getText();
					questionField.setText("");
					java.sql.Statement st = con.createStatement();
					String query = "SELECT answer FROM chats WHERE question = ?";
		            PreparedStatement preparedStatement = con.prepareStatement(query);
		            preparedStatement.setString(1, questions);
		            ResultSet rs = preparedStatement.executeQuery();
		            if(rs.next())
		                    {
		                    	String ans = rs.getString("answer");
								display.append("You -> "+questions + "\n"+"Blue -> "+ans+"\n\n");
		                    }
		                    else {
		                    	display.append("You - > "+questions +"\n"+"Blue -> I apologize for not knowing the answer to this, "
		                    			+ "however here is a source where you can find it.\n\n");
		                    	String googleUrl = "https://www.google.com?q="+URLEncoder.encode(questions,"UTF-8");	
		                    	try {
		                                Desktop desktop = Desktop.getDesktop();
		                                URI uri = new URI(googleUrl);
		                                desktop.browse(uri);
		                        } catch (Exception e) {
		                            e.printStackTrace();
		                        }
		                            }
		            st.close();
					con.close();
				}
				catch (Exception e) {
                    e.printStackTrace();                }
			}
		});
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
