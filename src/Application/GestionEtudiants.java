package Application;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class GestionEtudiants extends JFrame {

	private JPanel contentPane;
	private JTextField prenomfield;
	private JTextField nomfield;
	private JTextField adressefield;
	private JTextField numtelfield;
	private JTextField cinfield;
	private String s;
	
	private JTextField datenaissfield;
	private JTable table;
	private JComboBox filierecombox;
	
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
					GestionEtudiants frame = new GestionEtudiants();
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
	public GestionEtudiants() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cnx =  ConnexionMysql.ConnexionDb();
		
		prenomfield = new JTextField();
		prenomfield.setBounds(149, 91, 134, 26);
		contentPane.add(prenomfield);
		prenomfield.setColumns(10);
		prenomfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		nomfield = new JTextField();
		nomfield.setColumns(10);
		nomfield.setBounds(149, 128, 134, 26);
		contentPane.add(nomfield);
		nomfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		cinfield = new JTextField();
		cinfield.setColumns(10);
		cinfield.setBounds(149, 165, 134, 26);
		contentPane.add(cinfield);
		cinfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		numtelfield = new JTextField();
		numtelfield.setColumns(10);
		numtelfield.setBounds(149, 202, 134, 26);
		contentPane.add(numtelfield);
		numtelfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(289, 90, 113, 107);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 1));
		
		JLabel labimage = new JLabel("");
		panel.add(labimage);
		
		
		adressefield = new JTextField();
		adressefield.setColumns(10);
		adressefield.setBounds(149, 239, 253, 26);
		contentPane.add(adressefield);
		adressefield.setHorizontalAlignment(SwingConstants.CENTER);
		
		datenaissfield = new JTextField();
		datenaissfield.setColumns(10);
		datenaissfield.setBounds(149, 276, 253, 26);
		contentPane.add(datenaissfield);
		datenaissfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(91, 98, 70, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Nom :");
		lblNewLabel_4.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(91, 135, 46, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CIN :");
		lblNewLabel_5.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(91, 172, 46, 18);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Parcourir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpg" , "png","gif");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);     //=0 si kolchi mzyan
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File selectedfile = fileChooser.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					java.awt.Image img = myImage.getImage();
					java.awt.Image newImage = img.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalimg = new ImageIcon(newImage);
					labimage.setIcon(finalimg);
					s=path;
				}
				else
				{
					if(result == JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, "Choisissez l'image");
				}
				
			}
		});
		btnNewButton_4.setBounds(289, 202, 113, 26);
		contentPane.add(btnNewButton_4);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Num Tel :");
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(80, 209, 63, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Adresse :");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(91, 246, 63, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Date Naissance :");
		lblNewLabel_8.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(55, 283, 99, 18);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String prenom = prenomfield.getText().toString();
				String nom = nomfield.getText().toString();
				String cin = cinfield.getText().toString();
				String numtel = numtelfield.getText().toString();
				String adresse = adressefield.getText().toString();
				String datenaiss = datenaissfield.getText().toString();
				
				String sql = "insert into etudiants (prenom,nom,cin,tel,datenaissance,adresse,filière,image) values (?,?,?,?,?,?,?,?)";
				try {
					
					FileInputStream img = new FileInputStream(new File(s));
					
					if(!prenom.equals("") && !nom.equals("") && !cin.equals("") && !numtel.equals("") && !adresse.equals("") && !datenaiss.equals(""))
					{
						prepared= cnx.prepareStatement(sql);
						prepared.setString(1, prenom);
						prepared.setString(2, nom);
						prepared.setString(3, cin);
						prepared.setString(4, numtel);
						prepared.setString(5, datenaiss);
						prepared.setString(6, adresse);
						prepared.setString(7, filierecombox.getSelectedItem().toString());
						prepared.setBlob(8, img);
						
						prepared.execute();
						
						prenomfield.setText("");
						nomfield.setText("");
						cinfield.setText("");
						numtelfield.setText("");
						adressefield.setText("");
						datenaissfield.setText("");
						
						JOptionPane.showMessageDialog(null, "Etudiant Ajouté");
						UpdateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Veuillez Remplir tous les champs");
					
		
				} catch (SQLException | FileNotFoundException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\t\u00E9l\u00E9chargement (3).jpg"));
		btnNewButton.setBounds(98, 357, 63, 64);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
					JOptionPane.showMessageDialog(null, "Veuillez choisir l'étudiant");
				else {
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "delete from etudiants where id_etudiant='"+id+"'";
					
					try {
						prepared=cnx.prepareStatement(sql);
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Etudiant Supprimé");
						UpdateTable();
						prenomfield.setText("");
						nomfield.setText("");
						cinfield.setText("");
						numtelfield.setText("");
						datenaissfield.setText("");
						adressefield.setText("");
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}			
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\icono-de-eliminar-png-5.png"));
		btnNewButton_1.setBounds(166, 357, 63, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ligne = table.getSelectedRow();
				if(ligne==-1)
					JOptionPane.showMessageDialog(null, "Veuillez choisir l'étudiant");
				else
				{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "Update etudiants set prenom = ? , nom = ? , cin = ? , tel = ? , datenaissance=? , adresse= ? , filière=? , image=? where id_etudiant = '"+id+"'";
					
					try {
						FileInputStream in = new FileInputStream(new File(s));

						prepared = cnx.prepareStatement(sql);
						
						prepared.setString(1, prenomfield.getText().toString());
						prepared.setString(2, nomfield.getText().toString());
						prepared.setString(3, cinfield.getText().toString());
						prepared.setString(4, numtelfield.getText().toString());
						prepared.setString(5, datenaissfield.getText().toString());
						prepared.setString(6, adressefield.getText().toString());
						prepared.setString(7, filierecombox.getSelectedItem().toString());
						prepared.setBlob(8, in);
						
						prepared.execute();
						
						prenomfield.setText("");
						nomfield.setText("");
						cinfield.setText("");
						numtelfield.setText("");
						datenaissfield.setText("");
						adressefield.setText("");
						
						UpdateTable();
						JOptionPane.showMessageDialog(null, "Etudiant Modifié");
						
					} catch (SQLException | FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}
				
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\edit_male_user_256.png"));
		btnNewButton_2.setBounds(236, 357, 63, 64);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Document doc = new Document();
				
				String sql = "SELEct * from etudiants";
				 
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat= prepared.executeQuery();
					
					
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\gamer\\Desktop\\Etudiant.pdf"));
					doc.open();
					
					Image img = Image.getInstance("C:\\Users\\gamer\\Desktop\\eco.jpg");
					doc.add(img);
					img.setAlignment(Image.ALIGN_CENTER);
					
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Liste des Etudiants :"));
					doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(7);   //chaque ligne fiha 6 colonnes
					table.setWidthPercentage(100);
		
					PdfPCell cell;   //Colonne
					
					cell = new PdfPCell(new Phrase("Prenom",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Nom",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("CIN",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("Num TEL",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("Adresse",FontFactory.getFont("Comic Sans MS",12)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("Date Naissance",FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Filière",FontFactory.getFont("Comic Sans MS",11)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.GRAY);
					table.addCell(cell);
					
					//////////////////////////////////////////////////////////////// 2 eme ligne
					while(resultat.next())
					{
						cell = new PdfPCell(new Phrase(resultat.getString("nom").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("prenom").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("cin").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(resultat.getString("tel").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(resultat.getString("adresse").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(resultat.getString("datenaissance").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("filière").toString(),FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						//cell.setBackgroundColor(BaseColor.GRAY);
						table.addCell(cell);
					}
					
					
					
					
					doc.add(table);
					
					doc.close();
					Desktop.getDesktop().open(new File("C:\\Users\\gamer\\Desktop\\Etudiant.pdf"));
					
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\23935.png"));
		btnNewButton_3.setBounds(305, 357, 63, 62);
		contentPane.add(btnNewButton_3);
		
		
		
		JLabel lblNewLabel_7 = new JLabel("Table des Etudiants :");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(436, 97, 127, 20);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateTable();
			}
		});
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\output-onlinepngtools (2).png"));
		lblNewLabel_10.setBounds(922, 82, 52, 35);
		contentPane.add(lblNewLabel_10);
		
		 filierecombox = new JComboBox();
		filierecombox.setBounds(147, 307, 255, 22);
		contentPane.add(filierecombox);
		
		remplirCombobox();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(436, 124, 521, 315);
		contentPane.add(scrollPane);
		
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
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				String id = table.getModel().getValueAt(ligne,0).toString();
				
				String sql = "select * from etudiants where id_etudiant = '"+id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if(resultat.next())
					{
						prenomfield.setText(resultat.getString("prenom"));
						nomfield.setText(resultat.getString("nom"));
						cinfield.setText(resultat.getString("cin"));
						numtelfield.setText(resultat.getString("tel"));
						datenaissfield.setText(resultat.getString("datenaissance"));
						adressefield.setText(resultat.getString("adresse"));
						filierecombox.setSelectedItem(resultat.getString("filière"));
						
						byte[] img = resultat.getBytes("image");
						ImageIcon image = new ImageIcon(img);
						java.awt.Image im = image.getImage();
						java.awt.Image myimage = im.getScaledInstance(labimage.getWidth(), labimage.getHeight() , java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgg = new ImageIcon(myimage);
						labimage.setIcon(imgg);
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_9 = new JLabel("Fili\u00E8re :");
		lblNewLabel_9.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(98, 312, 46, 18);
		contentPane.add(lblNewLabel_9);
		

		JLabel lblNewLabel_3 = new JLabel("New Label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\eco.jpg"));
		lblNewLabel_3.setBounds(0, 0, 984, 80);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\gamer\\Desktop\\Gestion des etudiants\\d2e2kbv-e209c68b-7146-4e18-a266-371626b3ef0d.png"));
		lblNewLabel.setBounds(-47, 2, 1000, 500);
		contentPane.add(lblNewLabel);
		
	}
	
	public void UpdateTable()
	{
		String sql = "select * from etudiants";
		try {
			prepared= cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void remplirCombobox()
	{
		String sql = "select * from filieres";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
		
			while(resultat.next())
			{
				String nom = resultat.getString("nom").toString();
				filierecombox.addItem(nom);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
