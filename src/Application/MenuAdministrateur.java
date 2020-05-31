package Application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdministrateur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrateur frame = new MenuAdministrateur();
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
	public MenuAdministrateur() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEtudiants obj = new GestionEtudiants();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\ge (3).jpg"));
		btnNewButton_1.setBounds(425, 91, 141, 138);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionUsers obj = new GestionUsers();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\unnamed.png"));
		btnNewButton.setBounds(272, 91, 141, 141);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionFilieres obj = new GestionFilieres();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\755ea05d99f1995550817de7a0d0079d.jpg"));
		btnNewButton_2.setBounds(576, 93, 141, 136);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAbsence obj = new GestionAbsence();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\illustration-icone-calendrier_53876-5588.jpg"));
		btnNewButton_3.setBounds(272, 276, 140, 140);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\icone-reporting-e1471982757878.png"));
		btnNewButton_5.setBounds(574, 276, 140, 140);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\image-icone-couleur-livres-educatifs_24911-30407.jpg"));
		btnNewButton_4.setBounds(422, 275, 142, 141);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des Utilisateurs");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(282, 231, 139, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion des Fili\u00E8res");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(595, 231, 106, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gestion des Absences");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(282, 420, 112, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Etudiants");
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(437, 232, 124, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion des Mati\u00E8res");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(435, 420, 124, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Gestion des Notes");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(595, 420, 106, 18);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 1000, 80);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\content-e1472587975551.jpg"));
		lblNewLabel.setBounds(0, 0, 1000, 500);
		contentPane.add(lblNewLabel);
	}
}
