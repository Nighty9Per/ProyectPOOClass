package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Clinica;
import logical.U_Administrador;
import logical.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AccesoSistema extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JButton btnAcceso;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccesoSistema frame = new AccesoSistema();
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
	public AccesoSistema() {
		if (Clinica.getInstace().getMisUsuarios().size() == 0) {
			Clinica.getInstace().crearAdministrado("527813520", "admin", "contrasena6", "Ismael Reynoso", "8295513092", "Pontó", "Director");
		}
		setResizable(false);
		setTitle("Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AccesoSistema.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		
		
		lblNewLabel.setBounds(101, 46, 48, 48);
		contentPane.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setName("");
		txtLogin.setToolTipText("");
		txtLogin.setColumns(10);
		txtLogin.setBounds(101, 136, 134, 23);
		contentPane.add(txtLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setBounds(27, 140, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(27, 188, 96, 14);
		contentPane.add(lblContrasea);
		
		btnAcceso = new JButton("Entrar");
		btnAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario acceso = Clinica.getInstace().comprobarUsuario(txtLogin.getText(), txtPassword.getText());
				if(acceso != null) {
					PrincipalClinica prin = new PrincipalClinica(acceso);
					prin.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "Bienvenido usuario " +acceso.getNombre(), "Información", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "El login o la contraseña es incorrecto, por favor intente de nuevo", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
					clean();
				}
			}

			private void clean() {
				txtLogin.setText("");
				txtPassword.setText("");
				
			}
		});
		btnAcceso.setBounds(81, 269, 89, 23);
		contentPane.add(btnAcceso);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(101, 185, 134, 23);
		contentPane.add(txtPassword);
	}
}
