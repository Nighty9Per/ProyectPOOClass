package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logical.Clinica;
import logical.U_Administrador;
import logical.U_Medico;
import logical.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnAccion;
	private JPanel panel_1;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblCdula;
	private JTextField txtCed;
	private JLabel lblTlefono;
	private JTextField txtTelefono;
	private JLabel lblDireccin;
	private JTextField txtDirec;
	private JLabel lblLogin;
	private JTextField txtLogin;
	private JLabel lblNewLabel_1;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirm;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnMedico;
	private JPanel pnAdmin;
	private JTextField txtPuesto;
	private JTextField txtCodMed;
	private JLabel lblEspecialidad;
	private JTextField txtEspecialidad;
	private JPanel pnMed;
	private static Usuario update = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUsuario dialog = new RegUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario(Usuario modificar) {
		update = modificar;
		setModal(true);
		setResizable(false);
		if(update == null) {
			setTitle("Registrar Usuario");
		} else {
			setTitle("Modificar Usuario");
		}
		setBounds(100, 100, 608, 386);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel_1.add(lblNewLabel);
			
			txtCodigo = new JTextField();
			txtCodigo.setText("A-"+Clinica.getInstace().getGenerateCodigoAdministrador());
			txtCodigo.setEnabled(false);
			txtCodigo.setEditable(false);
			txtCodigo.setToolTipText("");
			txtCodigo.setName("");
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(76, 8, 124, 23);
			panel_1.add(txtCodigo);
			{
				lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(10, 45, 68, 14);
				panel_1.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setToolTipText("");
				txtNombre.setName("");
				txtNombre.setColumns(10);
				txtNombre.setBounds(76, 42, 124, 23);
				panel_1.add(txtNombre);
			}
			{
				lblCdula = new JLabel("C\u00E9dula:");
				lblCdula.setBounds(210, 45, 68, 14);
				panel_1.add(lblCdula);
			}
			{
				txtCed = new JTextField();
				txtCed.setToolTipText("");
				txtCed.setName("");
				txtCed.setColumns(10);
				txtCed.setBounds(255, 41, 134, 23);
				panel_1.add(txtCed);
			}
			{
				lblTlefono = new JLabel("T\u00E9lefono:");
				lblTlefono.setBounds(405, 45, 68, 14);
				panel_1.add(lblTlefono);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setToolTipText("");
				txtTelefono.setName("");
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(461, 42, 111, 23);
				panel_1.add(txtTelefono);
			}
			{
				lblDireccin = new JLabel("Direcci\u00F3n:");
				lblDireccin.setBounds(10, 79, 68, 14);
				panel_1.add(lblDireccin);
			}
			{
				txtDirec = new JTextField();
				txtDirec.setToolTipText("");
				txtDirec.setName("");
				txtDirec.setColumns(10);
				txtDirec.setBounds(76, 76, 496, 23);
				panel_1.add(txtDirec);
			}
			{
				lblLogin = new JLabel("Login:");
				lblLogin.setBounds(10, 142, 68, 14);
				panel_1.add(lblLogin);
			}
			{
				txtLogin = new JTextField();
				txtLogin.setToolTipText("");
				txtLogin.setName("");
				txtLogin.setColumns(10);
				txtLogin.setBounds(66, 139, 134, 23);
				panel_1.add(txtLogin);
			}
			{
				lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
				lblNewLabel_1.setBounds(10, 184, 85, 14);
				panel_1.add(lblNewLabel_1);
			}
			
			txtPassword = new JPasswordField();
			txtPassword.setBounds(10, 200, 190, 23);
			panel_1.add(txtPassword);
			
			JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a:");
			lblConfirmarContrasea.setBounds(10, 241, 149, 14);
			panel_1.add(lblConfirmarContrasea);
			
			txtConfirm = new JPasswordField();
			txtConfirm.setBounds(10, 257, 190, 23);
			panel_1.add(txtConfirm);
			
			rdbtnAdmin = new JRadioButton("Administrador");
			rdbtnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnAdmin.setVisible(true);
					pnMed.setVisible(false);
					rdbtnAdmin.setSelected(true);
					rdbtnMedico.setSelected(false);
					txtCodigo.setText("A-"+Clinica.getInstace().getGenerateCodigoAdministrador());
				}
			});
			rdbtnAdmin.setSelected(true);
			rdbtnAdmin.setBounds(286, 138, 109, 23);
			panel_1.add(rdbtnAdmin);
			
			rdbtnMedico = new JRadioButton("M\u00E9dico");
			rdbtnMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnAdmin.setVisible(false);
					pnMed.setVisible(true);
					rdbtnAdmin.setSelected(false);
					rdbtnMedico.setSelected(true);
					txtCodigo.setText("M-"+Clinica.getInstace().getGenerateCodigoMedico());
				}
			});
			rdbtnMedico.setBounds(397, 138, 109, 23);
			panel_1.add(rdbtnMedico);
			
			pnAdmin = new JPanel();
			pnAdmin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			pnAdmin.setBounds(286, 184, 268, 95);
			panel_1.add(pnAdmin);
			pnAdmin.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Puesto:");
			lblNewLabel_2.setBounds(10, 11, 46, 14);
			pnAdmin.add(lblNewLabel_2);
			
			txtPuesto = new JTextField();
			txtPuesto.setToolTipText("");
			txtPuesto.setName("");
			txtPuesto.setColumns(10);
			txtPuesto.setBounds(70, 8, 134, 23);
			pnAdmin.add(txtPuesto);
			
			pnMed = new JPanel();
			pnMed.setVisible(false);
			pnMed.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			pnMed.setBounds(286, 184, 268, 95);
			panel_1.add(pnMed);
			pnMed.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Identificaci\u00F3n:");
			lblNewLabel_3.setBounds(10, 11, 94, 14);
			pnMed.add(lblNewLabel_3);
			
			txtCodMed = new JTextField();
			txtCodMed.setToolTipText("");
			txtCodMed.setName("");
			txtCodMed.setColumns(10);
			txtCodMed.setBounds(94, 7, 134, 23);
			pnMed.add(txtCodMed);
			{
				lblEspecialidad = new JLabel("Especialidad:");
				lblEspecialidad.setBounds(10, 48, 94, 14);
				pnMed.add(lblEspecialidad);
			}
			{
				txtEspecialidad = new JTextField();
				txtEspecialidad.setToolTipText("");
				txtEspecialidad.setName("");
				txtEspecialidad.setColumns(10);
				txtEspecialidad.setBounds(94, 44, 134, 23);
				pnMed.add(txtEspecialidad);
			}
		}
		if (update != null) {
			txtCed.setText(update.getCedula());
			txtCodigo.setText(update.getCodigoUsuario());
			txtNombre.setText(update.getNombre());
			txtTelefono.setText(update.getTelefono());
			txtDirec.setText(update.getDireccion());
			txtLogin.setText(update.getLogin());
			txtPassword.setText(update.getPassword());
			if (update instanceof U_Administrador) {
				U_Administrador aux = (U_Administrador)update;
				pnAdmin.setVisible(true);
				rdbtnAdmin.setEnabled(false);
				rdbtnMedico.setEnabled(false);
				rdbtnAdmin.setSelected(true);
				txtPuesto.setText(aux.getPuestoLaboral());
			}else if (update instanceof U_Medico) {
				U_Medico aux = (U_Medico)update;
				pnMed.setVisible(true);
				rdbtnAdmin.setEnabled(false);
				rdbtnMedico.setEnabled(false);
				rdbtnMedico.setSelected(true);
				txtCodMed.setText(aux.getCodigoMedico());
				txtEspecialidad.setText(aux.getEspecialidad());
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAccion = new JButton();
				if (update == null) {
					btnAccion.setText("Registrar");
				}else {
					btnAccion.setText("Modificar");
				}
				btnAccion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pass = txtPassword.getText();
						String conf = txtConfirm.getText();
						if (update == null) {
							if (pass.equals(conf)) {
								if (rdbtnAdmin.isSelected()) {
									Clinica.getInstace().crearAdministrado(txtCed.getText(), txtLogin.getText(), pass, txtNombre.getText(), txtTelefono.getText(), txtDirec.getText(), txtPuesto.getText());
									clean();
									txtCodigo.setText("A-"+Clinica.getInstace().getGenerateCodigoAdministrador());
								}
								if (rdbtnMedico.isSelected()) {
									Clinica.getInstace().crearMedico(txtCed.getText(), txtLogin.getText(), pass, txtNombre.getText(), txtTelefono.getText(), txtDirec.getText(), txtCodMed.getText(), txtEspecialidad.getText());
									clean();
									txtCodigo.setText("M-"+Clinica.getInstace().getGenerateCodigoMedico());
								}
								JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);

							}else {
								JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, intente de nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);

							}
						}else {
							if (pass.equals(conf)) {
								if (rdbtnAdmin.isSelected()) {
									Usuario aux = new U_Administrador(txtCodigo.getText(), txtCed.getText(), txtLogin.getText(), pass, txtNombre.getText(), txtTelefono.getText(), txtDirec.getText(), txtPuesto.getText());
									Clinica.getInstace().editarUsuario(update.getCodigoUsuario(), aux);
								}
								if (rdbtnMedico.isSelected()) {
									Usuario aux = new U_Medico(txtCodigo.getText(), txtCed.getText(), txtLogin.getText(), pass, txtNombre.getText(), txtTelefono.getText(), txtDirec.getText(), txtCodMed.getText(), txtEspecialidad.getText());
								}
								JOptionPane.showMessageDialog(null, "Usuario editado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

							}else {
								JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, intente de nuevo", "Advertencia", JOptionPane.WARNING_MESSAGE);

							}
						}
					}
				});
				btnAccion.setActionCommand("OK");
				buttonPane.add(btnAccion);
				getRootPane().setDefaultButton(btnAccion);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadUser(update);
	}

	private void clean() {
		txtCed.setText("");
		txtCodigo.setText("");
		txtCodMed.setText("");
		txtConfirm.setText("");
		txtDirec.setText("");
		txtEspecialidad.setText("");
		txtLogin.setText("");
		txtNombre.setText("");
		txtPassword.setText("");
		txtPuesto.setText("");
		txtTelefono.setText("");
		
	}
	
	private void loadUser(Usuario aux) {
		if (aux != null) {
			txtCodigo.setText(aux.getCodigoUsuario());
			txtNombre.setText(aux.getNombre());
			txtTelefono.setText(aux.getTelefono());
			txtCed.setText(aux.getCedula());
			txtDirec.setText(aux.getDireccion());
			txtLogin.setText(aux.getLogin());
			txtPassword.setText(aux.getPassword());
			if (aux instanceof U_Administrador) {
				rdbtnAdmin.setSelected(true);
				rdbtnAdmin.setEnabled(false);
				rdbtnMedico.setEnabled(false);
				pnAdmin.setVisible(true);
				U_Administrador mod = (U_Administrador)aux;
				txtPuesto.setText(mod.getPuestoLaboral());
			}else if (aux instanceof U_Medico) {
				rdbtnMedico.setSelected(true);
				rdbtnAdmin.setEnabled(false);
				rdbtnMedico.setEnabled(false);
				pnMed.setVisible(true);
				U_Medico mod = (U_Medico)aux;
				txtCodMed.setText(mod.getCodigoMedico());
				txtEspecialidad.setText(mod.getEspecialidad());
			}
		
			
		}
	}
		

	public static Usuario getUpdate() {
		return update;
	}

	public static void setUpdate(Usuario update) {
		RegUsuario.update = update;
	}
}
