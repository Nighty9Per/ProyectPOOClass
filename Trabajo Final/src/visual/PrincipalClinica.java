package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Clinica;
import logical.Enfermedad;
import logical.U_Administrador;
import logical.U_Medico;
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

	public static PrincipalClinica clinicaVisual = null;
	private JPanel contentPane;
	private JPanel panelMain;
	private JPanel panelUserInfo;
	private JButton btnLogOut;
	private JPanel panelUser;
	private JMenuItem mntmRegUsuario;
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
	private JButton btnCerrar;
	private JButton btnInicio;
	private JLabel lblUser;
	private JMenuItem mntmRegistrarCita;
	private JMenuItem mntmListUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalClinica frame = PrincipalClinica.getInstace();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static PrincipalClinica getInstace() {
		if(clinicaVisual == null) {
			clinicaVisual = new PrincipalClinica();
		}
		return clinicaVisual;
	}

	private PrincipalClinica() {
		
		Clinica.getInstace().crearAdministrado("asd", "admin", "admin", "asd", "asdasd", "direasdasdccion", "asd");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 700);
		setLocationRelativeTo(null);
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
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Clinica.getInstace().getLoginUser();
				if(user != null) {
					PanelLogin.getInstance().logOut();
					btnLogOut.setText("Log In");
					btnInicio.setEnabled(false);
					viewLoginPanel();
				}
			}
		});
		btnLogOut.setBounds(827, 13, 89, 23);
		panelUserInfo.add(btnLogOut);
		
		lblUser = new JLabel("User: N/A");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUser.setBounds(10, 15, 166, 14);
		panelUserInfo.add(lblUser);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setEnabled(false);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCleanPanelUser();
			}
		});
		btnInicio.setBounds(926, 13, 89, 23);
		panelUserInfo.add(btnInicio);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clinica.getInstace().setLoginUser(null);
				dispose();
			}
		});
		btnCerrar.setBounds(1025, 13, 89, 23);
		panelUserInfo.add(btnCerrar);
		
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
		
		mntmRegUsuario = new JMenuItem("Registrar Usuario");
		mntmRegUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUsuario regUsuario = new RegUsuario(null);
				regUsuario.setVisible(true);
			}
		});
		mnAdministrador.add(mntmRegUsuario);
		
		mntmRegVacuna = new JMenuItem("Registrar Vacuna");
		mntmRegVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna regVacuna = new RegVacuna();
				regVacuna.setVisible(true);
			}
		});
		mnAdministrador.add(mntmRegVacuna);
		
		mntmRegEnfermedad = new JMenuItem("Registrar Enfermedad");
		mntmRegEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad regEnfermedad = new RegEnfermedad(null);
				regEnfermedad.setVisible(true);
			}
		});
		mnAdministrador.add(mntmRegEnfermedad);
		
		mntmListUsuarios = new JMenuItem("Lista de Usuarios");
		mnAdministrador.add(mntmListUsuarios);
		
		
		
		mnMedico = new JMenu("M\u00E9dico");
		menuBar.add(mnMedico);
		
		mntmListCitaMedica = new JMenuItem("Lista de Citas Medicas");
		mnMedico.add(mntmListCitaMedica);
		
		mntmCrearConsulta = new JMenuItem("Hacer Consulta");
		mnMedico.add(mntmCrearConsulta);
		
		mntmRegistrarCita = new JMenuItem("Hacer Cita M\u00E9dica");
		mntmRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita regc = new RegCita();
				regc.setVisible(true);
			}
		});
		mnMedico.add(mntmRegistrarCita);
		
		mnVacunas = new JMenu("Vacunas");
		menuBar.add(mnVacunas);
		
		mntmListVacuna = new JMenuItem("Lista de Vacunas");
		mnVacunas.add(mntmListVacuna);
		
		mnListEnfermedades = new JMenu("Enfermedades");
		menuBar.add(mnListEnfermedades);
		
		mntmNewMenuItem_6 = new JMenuItem("Lista de Enfermedades");
		mnListEnfermedades.add(mntmNewMenuItem_6);
		
		panelFoundation = new JPanel();
		panelFoundation.setVisible(false);
		panelFoundation.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFoundation.setBounds(0, 67, 1124, 584);
		panelMain.add(panelFoundation);
		
		panelLogin = PanelLogin.getInstance();
		panelLogin.setBounds(0, 45, 1124, 606);
		panelMain.add(panelLogin);
		
		
		
		viewLoginPanel();
	}
	
	private void guardarDatos() {
		
	}
	
	// Poner el panel Login en frente
	private void viewLoginPanel() {
		panelLogin.setVisible(true);
		panelUser.setVisible(false);
	}
	
	
	public void viewCleanPanelUser() {
		panelUser.setVisible(true);
		panelLogin.setVisible(false);
	}
	
	// Recordar remover los comentario despues de todos los test.
	public void getUserLoginFeedback() {
		Usuario user = Clinica.getInstace().getLoginUser();
		if(user != null) {
			/*
			if(user instanceof U_Medico) {
				mnMedico.setEnabled(true);
				mnAdministrador.setEnabled(false);
			}
			else if(user instanceof U_Administrador) {
				mnMedico.setEnabled(false);
				mnAdministrador.setEnabled(true);
			}
			*/
			btnLogOut.setText("Log Out");
			btnInicio.setEnabled(true);
		}
		else {
			btnLogOut.setText("Log In");
		}
		viewCleanPanelUser();
	}
}
