package Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class IndicationPass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndicationPass frame = new IndicationPass();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IndicationPass() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String username = textField.getText().toString();
				String sql = "select password from utilisateurs where username = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,username);
					resultat = prepared.executeQuery();
				
					if(resultat.next())
					{
						String pass = resultat.getString("password");
						
						String pass1 = pass.substring(0,2);
						
						textField_1.setText(pass1);
						
					}
	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		textField.setBounds(170, 26, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setEditable(false);
		textField_1.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		textField_1.setBounds(222, 55, 131, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setBounds(71, 29, 70, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Deux premiers caract\u00E8res du mdp :");
		lblNewLabel_1.setBounds(10, 57, 206, 20);
		contentPane.add(lblNewLabel_1);
	}

}
