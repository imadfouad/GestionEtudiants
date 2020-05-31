package Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jcp.xml.dsig.internal.dom.DOMUtils;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class GestionUsers extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;
	private JTable table;
	private String userold=null;
	
	void fermer()
	{
		dispose();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUsers frame = new GestionUsers();
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
	public GestionUsers() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 221, 157, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setBounds(74, 196, 85, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
		/*	
				String sql="select password from utilisateurs where username = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					resultat = prepared.executeQuery();
					if(resultat.next())
					{
						String password = resultat.getString("password");
						textField_1.setText(password);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
		*/		
			}
		});
		textField.setBounds(152, 190, 157, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setBounds(74, 226, 70, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\unnamed (1).png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO utilisateurs (username,password) values (? , ?)";
				
				try {
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					
					if(!textField.getText().toString().equals("") && !textField_1.getText().toString().equals(""))
					{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur Ajouté !");
						textField.setText("");
						textField_1.setText("");
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir les champs vides");
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(122, 257, 55, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\t\u00E9l\u00E9chargement (2).jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "delete from utilisateurs where username=? and password = ?";
				try {
					if(!textField.getText().toString().equals("") && !textField_1.getText().toString().equals("")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, textField_1.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur supprimé");
						textField.setText("");
						textField_1.setText("");
						
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez choisir l'utilisateur");
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					
			}
		});
		btnNewButton_1.setBounds(273, 257, 55, 55);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\t\u00E9l\u00E9chargement (1).jpg"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText().toString();
				String sql = "update utilisateurs set username=? , password = ? where username = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,username );
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, userold);
					
					if(!textField_1.getText().toString().equals("") && !textField.getText().toString().equals(""))
					{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Utilisateur Modifié");
						textField.setText("");
						textField_1.setText("");
						
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (2).png"));
		lblNewLabel_4.setBounds(898, 91, 36, 37);
		contentPane.add(lblNewLabel_4);
		btnNewButton_2.setBounds(198, 257, 55, 55);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_5.setBounds(10, 91, 36, 37);
		contentPane.add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(455, 139, 479, 288);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				userold = table.getModel().getValueAt(ligne, 0).toString() ;
				String password = table.getModel().getValueAt(ligne, 1).toString() ;
				
				textField.setText(userold);
				textField_1.setText(password);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 1000, 80);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\196210.jpg"));
		lblNewLabel.setBounds(0, 2, 1000, 500);
		contentPane.add(lblNewLabel);
	}
	
	public void UpdateTable()
	{
		String sql = "select username , password from utilisateurs";
		try {
			prepared= cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
