package Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import net.proteanit.sql.DbUtils;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionAbsence extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox nometudbox;
	
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
					GestionAbsence frame = new GestionAbsence();
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
	public GestionAbsence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		JComboBox raisonBox = new JComboBox();
		raisonBox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez", "Maladie", "Retard", "Autres"}));
		raisonBox.setBounds(174, 242, 197, 26);
		contentPane.add(raisonBox);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(174, 205, 197, 26);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		JLabel lblNewLabel_1 = new JLabel("Nom Etudiant :");
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(78, 176, 107, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date Absence :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(81, 212, 96, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = nometudbox.getSelectedItem().toString();
				String date = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				String raison = raisonBox.getSelectedItem().toString();
				
				String sql = "insert into absences (nom_etud,date,raison) values (?,?,?)";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, nom);
					prepared.setString(2, date);
					prepared.setString(3, raison);
					if(!date.equals("") && !nometudbox.getSelectedItem().equals("Sélectionnez") && !raisonBox.getSelectedItem().equals("Sélectionnez"))
					{
						prepared.execute();
						nometudbox.setSelectedItem("Sélectionnez");
						raisonBox.setSelectedItem("Sélectionnez");
						
						
						JOptionPane.showMessageDialog(null, "Absence Ajouté");
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\calendar-add-512.png"));
		btnNewButton.setBounds(143, 315, 60, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez l'absence");
				}
				else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from absences where id_absence= ?";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, id);
						prepared.execute();
						
						JOptionPane.showMessageDialog(null, "Absence Supprimé");
						UpdateTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\calendar-delete-512.png"));
		btnNewButton_2.setBounds(214, 315, 60, 60);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
				{
					JOptionPane.showMessageDialog(null, "Sélectionnez l'absence");
				}
				else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update absences set nom_etud= ? , date=? , raison=? where id_absence='"+id+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, nometudbox.getSelectedItem().toString());
						prepared.setString(2, ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
						prepared.setString(3, raisonBox.getSelectedItem().toString());
						if(!nometudbox.getSelectedItem().toString().equals("Sélectionnez") && !raisonBox.getSelectedItem().toString().equals("Sélectionnez"))
						{
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Absence Modifié");
							UpdateTable();
						}
						else
							JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\calendar-edit-512.png"));
		btnNewButton_1.setBounds(286, 315, 60, 60);
		contentPane.add(btnNewButton_1);
		
		JLabel raisonfield = new JLabel("Raison :");
		raisonfield.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		raisonfield.setBounds(119, 247, 56, 18);
		contentPane.add(raisonfield);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Table des Absences :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(523, 101, 133, 20);
		contentPane.add(lblNewLabel_5);
		
		
		
		
		
		nometudbox = new JComboBox();
		nometudbox.setModel(new DefaultComboBoxModel(new String[] {"S\u00E9lectionnez"}));
		nometudbox.setBounds(174, 171, 197, 26);
		contentPane.add(nometudbox);
		
		RemplirCombo();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(523, 122, 436, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				String name = table.getValueAt(ligne,1).toString();
				String date = table.getValueAt(ligne,2).toString();
				String raison = table.getValueAt(ligne, 3).toString();
																				
				
				
				nometudbox.setSelectedItem(name);
				Date date2=null;
				try {
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    					/////////////////////////////////////////////////////////
				dateChooser.setDate(date2);
				raisonBox.setSelectedItem(raison);
				
			}
		});
		scrollPane.setViewportView(table);
		
		
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
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (2).png"));
		lblNewLabel_10.setBounds(922, 86, 52, 35);
		contentPane.add(lblNewLabel_10);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\dQcnML.png"));
		lblNewLabel.setBounds(0, 5, 984, 451);
		contentPane.add(lblNewLabel);
	}
	
	public void RemplirCombo()
	{
		String sql = "select * from etudiants order by prenom";
		try {
			prepared=cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next())
			{
				String prenom = resultat.getString("prenom").toString();
				String nom = resultat.getString("nom").toString();
				nometudbox.addItem(prenom+" "+nom);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void UpdateTable()
	{
		String sql = "select * from absences";
		try {
			prepared= cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
