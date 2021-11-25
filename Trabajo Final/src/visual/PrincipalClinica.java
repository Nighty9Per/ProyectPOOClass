package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Usuario;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalClinica extends JFrame {

	private JPanel contentPane;
	private JPanel panelMain;
	private JPanel panelUserInfo;
	private JButton btnLogOut;
	private JPanel panelUser;
	private JMenuItem mntmRegMedico;
	private JMenu mnAdministrador;
	private JMenu mnMedico;
	private JMenu mnVacunas;
	private JMenu mnListEnfermedades;
	private JMenuItem mntmRegVacuna;
	private JMenuItem mntmRegEnfermedad;
	private JMenuItem mntmListCitaMedica;
	private JMenuItem mntmCrearConsulta;
	private JMenuItem mntmListVacuna;
	private JMenuItem mntmNewMenuItem_6;
	private JMenuBar menuBar;
	private JPanel panelFoundation;
	private JPanel panelLogin;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalClinica frame = new PrincipalClinica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(null);
		
		panelUserInfo = new JPanel();
		panelUserInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUserInfo.setBounds(0, 0, 1124, 46);
		panelMain.add(panelUserInfo);
		panelUserInfo.setLayout(null);
		
		btnLogOut = new JButton("Log In");
		btnLogOut.setBounds(827, 13, 89, 23);
		panelUserInfo.add(btnLogOut);
		
		JLabel lblNewLabel = new JLabel("User: N/A");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 15, 166, 14);
		panelUserInfo.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Inicio");
		btnNewButton.setBounds(926, 13, 89, 23);
		panelUserInfo.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(1025, 13, 89, 23);
		panelUserInfo.add(btnNewButton_1);
		
		panelUser = new JPanel();
		panelUser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUser.setBounds(0, 45, 1124, 21);
		panelMain.add(panelUser);
		panelUser.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1124, 21);
		panelUser.add(menuBar);
		
		mnAdministrador = new JMenu("Administrador");
		menuBar.add(mnAdministrador);
		
		mntmRegMedico = new JMenuItem("Registrar Medico");
		mnAdministrador.add(mntmRegMedico);
		
		mntmRegVacuna = new JMenuItem("Registrar Vacuna");
		mnAdministrador.add(mntmRegVacuna);
		
		mntmRegEnfermedad = new JMenuItem("Registrar Enfermedad");
		mnAdministrador.add(mntmRegEnfermedad);
		
		mnMedico = new JMenu("M\u00E9dico");
		menuBar.add(mnMedico);
		
		mntmListCitaMedica = new JMenuItem("Lista Citas Medicas");
		mnMedico.add(mntmListCitaMedica);
		
		mntmCrearConsulta = new JMenuItem("Hacer Consulta");
		mnMedico.add(mntmCrearConsulta);
		
		mnVacunas = new JMenu("Vacunas");
		menuBar.add(mnVacunas);
		
		mntmListVacuna = new JMenuItem("Lista de Vacunas");
		mnVacunas.add(mntmListVacuna);
		
		mnListEnfermedades = new JMenu("Enfermedades");
		menuBar.add(mnListEnfermedades);
		
		mntmNewMenuItem_6 = new JMenuItem("Lista de Enfermedades");
		mnListEnfermedades.add(mntmNewMenuItem_6);
		
		panelFoundation = new JPanel();
		panelFoundation.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFoundation.setBounds(0, 67, 1124, 584);
		panelMain.add(panelFoundation);
		
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 45, 1124, 606);
		panelMain.add(panelLogin);
	}
	
	private void guardarDatos() {
		
	}
}
