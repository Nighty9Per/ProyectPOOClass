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

public class PanelLogin extends JPanel {
	private JPanel panelMain;
	private JButton btnLogin;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public PanelLogin() {
		setLayout(null);
		setBounds(0, 45, 1124, 606);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1124, 606);
		add(panelMain);
		panelMain.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 11, 557, 595);
		panelMain.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(567, 11, 557, 595);
		panelMain.add(panel_1);
		panel_1.setLayout(null);
		
		btnLogin = new JButton("Log In");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Clinica.getInstace().comprobarUsuario(txtUsername.getText(), txtPassword.getText());
				if(user == null) {
					JOptionPane.showMessageDialog(null, "Informacion no coincide, revise Usuario y Contraseña", "Usuario no Encontrado", JOptionPane.WARNING_MESSAGE);
				}
				else {
					
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
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(141, 306, 278, 35);
		panel_1.add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(141, 185, 178, 23);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(141, 281, 178, 23);
		panel_1.add(lblNewLabel_2);

		
	}
	
	public void checkUserLoginFeedBack() {
		Usuario user = PrincipalClinica.getInstace().getUser();
		if(user != null) {
			txtPassword.setText(user.getPassword());
			txtUsername.setText(user.getLogin());
			txtPassword.setEditable(false);
			txtUsername.setEditable(false);
			btnLogin.setText("Log Out");
		}
		else {
			txtPassword.setText("");
			txtUsername.setText("");
			txtPassword.setEditable(true);
			txtUsername.setEditable(true);
			btnLogin.setText("Log In");
		}
	}
}
