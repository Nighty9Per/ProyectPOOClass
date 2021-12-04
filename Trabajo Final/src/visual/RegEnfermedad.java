package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logical.Clinica;
import logical.Enfermedad;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class RegEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCod;
	private JTextField txtNombre;
	private JButton btnAccion;
	private JButton btnCancel;
	private static Enfermedad update = null;
	private JTextPane txtpDescrip;
	private JComboBox cbxTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad(Enfermedad modificar) {
		update = modificar;
		setModal(true);
		setResizable(false);
		if(update == null) {
			setTitle("Registrar Enfermedad");
		} else {
			setTitle("Modificar Enfermedad");
		}
		setBounds(100, 100, 393, 287);
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
			{
				JLabel label = new JLabel("C\u00F3digo:");
				label.setBounds(10, 11, 46, 14);
				panel.add(label);
			}
			{
				txtCod = new JTextField();
				txtCod.setToolTipText("");
				txtCod.setText("E-"+Clinica.getInstace().getGenerateCodigoEnfermedad());
				txtCod.setName("");
				txtCod.setEnabled(false);
				txtCod.setEditable(false);
				txtCod.setColumns(10);
				txtCod.setBounds(10, 26, 134, 23);
				panel.add(txtCod);
			}
			{
				JLabel lblNombreDeEnfermedad = new JLabel("Nombre de Enfermedad:");
				lblNombreDeEnfermedad.setBounds(10, 71, 164, 14);
				panel.add(lblNombreDeEnfermedad);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setToolTipText("");
				txtNombre.setName("");
				txtNombre.setColumns(10);
				txtNombre.setBounds(10, 86, 134, 23);
				panel.add(txtNombre);
			}
			{
				JLabel lblTipoDeEnfermedad = new JLabel("Tipo de Enfermedad:");
				lblTipoDeEnfermedad.setBounds(10, 134, 123, 14);
				panel.add(lblTipoDeEnfermedad);
			}
			
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Viral", "Bacteriana", "F\u00FAngica", "Parasitarias"}));
			cbxTipo.setBounds(10, 151, 134, 23);
			panel.add(cbxTipo);
			
			JLabel lblDescripcinDeEnfermedad = new JLabel("Descripci\u00F3n de Enfermedad:");
			lblDescripcinDeEnfermedad.setBounds(184, 11, 187, 14);
			panel.add(lblDescripcinDeEnfermedad);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(184, 28, 162, 146);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			{
				txtpDescrip = new JTextPane();
				scrollPane.setViewportView(txtpDescrip);
			}
		}
		if (update != null) {
			txtCod.setText(update.getCodigoEnfermedad());
			txtNombre.setText(update.getNombreEnfermedad());
			txtpDescrip.setText(update.getDescripcionEnfermedad());
			cbxTipo.setSelectedItem(update.getTipoEnfermedad());
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAccion = new JButton();
				btnAccion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (validacion() == true) {
							if (update == null) {
								if (cbxTipo.getSelectedIndex() != 0) {
									Clinica.getInstace().crearEnfermedadBajoVigilancia(txtNombre.getText(), cbxTipo.getSelectedItem().toString(), txtpDescrip.getText());
									JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}else {
									JOptionPane.showMessageDialog(null, "Debe elegir el tipo de enfermedad", "Advertencia", JOptionPane.WARNING_MESSAGE);
								}
							}else {
								Enfermedad aux = new Enfermedad(update.getCodigoEnfermedad(), txtNombre.getText(), cbxTipo.getSelectedItem().toString(), txtpDescrip.getText());
								Clinica.getInstace().editarEnfermedad(update.getCodigoEnfermedad(), aux);
								JOptionPane.showMessageDialog(null, "Enfermedad Actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
							}
						}else if (validacion() == false){
							JOptionPane.showMessageDialog(null, "No debe dejar campos vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
					}

					

					
				});
				if (update == null) {
					btnAccion.setText("Registrar");
				}else {
					btnAccion.setText("Modificar");
				}
				btnAccion.setActionCommand("OK");
				buttonPane.add(btnAccion);
				getRootPane().setDefaultButton(btnAccion);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		loadEnfermedad(update);
	}
	private void loadEnfermedad(Enfermedad aux) {
		if (aux != null) {
			txtCod.setText(aux.getCodigoEnfermedad());
			txtNombre.setText(aux.getNombreEnfermedad());
			cbxTipo.setSelectedItem(aux.getTipoEnfermedad());
			txtpDescrip.setText(aux.getDescripcionEnfermedad());
		}
	}

	private boolean validacion() {
		boolean validar = false; 
		if (!txtNombre.getText().equals("") && !txtpDescrip.getText().equals("") && cbxTipo.getSelectedIndex() != 0) {
			validar = true;
		}
		return validar;
	}
	
	private void clean() {
		txtCod.setText("E-"+Clinica.getInstace().getGenerateCodigoEnfermedad());
		txtNombre.setText("");
		cbxTipo.setSelectedIndex(0);
		txtpDescrip.setText("");
	}

	public static Enfermedad getUpdate() {
		return update;
	}

	public static void setUpdate(Enfermedad update) {
		RegEnfermedad.update = update;
	}
}
