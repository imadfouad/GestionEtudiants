package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame{

	private JFrame frame;
	private JTextField usernamefield;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordfield;
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JLabel label;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		usernamefield = new JTextField();
		usernamefield.setColumns(10);
		usernamefield.setBounds(417, 121, 185, 20);
		frame.getContentPane().add(usernamefield);
		usernamefield.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_1 = new JLabel("User Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(320, 116, 114, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_2.setBounds(320, 153, 87, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton = new JButton("Se Connecter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//METHODE BDD
				String username = usernamefield.getText().toString();
				String password = passwordfield.getText().toString();
				
				String sql = "select username , password from utilisateurs ";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					int i = 0;
					if(username.equals("") || password.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					else
					{
						while(resultat.next())    //kidoz 3la chaque ligne 
						{
							String username1 = resultat.getString("username");
							String password1 = resultat.getString("password");
							if(username1.equals(username) && password1.equals(password))
							{
								JOptionPane.showMessageDialog(null, "Connexion Réussie");
								i = 1;
								MenuAdministrateur obj = new MenuAdministrateur();
								obj.setLocationRelativeTo(null);
								obj.setVisible(true);
								frame.dispose();
							}		
						}
						if(i==0)
						{
							JOptionPane.showMessageDialog(null, "Connexion Echouée , Informations Incorrectes");
						}
					}
					
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}

				/*
				String username = usernamefield.getText().toString();
				String password = passwordfield.getText().toString();
				
//username=admin & password=1234
				if(!username.equals("") && !password.equals(""))
				{	if(username.equals("admin") && password.equals("1234"))
						{
							JOptionPane.showMessageDialog(null, "Connexion Réussite");	
							MenuAdministrateur menu = new MenuAdministrateur();
							menu.setVisible(true);
							
						}
						else
							JOptionPane.showMessageDialog(null, "Username ou mot de passe érroné!");
				}
				else
					JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");  */
			}
		});
		btnNewButton.setBounds(451, 206, 114, 32);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 1000, 80);
		frame.getContentPane().add(lblNewLabel_3);
		
		passwordfield = new JPasswordField();
		passwordfield.setBounds(417, 159, 185, 20);
		frame.getContentPane().add(passwordfield);
		passwordfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_4 = new JLabel("Mot de passe oubli\u00E9 ?");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				IndicationPass obj = new IndicationPass();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(501, 181, 112, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\greengradient.jpg"));
		lblNewLabel.setBounds(0, 0, 1000, 500);
		frame.getContentPane().add(lblNewLabel);
		
		label = new JLabel("New label");
		label.setBounds(289, 243, 46, 14);
		frame.getContentPane().add(label);
	}
}
