package com.shubham.StudentManagementSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shubham.StudentManagementSystem.Entity.UserInfo;

public class SignUpPage {
	
	JFrame f;
	JPanel p;
	
	JLabel lblUserName , lblPassword, lblEmailId , lblMobileNo,lblMessage,lblHeading,lblLoginPage ;
	JTextField txtUserName, txtPassword , txtEmailId , txtMobileNo ;	
	
	JButton btnSubmit ;
	JButton btnCancel ;
	
	Session session;
	Transaction transaction;

		public SignUpPage() {
				
				f = new JFrame();
				f.setTitle("SignUp Page Frame");
				f.setLocation(400, 50);
				f.setSize(500, 600);
				
				JPanel p = new JPanel();
				p.setLayout(null);
				f.add(p);
				
				
				 lblHeading = new JLabel();
				 lblMessage = new JLabel();
		
				 lblUserName = new JLabel();
				 lblPassword = new JLabel();
				 lblEmailId = new JLabel();
				 lblMobileNo = new JLabel();
				 lblLoginPage = new JLabel();
				
				 txtUserName = new JTextField();
				 txtPassword = new JTextField();
				 txtEmailId = new JTextField();
				 txtMobileNo = new JTextField();
			
				 btnSubmit = new JButton();
				 btnCancel = new JButton();
				 
				
				lblHeading.setText("!! Welcome To Student Management System !!");
				lblHeading.setBounds(100, 10 ,300, 30);
				p.add(lblHeading);
		
				lblMessage.setText(" SignUp Page ");
				lblMessage.setBounds(190, 45 ,200, 30);
				p.add(lblMessage);
				
				lblEmailId.setText("Email Id :");
				lblEmailId.setBounds(100, 100, 100, 30);
				p.add(lblEmailId);
				
				lblUserName.setText("UserName :");
				lblUserName.setBounds(100, 150, 100, 30);
				p.add(lblUserName);
					
				lblPassword.setText("Password :");
				lblPassword.setBounds(100,200,100, 30);
				p.add(lblPassword);
				
				lblMobileNo.setText("Mobile No :");
				lblMobileNo.setBounds(100,250,100, 30);
				p.add(lblMobileNo);
				
				txtEmailId.setBounds(210, 100, 150, 30);
				p.add(txtEmailId);
				
				txtUserName.setBounds(210, 150, 150, 30);
				p.add(txtUserName);
				
				txtPassword.setBounds(210, 200, 150, 30);
				p.add(txtPassword);
				
				txtMobileNo.setBounds(210, 250, 150, 30);
				p.add(txtMobileNo);
				
				lblLoginPage.setText("Already have an account?");
				lblLoginPage.setBounds(100, 300 ,200, 30);
				p.add(lblLoginPage);	
				
				btnSubmit.setBounds(100, 350, 100, 30);
				btnSubmit.setText("Submit");
				p.add(btnSubmit);
				
				btnCancel.setBounds(260, 350, 100, 30);
				btnCancel.setText("Cancel");
				p.add(btnCancel);
				
				
				lblLoginPage.addMouseListener(new MouseAdapter() {
					  @Override
			            public void mousePressed(MouseEvent e) {
			         
			                if (SwingUtilities.isLeftMouseButton(e) || SwingUtilities.isRightMouseButton(e) || SwingUtilities.isMiddleMouseButton(e)) {
			                 
			                	f.dispose();
			                	//new LoginPage();
			                	
			                }
			            }
					
				});
				
				
				
				btnSubmit.addActionListener(new ActionListener() {
										
					@Override
					public void actionPerformed(ActionEvent e) {
							
						 if (txtEmailId.getText().trim().isEmpty()) {
							 
			                    JOptionPane.showMessageDialog(f, "Email is required!");
			                    txtEmailId.requestFocus();
			                    
			                } else if (txtPassword.getText().trim().isEmpty()) {
			                	
			                    JOptionPane.showMessageDialog(f, "Password is required!");
			                    txtPassword.requestFocus();
			                    
			                } else if (txtUserName.getText().trim().isEmpty()) {
			                	
			                    JOptionPane.showMessageDialog(f, "UserName is required!");
			                    txtUserName.requestFocus();
			                    
			                } else if (txtMobileNo.getText().trim().isEmpty()) {
			                	
			                    JOptionPane.showMessageDialog(f, "Mobile is required!");
			                    txtPassword.requestFocus();
			                    
			                } else {
			                	
			                    
			                	try {
			                		
									session = HibernateConn.getSessionFactoryInstance().openSession();
									transaction =	session.beginTransaction();
									
									UserInfo userInfo = new UserInfo();
									userInfo.setEmailId(txtEmailId.getText().toString());
									userInfo.setUserName(txtUserName.getText().toString());
									userInfo.setPassword(txtPassword.getText().toString());
									userInfo.setMobileNo(txtMobileNo.getText().toString());
									
									session.save(userInfo);
									transaction.commit();
									
								} catch (HibernateException e2) {
									
									System.out.println("Hibernate Error "+e2.getMessage());
									transaction.rollback();
									
								} catch (Exception ex) {
									
									System.out.println("Data Error "+ex.getMessage());
									transaction.rollback();
									
								} finally {
									
									if (session != null) {
										session.close();
									}
								}	
							
							txtEmailId.setText("");
							txtUserName.setText("");
							txtPassword.setText("");
							txtMobileNo.setText("");
							txtEmailId.requestFocus();		
							
							JOptionPane.showMessageDialog(f, "SignUp Successful!");
				                   
			             }		
					}		
				});	
				
				btnCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					
						f.dispose();
						
					}
				});
				
				f.setVisible(true);	
		}
		
		public static void main(String args[]) {
			
			new SignUpPage();
			
		}

}
