package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logical.Clinica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCod;
	private JTextField txtNombre;
	private JButton btnCancelar;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setTitle("Registrar Vacuna");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 223, 217);
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
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			
			txtCod = new JTextField();
			txtCod.setText("V-"+Clinica.getInstace().getGenerateCodigoVacuna());
			txtCod.setEditable(false);
			txtCod.setEnabled(false);
			txtCod.setToolTipText("");
			txtCod.setName("");
			txtCod.setColumns(10);
			txtCod.setBounds(10, 26, 134, 23);
			panel.add(txtCod);
			
			JLabel lblNombreDeVacuna = new JLabel("Nombre de Vacuna:");
			lblNombreDeVacuna.setBounds(10, 63, 123, 14);
			panel.add(lblNombreDeVacuna);
			
			txtNombre = new JTextField();
			txtNombre.setToolTipText("");
			txtNombre.setName("");
			txtNombre.setColumns(10);
			txtNombre.setBounds(10, 78, 134, 23);
			panel.add(txtNombre);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtNombre.getText().equals("")) {
							Clinica.getInstace().crearVacunaBajoVigilacia(txtNombre.getText());
							txtCod.setText("V-"+Clinica.getInstace().getGenerateCodigoVacuna());
							txtNombre.setText("");
							JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
						}else {
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
}
