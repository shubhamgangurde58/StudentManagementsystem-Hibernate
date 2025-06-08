package com.shubham.StudentManagementSystem;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.shubham.StudentManagementSystem.Entity.UserInfo;


public class LoginPage {
	
	UserInfo userInfo = new UserInfo();
	
	JFrame f;
	JPanel p;
	
	JLabel lblHeading,lblMessage,lblUserName,lblPassword, lblSignUpPage;
	JTextField txtUserName,txtPassword;
	JButton btnLogin ,btnCancel;
	
	public  LoginPage() {
		
				f = new JFrame();
				f.setTitle("Login Page Frame");
				f.setLocation(400, 50);
				f.setSize(500, 600);
				
				p = new JPanel();
				p.setLayout(null);
				f.add(p);
				
				 lblHeading = new JLabel();
				 lblMessage = new JLabel();
				 lblUserName = new JLabel();
				 lblPassword = new JLabel();
				 
				 lblSignUpPage = new JLabel();
				 txtUserName = new JTextField();
				 txtPassword = new JTextField();
				 
			
				 btnLogin = new JButton();
				 btnCancel = new JButton();
				
				lblHeading.setText("!! Welcome To Student Management System !!");
				lblHeading.setBounds(100, 10 ,300, 30);
				p.add(lblHeading);
		
				lblMessage.setText(" Login Page ");
				lblMessage.setBounds(190, 45 ,200, 30);
				p.add(lblMessage);
				
				lblUserName.setText("UserName :");
				lblUserName.setBounds(100, 100, 100, 30);
				p.add(lblUserName);
					
				lblPassword.setText("Password :");
				lblPassword.setBounds(100,180,100, 30);
				p.add(lblPassword);	
				
				txtUserName.setBounds(210, 100, 150, 30);
				p.add(txtUserName);
				
				txtPassword.setBounds(210, 180, 150, 30);
				p.add(txtPassword);
				
				lblSignUpPage.setText("Create Your Account!");
				lblSignUpPage.setBounds(100, 250 ,200, 30);
				p.add(lblSignUpPage);			
				
				btnLogin.setBounds(100, 300, 100, 30);
				btnLogin.setText("login");
				p.add(btnLogin);
				
				btnCancel.setBounds(260, 300, 100, 30);
				btnCancel.setText("Cancel");
				p.add(btnCancel);
				
				f.setVisible(true);		
				
				btnLogin.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						 String userName = txtUserName.getText().trim();
			             String password = txtPassword.getText().trim();
						
						if(userName.isEmpty()) {
							
							JOptionPane.showMessageDialog(f,"UserName is Required !!");
							txtUserName.requestFocus();
							
						}else if (password.isEmpty()) {
						
							JOptionPane.showMessageDialog(f,"Password is Required !!");
							txtPassword.requestFocus();
							
						}else {
				
						
								Session session = HibernateConn.getSessionFactoryInstance().openSession();
								String hql = "FROM  UserInfo WHERE userName = :userName AND password = :password ";
								
								Query<UserInfo> query = 	session.createQuery(hql,UserInfo.class);
								
								query.setParameter("userName",userName);
								query.setParameter("password", password);
								
								List<UserInfo> userInfolList = query.getResultList();
								
								session.close();
								
								if(!userInfolList.isEmpty()) {
									
									JOptionPane.showMessageDialog(f,"Login Successfully ");
									f.dispose();	
									//new Dashboard(userName);
										
								}else {
									  JOptionPane.showMessageDialog(f, "Invalid username or password!");
								}
						}
					}
					});
		
					btnCancel.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
						
							f.dispose();
						}
					});	
		
					lblSignUpPage.addMouseListener(new MouseAdapter() {
						
						  @Override
				            public void mousePressed(MouseEvent e) {
				         
				                if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton(e) || SwingUtilities.isMiddleMouseButton(e)) {
				                 
				                	f.dispose();
				                	new SignUpPage();
				                }
				            }
					});
					
	}
	
	 public static void main(String[] args) {

    	 new LoginPage();

    }
}
