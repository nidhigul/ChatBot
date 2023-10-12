package chatbot.project.java;

import javax.swing.*;
import javax.swing.border.Border;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;


public class Chat {
	Chat(){
		final JFrame frame= new JFrame("CHAT HERE"); 
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		final ImageIcon imageIcon = new ImageIcon("C:\\\\Users\\\\rama bebs\\\\Downloads\\\\Java Module\\\\bot.jpg");
		JPanel panel=new JPanel(){
		      Image image = imageIcon.getImage();
		      {
		        setOpaque(false); 
		      }
		      public void paint(Graphics g) {
		    	int x = (this.getWidth() - image.getWidth(null))/2;
		    	int y = (this.getHeight() - image.getHeight(null))/2;
		        g.drawImage(image, x, y, this);
		        super.paint(g);
		      }
		    }; 
		JButton button=new JButton(new ImageIcon("C:\\Users\\rama bebs\\Downloads\\Java Module\\buttton.JPG"));
		panel.setBackground(Color.getHSBColor(135, 206, 250));
		final JTextField questionField = new JTextField("Ask me anything...",100);
		Border border = BorderFactory.createLineBorder(Color.black);
		questionField.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		final JTextArea display = new JTextArea();
		display.setOpaque(false);
		questionField.setOpaque(false);
		display.setRows(30);
		display.setColumns(100);
		display.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		display.setEditable(false);
		display.setFont(new Font("Monospaced",Font.PLAIN,15));
		display.setText("Welcome, my name is blue and i will be your friend. let's get to know each other");
		button.setBounds(1200,580,150,58);
		frame.add(button); 
		panel.add(display);
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
					java.sql.Statement st = con.createStatement();
					String query = "SELECT answer FROM chats WHERE question = ?";
		            PreparedStatement preparedStatement = con.prepareStatement(query);
		            preparedStatement.setString(1, questions);
		            ResultSet rs = preparedStatement.executeQuery();
		            if(rs.next())
		                    {
		                    	String ans = rs.getString("answer");
								display.setText("User -> "+questions + "\n"+"Blue -> "+ans);
		                    }
		                    else {
		                    	display.setText("I apologize for not knowing the answer to this, "
		                    			+ "however here is a source where you can find it.\r\n");
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
