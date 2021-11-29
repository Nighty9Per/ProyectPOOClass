package visual;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.Usuario;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class PanelLogin extends JPanel {
	private JPanel panelMain;
	private JButton btnLogin;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblNewLabel;
	public static PanelLogin login = null;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblCita;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public static PanelLogin getInstance() {
		if(login == null) {
			login = new PanelLogin();
		}
		return login;
	}
	
	private PanelLogin() {
		setLayout(null);
		setBounds(0, 45, 1124, 606);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1124, 606);
		add(panelMain);
		panelMain.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 11, 557, 595);
		panelMain.add(panel);
		panel.setLayout(null);
		
		lblCita = new JLabel("Hacer Cita");
		lblCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblCita.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblCita.setBounds(10, 53, 547, 82);
		panel.add(lblCita);
		
		btnNewButton = new JButton("Hacer Cita");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita regCita = new RegCita();
				regCita.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(113, 310, 334, 50);
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(567, 11, 557, 595);
		panelMain.add(panel_1);
		panel_1.setLayout(null);
		
		btnLogin = new JButton("Iniciar Sesi\u00F3n");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Clinica.getInstace().getLoginUser();
				if(user == null) {
					user = Clinica.getInstace().comprobarUsuario(txtUsername.getText(), txtPassword.getText());
					if(user != null) {
						logIn(user);
					}
					else {
						JOptionPane.showMessageDialog(null, "Informacion no coincide, revise Usuario y Contraseña", "Usuario no Encontrado", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(user != null) {
					logOut();
				}
			}
		});
		btnLogin.setBounds(112, 448, 334, 50);
		panel_1.add(btnLogin);
		
		lblNewLabel = new JLabel("Log In");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblNewLabel.setBounds(10, 53, 537, 82);
		panel_1.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(141, 210, 278, 35);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(141, 306, 278, 35);
		panel_1.add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(141, 185, 178, 23);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(141, 281, 178, 23);
		panel_1.add(lblNewLabel_2);

	}
	
	public void logOut() {
		int select = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar sesión?" , "Confirmación", JOptionPane.WARNING_MESSAGE);
		if (select == JOptionPane.YES_OPTION) {
			Clinica.getInstace().setLoginUser(null);
			txtPassword.setText("");
			txtUsername.setText("");
			txtPassword.setEditable(true);
			txtUsername.setEditable(true);
			btnLogin.setText("Log In");
			JOptionPane.showMessageDialog(null, "Sesión Finalizada", "Log Out", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void logIn(Usuario user) {
		Clinica.getInstace().setLoginUser(user);
		txtPassword.setText(user.getPassword());
		txtUsername.setText(user.getLogin());
		txtPassword.setEditable(false);
		txtUsername.setEditable(false);
		btnLogin.setText("Log Out");
		JOptionPane.showMessageDialog(null, "Log In Succesful", "Log In", JOptionPane.INFORMATION_MESSAGE);
		PrincipalClinica.getInstace().getUserLoginFeedback();
	}
}
