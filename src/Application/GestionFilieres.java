package Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionFilieres extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	Connection cnx = null ;
	ResultSet resultat = null;
	PreparedStatement prepared = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFilieres frame = new GestionFilieres();
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
	public GestionFilieres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JLabel lblNewLabel_1 = new JLabel("Nom Fili\u00E8re :");
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(47, 212, 85, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Table des Fili\u00E8res :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(504, 107, 110, 26);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(133, 205, 187, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Sélectionnez");
		comboBox.addItem("Licence Professionnelle");
		comboBox.addItem("Licence Fondamentale");
		comboBox.setBounds(133, 241, 187, 26);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "insert into filieres (nom,type) values (? , ?)";
				try {
					if(!textField.getText().toString().equals("") && !comboBox.getSelectedItem().equals("Sélectionnez"))
					{
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, comboBox.getSelectedItem().toString());
						prepared.execute();
						textField.setText("");
						JOptionPane.showMessageDialog(null, "Filière Ajoutée");
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\add-circle-green-512.png"));
		btnNewButton.setBounds(102, 344, 66, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
					JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
				else
				{
					int a = JOptionPane.showConfirmDialog(null , "êtes-vous sûr de vouloir Supprimer cette Filière ?","Supprimer Filière",JOptionPane.YES_NO_OPTION);
					String sql = "delete from filieres where id_filiere = ?";
					try {
						prepared= cnx.prepareStatement(sql);
						prepared.setString(1, table.getModel().getValueAt(ligne, 0).toString());
						if(a==0) //Yes->a==0
							{	prepared.execute();
								JOptionPane.showMessageDialog(null, "Filière Supprimée");
							}
						UpdateTable();
						textField.setText("");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\150_check_no_delete_error_remove-512.png"));
		btnNewButton_1.setBounds(178, 344, 66, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
					JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
				else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update  filieres set nom = ? , type=? where id_filiere = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, comboBox.getSelectedItem().toString());
						prepared.execute();
						textField.setText("");
						UpdateTable();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\document-edit-flat.png"));
		btnNewButton_2.setBounds(254, 344, 66, 60);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(505, 136, 457, 298);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				String name = table.getModel().getValueAt(ligne, 1).toString();
				textField.setText(name);
				comboBox.setSelectedItem(table.getModel().getValueAt(ligne, 2).toString());
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Type :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(80, 246, 47, 18);
		contentPane.add(lblNewLabel_2);
		
		//
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (2).png"));
		lblNewLabel_10.setBounds(922, 98, 52, 35);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_0 = new JLabel("");
		lblNewLabel_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuAdministrateur obj = new MenuAdministrateur();
				
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_0.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (3) (1).png"));
		lblNewLabel_0.setBounds(10, 91, 36, 37);
		contentPane.add(lblNewLabel_0);
		
		
		JLabel lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\dQcnML.png"));
		lblNewLabel.setBounds(0, 4, 1010, 500);
		contentPane.add(lblNewLabel);
	}
	
	public void UpdateTable()
	{
		String sql = "select * from filieres";
		try {
			prepared= cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
