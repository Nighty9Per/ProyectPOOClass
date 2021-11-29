package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.CitaMedica;
import logical.Clinica;
import logical.Enfermedad;
import logical.Paciente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class RegPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnBuscar;
	private JComboBox cbxSexo;
	private JSpinner spnNacimiento;
	private static Paciente update = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPaciente dialog = new RegPaciente(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPaciente(Paciente modificar, CitaMedica cita) {
		update = modificar;
		setModal(true);
		setResizable(false);
		if(update == null) {
			setTitle("Registrar Paciente");
		} else {
			setTitle("Modificar Paciente");
		}
		setBounds(100, 100, 597, 247);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00E9dula:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			{
				txtCedula = new JTextField();
				if (update != null) {
					txtCedula.setEnabled(false);
				}
				txtCedula.setColumns(10);
				txtCedula.setBounds(63, 8, 113, 23);
				panel.add(txtCedula);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Nombre:");
				lblNewLabel_2.setBounds(10, 64, 89, 14);
				panel.add(lblNewLabel_2);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEnabled(false);
				if (update != null) {
					txtNombre.setEnabled(true);
				}
				txtNombre.setBounds(63, 61, 113, 23);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			
			spnNacimiento = new JSpinner();
			spnNacimiento.setEnabled(false);
			Date date = new Date();
			spnNacimiento.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
			JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnNacimiento,"dd/MM/yyyy");
			spnNacimiento.setEditor(de_spnFecha);
			spnNacimiento.setBounds(119, 110, 124, 23);
			panel.add(spnNacimiento);
			
			btnBuscar = new JButton("Buscar");
			if(update != null) {
				btnBuscar.setEnabled(false);
			}
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Paciente pat = Clinica.getInstace().buscarPacienteCedula(txtCedula.getText());
					if (pat == null) {
						txtDireccion.setEnabled(true);
						cbxSexo.setEnabled(true);
						spnNacimiento.setEnabled(true);
					}else {
						cbxSexo.setSelectedItem(pat.getGenero());
						spnNacimiento.setModel(new SpinnerDateModel(pat.getFechaNacimiento(), null, null, Calendar.YEAR));
						JSpinner.DateEditor de_spnNac = new JSpinner.DateEditor(spnNacimiento,"dd/MM/yyyy");
						spnNacimiento.setEditor(de_spnNac);
						txtDireccion.setText(pat.getDireccion());
					}
				}
			});
			btnBuscar.setBounds(185, 7, 89, 23);
			panel.add(btnBuscar);
			
			JLabel lblNewLabel_3 = new JLabel("T\u00E9lefono:");
			lblNewLabel_3.setBounds(190, 64, 72, 14);
			panel.add(lblNewLabel_3);
			
			txtTelefono = new JTextField();
			txtTelefono.setEnabled(false);
			if (update != null) {
				txtTelefono.setEnabled(true);
			}
			txtTelefono.setBounds(250, 61, 108, 23);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			{
				JLabel lblSexo = new JLabel("Sexo:");
				lblSexo.setBounds(379, 64, 72, 14);
				panel.add(lblSexo);
			}
			{
				cbxSexo = new JComboBox();
				cbxSexo.setEnabled(false);
				cbxSexo.setEditable(true);
				cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Masculino", "Femenino"}));
				cbxSexo.setBounds(423, 61, 128, 23);
				panel.add(cbxSexo);
			}
			
			JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
			lblFechaNacimiento.setBounds(10, 114, 118, 14);
			panel.add(lblFechaNacimiento);
			
			
			JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n:");
			lblNewLabel_4.setBounds(259, 114, 72, 14);
			panel.add(lblNewLabel_4);
			
			txtDireccion = new JTextField();
			txtDireccion.setEnabled(false);
			if (update != null) {
				txtDireccion.setEnabled(true);
			}
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(322, 110, 229, 23);
			panel.add(txtDireccion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton();
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date dateaux = (Date) spnNacimiento.getValue();
						if (update == null) {
							Clinica.getInstace().crearPaciente(txtCedula.getText(), txtNombre.getText(), cbxSexo.getSelectedItem().toString(), dateaux, txtDireccion.getText(), txtTelefono.getText());
							JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}else {
							Paciente aux = new Paciente(txtCedula.getText(), txtNombre.getText(), cbxSexo.getSelectedItem().toString(), dateaux, txtDireccion.getText(), txtTelefono.getText());
							Clinica.getInstace().editarPaciente(update.getCedula(), aux);
							JOptionPane.showMessageDialog(null, "Paciente Actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				if (update == null) {
					btnRegistrar.setText("Registrar");
				}else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
	}
	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		cbxSexo.setSelectedIndex(0);
		Date date = new Date();
		spnNacimiento.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
		JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnNacimiento,"dd/MM/yyyy");
		spnNacimiento.setEditor(de_spnFecha);
		txtDireccion.setEnabled(false);
		cbxSexo.setEnabled(false);
		spnNacimiento.setEnabled(false);
	}
}
