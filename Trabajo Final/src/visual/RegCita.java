package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.U_Medico;
import logical.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_1;
	private JSpinner spnFecha;
	private JLabel lblNewLabel_2;
	private JTextField txtNombre;
	private JLabel lblTlefonoAgendado;
	private JTextField txtTelefono;
	private JLabel lblNewLabel_3;
	private JComboBox cbxMedico;
	private DefaultComboBoxModel<String> cadena = null;
	private ArrayList<String> cadCodigo = null;
	private JTextField txtCed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCita dialog = new RegCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCita() {
		setTitle("Registro de Citas");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 476, 264);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setToolTipText("");
		txtCodigo.setText("CM-"+Clinica.getInstace().getGenerateCodigoCita());
		txtCodigo.setName("");
		txtCodigo.setEnabled(false);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(66, 7, 124, 23);
		panel.add(txtCodigo);
		
		lblNewLabel_1 = new JLabel("Fecha de Cita:");
		lblNewLabel_1.setBounds(211, 10, 80, 14);
		panel.add(lblNewLabel_1);
		
		spnFecha = new JSpinner();
		Date date = new Date();
		spnFecha.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
		JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha,"dd/MM/yyyy");
		spnFecha.setEditor(de_spnFecha);
		spnFecha.setBounds(301, 7, 92, 23);
		panel.add(spnFecha);
		
		lblNewLabel_2 = new JLabel("Nombre Agendado:");
		lblNewLabel_2.setBounds(211, 67, 124, 14);
		panel.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(211, 92, 124, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblTlefonoAgendado = new JLabel("T\u00E9lefono Agendado:");
		lblTlefonoAgendado.setBounds(10, 130, 124, 14);
		panel.add(lblTlefonoAgendado);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 153, 124, 23);
		panel.add(txtTelefono);
		
		lblNewLabel_3 = new JLabel("M\u00E9dico Agendado:");
		lblNewLabel_3.setBounds(211, 129, 124, 14);
		panel.add(lblNewLabel_3);
		
		cbxMedico = new JComboBox();
		cbxMedico.setBounds(211, 153, 124, 23);
		cadena = new DefaultComboBoxModel<String>();
		cadCodigo = new ArrayList<String>();
		cadena.addElement("<<Seleccione>>");
		cadCodigo.add("0");
		for (int i = 0; i < Clinica.getInstace().getMisUsuarios().size(); i++) {
			if (Clinica.getInstace().getMisUsuarios().get(i) instanceof U_Medico) {
				cadena.addElement(""+Clinica.getInstace().getMisUsuarios().get(i).getNombre());
				cadCodigo.add(Clinica.getInstace().getMisUsuarios().get(i).getCodigoUsuario());
			}
		}
		cbxMedico.setModel(cadena);
		panel.add(cbxMedico);
		
		JLabel lblCdulaAgendada = new JLabel("C\u00E9dula Agendada:");
		lblCdulaAgendada.setBounds(10, 67, 124, 14);
		panel.add(lblCdulaAgendada);
		
		txtCed = new JTextField();
		txtCed.setColumns(10);
		txtCed.setBounds(10, 90, 124, 23);
		panel.add(txtCed);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validacion() == true) {
							Usuario med = Clinica.getInstace().buscarUsuarioCodigo(cadCodigo.get(cbxMedico.getSelectedIndex()));
							Date dateaux = (Date) spnFecha.getValue();
							Clinica.getInstace().crearCitaMedica(txtCed.getText(),dateaux, txtNombre.getText(), txtTelefono.getText(), med);
							JOptionPane.showMessageDialog(null, "Registro Exitoso", "Informaci?n", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}else if (validacion() == false){
							JOptionPane.showMessageDialog(null, "No debe dejar campos vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
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
	
	private boolean validacion() {
		boolean validar = false;
		if (!txtCed.getText().equals("") && !txtNombre.getText().equals("") && !txtTelefono.getText().equals("" ) && cbxMedico.getSelectedIndex() != 0) {
			validar = true;
		}
		return validar;
	}
	
	private void clean() {
		txtNombre.setText("");
		txtTelefono.setText("");
		Date date = new Date();
		spnFecha.setModel(new SpinnerDateModel(date, null, null, Calendar.YEAR));
		JSpinner.DateEditor de_spnFecha = new JSpinner.DateEditor(spnFecha,"dd/MM/yyyy");
		spnFecha.setEditor(de_spnFecha);
		txtCodigo.setText("CM-"+Clinica.getInstace().getGenerateCodigoCita());
		cbxMedico.setSelectedIndex(0);
		txtCed.setText("");
	}

	public DefaultComboBoxModel<String> getCadena() {
		return cadena;
	}

	public void setCadena(DefaultComboBoxModel<String> cadena) {
		this.cadena = cadena;
	}
}
